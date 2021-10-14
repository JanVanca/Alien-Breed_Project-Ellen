package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.awt.geom.Rectangle2D;
import java.util.List;

public class Teleport extends AbstractActor {

    Animation teleportAnimation = new Animation("sprites/lift.png", 48, 48, 0.1f, Animation.PlayMode.ONCE);

    Teleport teleport;
    Player player;

    public Teleport(Teleport teleport) {
        setAnimation(teleportAnimation);
        this.teleport = teleport;
    }

    public Teleport getDestination() {
        return teleport;
    }

    public void setDestination(Teleport destinationTeleport) {
        this.teleport = destinationTeleport;
    }

    public void teleportPlayer(Player player) {
        player.setPosition(teleport.getPosX(), teleport.getPosY());

    }

    //private void getTeleport() {
    //    Scene scene;
    //    scene = getScene();
    //    List<Actor> allActors = scene.getActors();

    //    for (Actor actor : allActors) {
    //        if (actor instanceof ChainBomb) {
    //            Teleport teleport = (Teleport) actor;
    //        }

    //    }

    //}


    public void actualize() {
        Rectangle2D.Float rectangle = new Rectangle2D.Float();
        if (player == null) {
            Scene scene = getScene();
            List<Actor> allActors = scene.getActors();

            for (Actor actor : allActors) {
                if (actor instanceof Player) {
                    player = (Player) actor;
                }
            }
        }
        rectangle.setRect((player.getPosX() + player.getHeight()), (player.getPosY() + player.getWidth()), player.getHeight(), player.getWidth());
        if (player != null && teleport != null && rectangle.intersects(getPosX(), getPosY(), getHeight(), getWidth())) {
            teleportPlayer(player);

        }

        //if (player.getPosX() == this.getPosX() && player.getPosY() == this.getPosY()) {

        //}

    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if (teleport != null) {
            new Loop<>(new Invoke<>(this::actualize)).scheduleFor(this);
        }
    }


}
