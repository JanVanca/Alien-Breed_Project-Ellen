package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Helicopter;

public class AttackPlayerAction extends AbstractAction<Helicopter> {

    @Override
    public void execute(float deltaTime) {
        Helicopter helicopter = getActor();
        helicopter.searchAndDestroy();

    }
}
