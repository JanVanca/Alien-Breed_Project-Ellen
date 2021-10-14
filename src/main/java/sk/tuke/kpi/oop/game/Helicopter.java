package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Point;
import sk.tuke.kpi.oop.game.actions.AttackPlayerAction;

public class Helicopter extends AbstractActor {

    Animation helicopter = new Animation("sprites/heli.png", 64, 64, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    Player player;


    public Helicopter(Player player) {
        setAnimation(helicopter);
        this.player = player;

    }


    public void searchAndDestroy() {
        if (isCloseEnought() == true) {
            player.setEnergy(player.getEnergy()-1);
        }
    }


    public boolean isCloseEnought() {
        Point playerPosition = player.getPosition();
        Point helicopterPosition = this.getPosition();

        return playerPosition.equals(helicopterPosition);

    }

    public void move() {
        if (getPosX() - player.getPosX() < 0) { //hrac je vpravo odomna
            this.setPosition(getPosX() + 1, this.getPosY());
        }
        else if (getPosX() - player.getPosX() > 0) { // hrac je nalavo odomna
            this.setPosition(getPosX() - 1, this.getPosY());
        }
        if (getPosY() - player.getPosY() < 0) { // hrac je nadomnou
            this.setPosition(getPosX(), this.getPosY() + 1);
        }
        else if (getPosY() - player.getPosY() > 0) { // hrac je podomnou
            this.setPosition(getPosX(), this.getPosY() -1);
        }

    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::move)).scheduleFor(this);

       AttackPlayerAction player = new AttackPlayerAction();
       scene.scheduleAction(player, this);

    }

}
