package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<A extends Actor> extends AbstractAction<A> {

    private Usable<A> usableActor;

    public Use(Usable<A> usableActor) {
        this.usableActor = usableActor;
    }

    @Override
    public void execute(float deltaTime) {
        usableActor.useWith(getActor());
        setDone(true);
    }
}
