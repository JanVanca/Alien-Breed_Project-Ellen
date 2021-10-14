package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.List;

public class Take <T extends Actor> extends AbstractAction<Keeper<T>> {

    public Take() {
        setDone(false);


    }



    @Override
    public void execute(float deltaTime) {

        Keeper<T> ripley = getActor();
        Scene scene = getActor().getScene();


        for(Actor actor : scene) {
            if (actor.intersects(ripley)) {

              try {
                  ripley.getBackpack().add((T) actor);
                  setDone(true);
              } catch (Exception e) {
                  int windowHeight = scene.getGame().getWindowSetup().getHeight();
                  int topOffset = GameApplication.STATUS_LINE_OFFSET;
                  int yTextPos = windowHeight - topOffset;
                  scene.getGame().getOverlay().drawText(e.getMessage(), 200, yTextPos-100).showFor(2);
              }

              if (isDone()) {
                  scene.removeActor(actor);
              }

            }

        }


    }
}
