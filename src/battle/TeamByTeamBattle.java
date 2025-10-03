package battle;

import droid.AbstractDroid;

import java.util.List;

public class TeamByTeamBattle extends AbstractBattle {

    public TeamByTeamBattle(List<AbstractDroid> firstTeam, List<AbstractDroid> secondTeam) {
        super(firstTeam, secondTeam);
    }

    public TeamByTeamBattle(AbstractDroid firstTeam[], AbstractDroid secondTeam[]) {
        super(List.of(firstTeam), List.of(secondTeam));
    }

    @Override
    protected void battleMove() {
        if (BattleUtils.isTeamAFirst()) {
            BattleUtils.findRandomAlive(firstTeam).useAbility(firstTeam, secondTeam, battleLogger);
        } else {
            BattleUtils.findRandomAlive(secondTeam).useAbility(secondTeam, firstTeam, battleLogger);
        }
    }

}