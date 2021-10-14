package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.concurrent.TimeUnit;

public class TimeBomb extends AbstractActor {

    Animation bombAnimation = new Animation("sprites/bomb.png", 16, 16, 0.1f, Animation.PlayMode.LOOP);
    Animation bombActivateAnimation = new Animation("sprites/bomb_activated.png", 16, 16, 0.1f, Animation.PlayMode.LOOP);
    Animation explodeAnimation = new Animation("sprites/small_explosion.png", 16, 16, 0.1f, Animation.PlayMode.ONCE);
    private float timer;
    public boolean isActivated;

    public TimeBomb(float timer) {
        setAnimation(bombAnimation);
        this.timer = timer;
        isActivated = false;
    }

    public void activate() throws InterruptedException {
        isActivated = true;
        setAnimation(bombActivateAnimation);

        if (isActivated == true) {
            while (timer > 0) {
                timer = timer -1;
                TimeUnit.SECONDS.sleep(1);
            }
            if (timer == 0) {
                setAnimation(explodeAnimation);
                TimeUnit.SECONDS.sleep(1);
               // Scene scene = getScene();
                //scene.removeActor(this);
                new When<>(
                       () -> timer == 0,
                        new Invoke<>(() -> this.deleteFromScene())
                    ).scheduleFor(this);
            }
        }


    }

    private void deleteFromScene() {
        Scene scene = getScene();
        scene.removeActor(this);
    }

}
