package droid;

import battle.BattleLogger;

import java.util.List;

public class DefaultDroid extends AbstractDroid {

    DefaultDroid(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance) {
        super(name, health, abilityValue, abilityMultiplier, abilityCritChance);
    }

    @Override
    public void executeAbility(List<AbstractDroid> currentTeam, List<AbstractDroid> otherTeam, BattleLogger logger) {
        AbstractDroid target = otherTeam.stream().filter(AbstractDroid::isAlive).findFirst().orElse(null);

        if (target == null) {
            return;
        }

        target.takeDamage(currentAbilityValue);
        logger.logAttack(this, target);
    }
}