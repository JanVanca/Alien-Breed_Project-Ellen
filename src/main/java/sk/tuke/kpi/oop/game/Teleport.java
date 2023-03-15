package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.awt.geom.Rectangle2D;

public class Teleport extends AbstractActor {

    private Animation teleportAnimation = new Animation("sprites/lift.png");
    private Teleport destinationTeleport;
    private boolean wasTeleported;

    public Teleport(Teleport destinationTeleport) {
        setAnimation(teleportAnimation);
        this.destinationTeleport = destinationTeleport;
        this.wasTeleported = false;
    }

    public Teleport getDestination(Teleport destinationTeleport) {
        return destinationTeleport;
    }

    public void setDestination(Teleport destinationTeleport) {
        this.destinationTeleport = destinationTeleport;
    }

    public void teleportPlayer(Player player) {
        if (destinationTeleport != null) {
            player.setPosition(destinationTeleport.getPosX(), destinationTeleport.getPosY());
            destinationTeleport.wasTeleported = true;
        }

    }

    private void teleport() {

        Player player = getScene().getLastActorByType(Player.class);
        int playerCenterPositionX = player.getPosX() + player.getWidth() / 2;
        int playerCenterPositionY = player.getPosY() + player.getHeight() / 2;
        int teleportCenterPositionX = getPosX() + getWidth() / 2;
        int teleportCenterPositionY = getPosY() + getHeight() / 2;
        Rectangle2D.Float playerArea = new Rectangle2D.Float();
        playerArea.setRect(playerCenterPositionX - 5, playerCenterPositionY + 5, 5, 5);
        Rectangle2D.Float teleportArea = new Rectangle2D.Float(teleportCenterPositionX - 5, teleportCenterPositionY + 5, 5, 5);
        if (wasTeleported == false && playerArea.intersects(teleportArea)) {
            teleportPlayer(player);
        }

    }

    private void setWasTeleported() {
        if (destinationTeleport != null) {
            Player player = getScene().getLastActorByType(Player.class);
            int playerCenterPositionX = player.getPosX() + player.getWidth() / 2;
            int playerCenterPositionY = player.getPosY() + player.getHeight() / 2;
            Rectangle2D.Float playerArea = new Rectangle2D.Float();
            playerArea.setRect(playerCenterPositionX - 5, playerCenterPositionY + 5, 5, 5);
            Rectangle2D.Float teleportArea = new Rectangle2D.Float(destinationTeleport.getPosX(), destinationTeleport.getPosY(), getWidth(), getHeight());
            if (!teleportArea.intersects(playerArea)) {
                destinationTeleport.wasTeleported = false;
            }
        }
    }


    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        Player player = getScene().getLastActorByType(Player.class);
        new Loop<>(new Invoke<>(this::teleport)).scheduleFor(player);
        new Loop<>(new Invoke<>(this::setWasTeleported)).scheduleFor(player);
    }

}
