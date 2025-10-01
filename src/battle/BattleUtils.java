package battle;

import droid.AbstractDroid;

import java.util.List;
import java.util.Random;

public class BattleUtils {

    public static List<AbstractDroid> findAlives(List<AbstractDroid> team) {
        if (team == null) {
            return List.of();
        }
        return team.stream().filter(AbstractDroid::isAlive).toList();
    }

    public static List<AbstractDroid> findDeads(List<AbstractDroid> team) {
        if (team == null) {
            return List.of();
        }
        return team.stream().filter(abstractDroid -> !abstractDroid.isAlive()).toList();
    }

    public static AbstractDroid findFirstAlive(List<AbstractDroid> team) {
        return findAlives(team).stream().findFirst().orElse(null);
    }

    public static AbstractDroid findRandomAlive(List<AbstractDroid> team) {
        List<AbstractDroid> alives = findAlives(team);
        if (alives.isEmpty()) {
            return null;
        }
        return alives.get(new Random().nextInt(0, alives.size()));
    }

    public static AbstractDroid findFirstDead(List<AbstractDroid> team) {
        return findDeads(team).stream().findFirst().orElse(null);
    }

    public static boolean isTeamAlive(List<AbstractDroid> team) {
        return findFirstAlive(team) != null;
    }

    public static boolean isTeamAFirst() {
        return new Random().nextBoolean();
    }

}