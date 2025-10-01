package droid;

import java.util.List;

public class DefaultDroid extends AbstractDroid {

    DefaultDroid(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance) {
        super(name, health, abilityValue, abilityMultiplier, abilityCritChance);
    }

    @Override
    public void executeAbility(List<AbstractDroid> currentTeam, List<AbstractDroid> otherTeam) {
        AbstractDroid target = otherTeam.stream().filter(AbstractDroid::isAlive).findFirst().orElse(null);

        if (target == null) {
            return;
        }

        target.takeDamage(currentAbilityValue);

        System.out.printf(String.format("[A] %s %s %d [B] %s\n",
                this.formatPrint(),
                (this.isAbilityCrit() ? "CRIT" : "ATTACK") + " WITH DAMAGE ",
                this.getCurrentAbilityValue(),
                target.formatPrint()));

        if (!target.isAlive()) {
            System.out.printf(String.format("[A] %s BEAT [B] %s\n",
                    this.formatPrint(),
                    target.formatPrint()));
        }
    }
}