package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Shift  extends AbstractAction<Keeper<?>> {

    @Override
    public void execute(float deltaTime) {

        Keeper<?> ripley = getActor();

        if (ripley.getBackpack() != null) {
            ripley.getBackpack().shift();
            setDone(true);
        }

    }
}
