package droid;

public class DroidFactory {

    public static AbstractDroid createDroid(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance) {
        return new DefaultDroid(name, health, abilityValue, abilityMultiplier, abilityCritChance);
    }

    public static AbstractDroid createDefaultDroid(String name) {
        return createDroid(name, 100, 15, 2, 0.3);
    }

    public static AbstractDroid createBomber(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance, int abilityRange) {
        return new BomberDroid(name, health, abilityValue, abilityMultiplier, abilityCritChance, abilityRange);
    }

    public static AbstractDroid createDefaultBomber(String name) {
        return createBomber(name, 100, 15, 2, 0.3, 3);
    }

    public static AbstractDroid createLaser(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance, int abilityChargeCount) {
        return new LaserDroid(name, health, abilityValue, abilityMultiplier, abilityCritChance, abilityChargeCount);
    }

    public static AbstractDroid createDefaultLaser(String name) {
        return createLaser(name, 100, 15, 2, 0.3, 3);
    }

    public static AbstractDroid createBerserk(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance, int rageCoefficient) {
        return new BerserkDroid(name, health, abilityValue, abilityMultiplier, abilityCritChance, rageCoefficient);
    }

    public static AbstractDroid createDefaultBerserk(String name) {
        return createBerserk(name, 100, 15, 2, 0.5, 3);
    }

    public static AbstractDroid createHealer(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance, int abilityCount) {
        return new HealerDroid(name, health, abilityValue, abilityMultiplier, abilityCritChance, abilityCount);
    }

    public static AbstractDroid createDefaultHealer(String name) {
        return createHealer(name, 100, 15, 2, 0.5, 3);
    }
}