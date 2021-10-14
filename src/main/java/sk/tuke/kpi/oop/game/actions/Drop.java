package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop <A extends Actor> extends AbstractAction<Keeper<A>> {

    @Override
    public void execute(float deltaTime) {
        Scene scene = getActor().getScene();
        Keeper<A> ripley = getActor();

        if (ripley.getBackpack() != null) {
            ActorContainer<A> backpack = ripley.getBackpack();
            if (backpack.peek() == null) {
                return;
            }
            Collectible item = (Collectible) backpack.peek();
            item.setPosition(ripley.getPosX(), ripley.getPosY());
            scene.addActor(item);
            ripley.getBackpack().remove(ripley.getBackpack().peek());
            setDone(true);
        }






    }
}
