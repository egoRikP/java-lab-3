package droid;

import battle.BattleLogger;

import java.util.List;

public abstract class AbstractDroid {

    protected String name;
    protected String icon = "\uD83E\uDD16";

    protected int maxHealth;
    protected int currentHealth;

    protected int currentAbilityValue;
    protected int abilityValue;
    protected int abilityMultiplier;
    protected double abilityCritChance;
    protected boolean isAbilityCrit;

    public final void useAbility(List<AbstractDroid> currentTeam, List<AbstractDroid> otherTeam, BattleLogger logger) {
        prepareAbility();
        executeAbility(currentTeam, otherTeam,logger);
    }

    public abstract void executeAbility(List<AbstractDroid> currentTeam, List<AbstractDroid> otherTeam, BattleLogger logger);

    AbstractDroid(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance) {
        this.name = name;

        this.maxHealth = health;
        this.currentHealth = health;

        this.abilityValue = abilityValue;
        this.abilityMultiplier = abilityMultiplier;
        this.abilityCritChance = abilityCritChance;
    }

    public void takeDamage(int amount) {
        if (amount < 0) {
            return;
        }

        if (!isAlive()) {
            return;
        }
        currentHealth -= amount;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    public void heal(int amount) {

        if (amount < 0) {
            return;
        }

        currentHealth += amount;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    protected void prepareAbility() {
        isAbilityCrit = Math.random() <= abilityCritChance;
        currentAbilityValue = isAbilityCrit ? abilityValue * abilityMultiplier : abilityValue;
    }

    public int getCurrentAbilityValue() {
        return currentAbilityValue;
    }

    public boolean isAbilityCrit() {
        return isAbilityCrit;
    }

    public String formatPrint() {
        StringBuilder health = new StringBuilder("----------");
        int count = (currentHealth * health.length()) / maxHealth;

        for (int i = 0; i < count; i++) {
            health.setCharAt(i, '█');
        }

        return String.format("[%s %s [%s] %d/%d]", currentHealth > 0 ? icon : "❌ " + icon, name, health, currentHealth, maxHealth);
    }

    public boolean isDamaged() {
        return currentHealth != maxHealth;
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s [HP: %d/%d, Ability: %d, Multiplier: x%d, CritChance: %.0f%%]", icon, name, currentHealth, maxHealth, abilityValue, abilityMultiplier, abilityCritChance * 100
        );
    }

}