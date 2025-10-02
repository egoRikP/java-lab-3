package droid;

import battle.BattleLogger;
import battle.BattleUtils;

import java.util.List;

public class LaserDroid extends AbstractDroid {

    private int currentCount = 0;
    private int countToLoad;

    LaserDroid(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance, int countToLoad) {
        super(name, health, abilityValue, abilityMultiplier, abilityCritChance);
        this.icon = "⚡";
        this.countToLoad = countToLoad;
    }

    @Override
    public AbstractDroid copy() {
        return new LaserDroid(name, maxHealth, abilityValue, abilityMultiplier, abilityCritChance, countToLoad);
    }

    @Override
    public void executeAbility(List<AbstractDroid> currentTeam, List<AbstractDroid> otherTeam, BattleLogger logger) {

        currentCount++;
        logger.log(String.format("%s charge laser %d/%d", this.formatPrint(), currentCount, countToLoad));

        if (currentCount == countToLoad) {
            prepareAbility();
            AbstractDroid target = BattleUtils.findRandomAlive(otherTeam);
            target.takeDamage(currentAbilityValue);
            logger.logAttack(this, target);

            currentCount = 0;
        }

    }

    @Override
    public String toString() {
        return String.format(
                "%s -> Laser (charge attack) { countToCharge %d }", super.toString(), countToLoad
        );
    }
}
