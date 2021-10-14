package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;

public class Ripley extends AbstractActor implements Movable, Keeper {


    private int speed;
    private int energy;
    private int ammo;
    private Animation animationRipley = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Backpack backpack;

    public Ripley() {
        super("Ellen");
        setAnimation(animationRipley);
        speed = 2;
        energy = 100;
        ammo = 50;
        backpack = new Backpack("Ripley's backpack", 10);
    }


    public void addActor(Actor actor, int xposition, int yposition) {
        Scene scene = getScene();
        scene.getActors();
        actor.addedToScene(scene);
        actor.setPosition(xposition, yposition);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction) {

        getAnimation().setRotation(direction.getAngle()-90);
        getAnimation().play();


    }

    @Override
    public void stoppedMoving() {
        getAnimation().pause();

    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;

        if (energy >= 100) {
            this.energy = 100;
        }

        if (energy <= 0) {
            this.energy = 0;
        }
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getAmmo() {
        return ammo;
    }

    @Override
    public ActorContainer getBackpack() {
         return backpack;
    }
}
