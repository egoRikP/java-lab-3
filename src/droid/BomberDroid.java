package droid;

import battle.BattleLogger;
import battle.BattleUtils;

import java.util.List;

public class BomberDroid extends AbstractDroid {

    private int abilityRange;

    BomberDroid(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance, int abilityRange) {
        super(name, health, abilityValue, abilityMultiplier, abilityCritChance);
        this.abilityRange = abilityRange;
        this.icon = "\uD83D\uDCA3";
    }

    @Override
    public AbstractDroid copy() {
        return new BomberDroid(name, maxHealth, abilityValue, abilityMultiplier, abilityCritChance, abilityRange);
    }

    @Override
    public void executeAbility(List<AbstractDroid> currentTeam, List<AbstractDroid> otherTeam, BattleLogger logger) {

        AbstractDroid target = otherTeam.stream().filter(AbstractDroid::isAlive).findFirst().orElse(null);

        if (target == null) {
            return;
        }

        target.takeDamage(currentAbilityValue);
        logger.logAttack(this, target);

        List<AbstractDroid> otherDroid = BattleUtils.findAlives(otherTeam);

        for (AbstractDroid droid : otherDroid) {
            if (droid == target) {
                continue;
            }
            int splashDamage = currentAbilityValue * 50 / 100;
            droid.takeDamage(splashDamage);
            logger.log("  -> " + droid.formatPrint() + " took splash damage " + splashDamage);
        }

    }
}
