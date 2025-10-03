package battle;

import droid.AbstractDroid;

import java.util.List;

public class OneByOneBattle extends AbstractBattle {

    public OneByOneBattle(AbstractDroid firstDroid, AbstractDroid secondDroid) {
        super(List.of(firstDroid), List.of(secondDroid));
    }

    @Override
    protected void battleMove() {
        if (currentMove % 2 == 1) {
            BattleUtils.findRandomAlive(firstTeam).useAbility(firstTeam, secondTeam, battleLogger);
        } else {
            BattleUtils.findRandomAlive(secondTeam).useAbility(secondTeam, firstTeam, battleLogger);
        }
    }

}