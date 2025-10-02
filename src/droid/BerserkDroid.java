package droid;

import battle.BattleLogger;
import battle.BattleUtils;

import java.util.List;

public class BerserkDroid extends AbstractDroid {

    private int rageCoefficient;

    BerserkDroid(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance, int rageCoefficient) {
        super(name, health, abilityValue, abilityMultiplier, abilityCritChance);
        this.rageCoefficient = rageCoefficient;
        this.icon = "\uD83E\uDE93";
    }

    @Override
    protected void prepareAbility() {
        isAbilityCrit = Math.random() <= abilityCritChance;
        currentAbilityValue = calculateBerserkDamageByHealth(isAbilityCrit);
    }

    private int calculateBerserkDamageByHealth(boolean crit) {
        double rageBoost = 1 + rageCoefficient * (1 - currentHealth / (double) maxHealth);
        return (int) (abilityValue * rageBoost * (crit ? abilityMultiplier : 1));
    }

    @Override
    public AbstractDroid copy() {
        return new BerserkDroid(name, maxHealth, abilityValue, abilityMultiplier, abilityCritChance, rageCoefficient);
    }

    @Override
    public void executeAbility(List<AbstractDroid> currentTeam, List<AbstractDroid> otherTeam, BattleLogger logger) {
        AbstractDroid target = BattleUtils.findRandomAlive(otherTeam);
        if (target == null) {
            return;
        }

        target.takeDamage(currentAbilityValue);
        logger.logAttack(this, target);
    }
}
