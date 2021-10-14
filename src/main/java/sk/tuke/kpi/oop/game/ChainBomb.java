package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;


public class ChainBomb extends TimeBomb {

    Ellipse2D.Float ellipse = new Ellipse2D.Float();
    private float radius = 50;


    public ChainBomb(float timer) {
        super(timer);
    }

    @Override
    public void activate() throws InterruptedException {
        ellipse.setFrame(this.getPosX(), this.getPosY(), radius, radius);

        super.activate();
        activateOtherBombs();
    }

    public void activateOtherBombs() throws InterruptedException {

        Scene scene = getScene();
        List<Actor> allActors = scene.getActors();
        for (Actor actor : allActors) {
            if (actor instanceof ChainBomb) {
                ChainBomb bomb = (ChainBomb) actor;
                if (isActorInRadius(bomb)) {
                    bomb.activate();
                }
            }
        }
    }
    private boolean isActorInRadius(ChainBomb otherBomb) {
        Rectangle2D.Float rectangleOfOtherBomb = new Rectangle2D.Float();
        rectangleOfOtherBomb.setRect(otherBomb.getPosX(), otherBomb.getPosY(), otherBomb.getWidth(), otherBomb.getHeight());
        return (ellipse.intersects(rectangleOfOtherBomb));
    }


}
