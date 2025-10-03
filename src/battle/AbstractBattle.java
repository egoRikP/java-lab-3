package battle;

import droid.AbstractDroid;
import droid.HealerDroid;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBattle {

    protected List<AbstractDroid> firstTeam;
    protected List<AbstractDroid> secondTeam;

    protected int currentMove;

    protected BattleLogger battleLogger;

    public AbstractBattle(List<AbstractDroid> firstTeam, List<AbstractDroid> secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;

        if (!isTeamSizeCorrect()) {
            throw new IllegalArgumentException("Teams sizes not same!");
        }

        this.firstTeam = deepCopyTeam(firstTeam);
        this.secondTeam = deepCopyTeam(secondTeam);
    }

    protected final List<AbstractDroid> deepCopyTeam(List<AbstractDroid> team) {
        List<AbstractDroid> deepCopy = new ArrayList<>();
        for (AbstractDroid droid : team) {
            deepCopy.add(droid.copy());
        }
        return deepCopy;
    }

    public final void start() {
        if (!isAllowed()) {
            throw new IllegalArgumentException("Teams can't have only ability droid (like healers)!");
        }

        battleLogger = new BattleLogger();

        battleLogger.log(String.format("BATTLE %d VS %d", firstTeam.size(), secondTeam.size()));

        showDetailedInfoTeams();

        battleCycle();

        if (BattleUtils.isTeamAlive(firstTeam)) {
            battleLogger.log("FIRST TEAM WIN");
        } else {
            battleLogger.log("SECOND TEAM WIN");
        }

        showDetailedInfoTeams();
    }

    public BattleLogger getBattleLogger() {
        return battleLogger;
    }

    private void showTeam(List<AbstractDroid> team) {
        for (AbstractDroid droid : team) {
            battleLogger.log(droid.toString());
        }
    }

    private void showDetailedInfoTeams() {
        battleLogger.log("FIRST TEAM DROIDS:");
        showTeam(firstTeam);
        battleLogger.log("");

        battleLogger.log("SECOND TEAM DROIDS:");
        showTeam(secondTeam);
        battleLogger.log("");
    }

    protected void battleCycle() {
        while (BattleUtils.isTeamAlive(firstTeam) && BattleUtils.isTeamAlive(secondTeam)) {
            currentMove++;
            battleLogger.log(String.format("==========================  MOVE %d ==========================", currentMove));
            battleLogger.log("");
            battleMove();
            battleLogger.log("");
        }
    }

    protected abstract void battleMove();

    protected boolean isAllowed() {
        //can't battle when only healers
        return firstTeam.stream().anyMatch(e -> !(e instanceof HealerDroid)) &&
                secondTeam.stream().anyMatch(e -> !(e instanceof HealerDroid));
    }

    protected final boolean isTeamSizeCorrect() {
        return firstTeam.size() == secondTeam.size();
    }
}