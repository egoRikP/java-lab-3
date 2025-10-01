package droid;

public class DroidFactory {

    public static AbstractDroid createDroid(String name, int health, int abilityValue, int abilityMultiplier, double abilityCritChance) {
        return new DefaultDroid(name, health, abilityValue, abilityMultiplier, abilityCritChance);
    }

    public static AbstractDroid createDefaultDroid(String name) {
        return new DefaultDroid(name, 100, 15, 2, 0.3);
    }

}