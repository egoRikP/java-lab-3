package droid;

import battle.BattleLogger;
import battle.BattleUtils;

import java.util.List;

public class HealerDroid extends AbstractDroid {

    private int abilityCount;

    HealerDroid(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance, int abilityCount) {
        super(name, health, abilityValue, abilityMultiplier, abilityCritChance);
        this.abilityCount = abilityCount;
        this.icon = "❤️";
    }

    @Override
    public AbstractDroid copy() {
        return new HealerDroid(name, maxHealth, abilityValue, abilityMultiplier, abilityCritChance, abilityCount);
    }

    @Override
    public void executeAbility(List<AbstractDroid> currentTeam, List<AbstractDroid> otherTeam, BattleLogger logger) {

        AbstractDroid[] toHeal = BattleUtils.findAlives(currentTeam).stream()
                .filter(AbstractDroid::isDamaged)
                .toArray(AbstractDroid[]::new);

        if (toHeal.length == 0) {
            logger.log(String.format("%s can't heal, everyone full HP!", this.formatPrint()));
        } else {
            logger.log(String.format("%s starts healing teammates:", this.formatPrint()));
            for (int i = 0; i < toHeal.length && i < abilityCount; i++) {
                toHeal[i].heal(currentAbilityValue);
                logger.log(String.format("  -> Healed %s + %d", toHeal[i].formatPrint(), currentAbilityValue));
            }
        }

    }

    @Override
    public String toString() {
        return String.format(
                "%s -> Healer (heal mates) { mates to heal %d }", super.toString(), abilityCount
        );
    }
}
