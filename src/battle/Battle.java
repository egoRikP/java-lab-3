package battle;

import droid.AbstractDroid;

import java.util.ArrayList;
import java.util.List;

public class Battle {

    private List<AbstractDroid> teamA;
    private List<AbstractDroid> teamB;

    private int currentMove;

    private BattleLogger logger = new BattleLogger();

    public Battle(List<AbstractDroid> teamA, List<AbstractDroid> teamB) {
        this.teamA = new ArrayList<>();

        for (AbstractDroid droid : teamA) {
            this.teamA.add(droid.copy());
        }

        this.teamB = new ArrayList<>();

        for (AbstractDroid droid : teamB) {
            this.teamB.add(droid.copy());
        }
    }

    private void showTeam(List<AbstractDroid> team) {
        if (team.size() == 0) {
            return;
        }

        for (AbstractDroid droid : team) {
            logger.log(droid.formatPrint());
        }
    }

    private void showInfo() {
        logger.log("TEAM A");
        showTeam(teamA);

        logger.log("TEAM B");
        showTeam(teamB);
    }

    public void start() {

        if (teamA.size() == 0 || teamB.size() == 0) {
            return;
        }

        showInfo();
        logger.log("\n");

        while (BattleUtils.isTeamAlive(teamA) && BattleUtils.isTeamAlive(teamB)) {
            currentMove++;

            logger.log(String.format("=============  MOVE %2d =============", currentMove));

            if (BattleUtils.isTeamAFirst()) {
                logger.log("TEAM A");
                BattleUtils.findRandomAlive(teamA).useAbility(teamA, teamB, logger);
            } else {
                logger.log("TEAM B");
                BattleUtils.findRandomAlive(teamB).useAbility(teamB, teamA, logger);
            }

        }

        showInfo();
    }

    public void showLogs() {
        logger.show();
    }

    public BattleLogger getLogger() {
        return logger;
    }
}