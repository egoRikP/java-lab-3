package battle;

import droid.AbstractDroid;

import java.util.ArrayList;
import java.util.List;

public class BattleLogger {

    private final List<String> logs = new ArrayList<>();

    public void logAttack(AbstractDroid current, AbstractDroid target) {

        logs.add(String.format("%s %s %d %s",
                current.formatPrint(),
                (current.isAbilityCrit() ? "CRIT " : " ATTACK "),
                current.getCurrentAbilityValue(),
                target.formatPrint()));

        if (!target.isAlive()) {
            logs.add(String.format("%s BEAT %s", current.formatPrint(), target.formatPrint()));
        }
    }

    public void log(String text) {
        logs.add(text);
    }

    public void show() {
        for (String string : logs) {
            System.out.println(string);
        }
    }

    public List<String> getLogs() {
        return logs;
    }
}
