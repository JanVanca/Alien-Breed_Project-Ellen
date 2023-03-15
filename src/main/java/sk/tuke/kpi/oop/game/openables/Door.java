package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;

public class Door extends AbstractActor implements Openable, Usable<Actor> {


    private boolean isOpen;
    private Animation doorAnimation;
    private int tile1x;
    private int tile1y;
    private int tile2x;
    private int tile2y;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    public enum  Orientation { HORIZONTAL, VERTICAL }

    public Door(String name, Orientation orientation) {
        super(name);
        this.isOpen = false;
        this.tile1x = this.getPosX() / 16;
        this.tile1y = this.getPosY() / 16;

        if (orientation == Orientation.HORIZONTAL) {
            doorAnimation = new Animation("sprites/hdoor.png", 32, 16, 0.1f);
            setAnimation(doorAnimation);
            getAnimation().stop();
            this.tile2x = this.getPosX() / 16 + 1;
            this.tile2y = this.getPosX() / 16;
        }
        else if (orientation == Orientation.VERTICAL) {
            doorAnimation = new Animation("sprites/vdoor.png", 16, 32, 0.1f);
            setAnimation(doorAnimation);
            getAnimation().stop();
            this.tile2x = this.getPosX() / 16;
            this.tile2y = this.getPosX() / 16 + 1;
        }

    }

    @Override
    public void open() {
        if (isOpen == false) {
            isOpen = true;
            doorAnimation.setPlayMode(Animation.PlayMode.ONCE);
            getAnimation().play();
            updateTileMap();
            getScene().getMessageBus().publish(DOOR_OPENED, this);
        }

    }

    @Override
    public void close() {
        if (isOpen == true) {
            isOpen = false;
            doorAnimation.setPlayMode(Animation.PlayMode.ONCE_REVERSED);
            getAnimation().play();
            updateTileMap();
            getScene().getMessageBus().publish(DOOR_CLOSED, this);
        }
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void useWith(Actor actor) {
        if (isOpen())
            close();
        else
            open();
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    private void updateTileMap() {
        if (this.isOpen) {
            Objects.requireNonNull(getScene()).getMap().getTile(getPosX() / 16, getPosY() / 16).setType(MapTile.Type.CLEAR);
            //getScene().getMap().getTile(getPosX() / 16, getPosY() / 16 + 1).setType(MapTile.Type.CLEAR);

        }
        else {
            Objects.requireNonNull(getScene()).getMap().getTile(this.getPosX() /16, this.getPosY() / 16).setType(MapTile.Type.WALL);
            //getScene().getMap().getTile(this.getPosX() /16, this.getPosY() / 16+1 ).setType(MapTile.Type.WALL);
        }
    }


    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        updateTileMap();


    }
}
