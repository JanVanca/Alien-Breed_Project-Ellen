package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Helicopter extends AbstractActor {

    private Player player;
    private Animation helicopterAnimation = new Animation("sprites/heli.png", 64, 64, 0.1f, Animation.PlayMode.LOOP_PINGPONG);

    public Helicopter() {
        setAnimation(helicopterAnimation);

    }

    public void helicopterAttackPlayer() {
        new Loop<>(new Invoke<>(this::searchAndDestroy)).scheduleFor(this);
    }

    public void searchAndDestroy() {
        this.player = getScene().getLastActorByType(Player.class);
        if (player.getPosX() < getPosX()) {
            setPosition(getPosX() -1, getPosY());
        }
        else if (player.getPosX() > getPosX()) {
            setPosition(getPosX() + 1, getPosY());
        }
        if (player.getPosY() < getPosY()) {
            setPosition(getPosX(), getPosY() - 1);
        }
        else if (player.getPosY() > getPosY()) {
            setPosition(getPosX(), getPosY() + 1);
        }

        if (player.intersects(this)) {
            player.setEnergy(player.getEnergy() -1);
        }
    }

}
