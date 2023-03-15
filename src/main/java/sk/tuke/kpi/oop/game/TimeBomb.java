package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {

    private Animation bombAnimation = new Animation("sprites/bomb.png");
    private Animation bombActivatedAnimation = new Animation("sprites/bomb_activated.png", 16, 16, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation bombExplodedAnimation = new Animation("sprites/small_explosion.png", 16, 16, 0.2f, Animation.PlayMode.ONCE);
    private float time;
    private boolean activated;
    private boolean isExploded;

    public TimeBomb(float time) {
        setAnimation(bombAnimation);
        this.time = time;
        this.activated = false;
        this.isExploded = false;


    }

    public void activate() {
        if (activated == false) {
            activated = true;
            setAnimation(bombActivatedAnimation);
            new ActionSequence<>(new Wait<>(time), new Invoke<>((this::explode))).scheduleFor(this);
            new When<>(() -> isExploded, new Invoke<>(() -> this.remove())).scheduleFor(this);

        } else {
            return;
        }
    }

    public boolean isActivated() {
        return activated;
    }

    private void explode() {
        this.isExploded = true;
        setAnimation(bombExplodedAnimation);
    }

    private void remove() {
        getScene().removeActor(this);
    }


}
