package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChainBomb extends TimeBomb {
    private Ellipse2D.Float explosionRadius = new Ellipse2D.Float();
    private float time;
    private ArrayList<ChainBomb> chainBombs = new ArrayList<>();

    public ChainBomb(float time) {
        super(time);
        this.time = time;

    }

    public void explodeBomb() {
        activate();
        while (time > 0) {
            time = time - 1;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        activateNearBombs();
        explodeAllNearBombs();
    }

    private void explodeAllNearBombs() {
        for (ChainBomb bomb : chainBombs) {
            bomb.activate();
        }
    }

    private void activateNearBombs() {
        Ellipse2D.Float explosionRadius = new Ellipse2D.Float(this.getPosX() - 25, this.getPosY() - 25, getPosX() + 25, getPosY() + 25);

        List<Actor> actorsList = getScene().getActors();
        for (Actor actor : actorsList) {
            if (actor instanceof ChainBomb && !((ChainBomb) actor).isActivated()) {
                ChainBomb bomb = (ChainBomb) actor;
                Rectangle2D.Float bombFields = new Rectangle2D.Float(bomb.getPosX() - 25, bomb.getPosY() - 25, bomb.getPosX() + 25, bomb.getPosY() + 25);
                if (explosionRadius.intersects(bombFields)) {
                    chainBombs.add(bomb);
                }
            }
        }
    }
}
