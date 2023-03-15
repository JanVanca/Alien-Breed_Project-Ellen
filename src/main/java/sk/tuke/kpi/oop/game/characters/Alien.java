package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

import java.util.Objects;

public class Alien extends AbstractActor implements Movable, Enemy, Alive {

   private Animation alienAnimation = new Animation("sprites/alien.png",32, 32, 01.f, Animation.PlayMode.LOOP_PINGPONG);
   private Health health;
   private Behaviour<? super Alien> behaviour;

   private int speed;


    public Alien() {
        setAnimation(alienAnimation);
        health = new Health(100);
        health.onExhaustion(() -> getScene().removeActor(this));
        this.speed = 2;
    }

    public Alien(int health, Behaviour<? super Alien> behaviour) {
        setAnimation(alienAnimation);
        this.health = new Health(health);
        this.health.onExhaustion(() -> getScene().removeActor(this));
        this.behaviour = behaviour;
        this.speed = 2;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        if(this.behaviour != null){
            this.behaviour.setUp(this);
        }
        for (Actor actor : Objects.requireNonNull(getScene()).getActors()) {
            if (actor instanceof Ripley) {
                Alive target = (Alive) actor;
                new Loop<>(
                    new When<>(
                        () -> this.intersects(target),
                        new Invoke<>(() -> target.getHealth().drain(1))
                    )
                ).scheduleFor(this);
            }
        }
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}
