package battle;

import droid.AbstractDroid;

import java.util.List;
import java.util.Random;

public class Battle {

    private List<AbstractDroid> teamA;
    private List<AbstractDroid> teamB;

    private int currentMove;

    public Battle(List<AbstractDroid> teamA, List<AbstractDroid> teamB) {
        this.teamA = List.copyOf(teamA);
        this.teamB = List.copyOf(teamB);
    }

    private void showTeam(List<AbstractDroid> team) {
        for (AbstractDroid droid : teamA) {
            System.out.println(droid);
        }
    }

    private void showInfo() {
        System.out.println(String.format("TEAM A"));
        showTeam(teamA);

        System.out.println(String.format("TEAM B"));
        showTeam(teamB);
    }

    public void start() {
        
        if (teamA.size() == 0 || teamB.size() == 0) {
            System.out.println("Team can't be empty!");
            return;
        }

        showInfo();

        while (teamA.stream().anyMatch(AbstractDroid::isAlive) && teamB.stream().anyMatch(AbstractDroid::isAlive)) {
            currentMove++;

            System.out.println(String.format("MOVE %d", currentMove));

            if (new Random().nextBoolean()) {
                System.out.println(String.format("TEAM A"));
                teamA.getFirst().useAbility(teamA, teamB);
            } else {
                System.out.println(String.format("TEAM B"));
                teamB.getFirst().useAbility(teamB, teamA);
            }

        }

    }

}