package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

import java.util.Objects;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed{

    private Animation playerAnimation = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation playerDiedAnimation = new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
    private int speed;
    private Backpack backpack;
    private int ammo;
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);
    private Disposable disposable = null;
    private Health health;
    private Firearm firearm;


    public Ripley() {
        super("Ellen");
        setAnimation(playerAnimation);
        this.speed = 2;
        this.ammo = 100;
        this.backpack = new Backpack("Ripley's backpack", 10);
        this.health = new Health(100);
        this.firearm = new Gun(100);
        health.onExhaustion(() -> {
            setAnimation(playerDiedAnimation);
            Objects.requireNonNull(getScene()).getMessageBus().publish(RIPLEY_DIED, this);
            getScene().cancelActions(this);
        });
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void decreaseEnergy() {

        if (this.health.getActualHealth() <= 0) {
            this.setAnimation(playerDiedAnimation);
            getScene().getMessageBus().publish(RIPLEY_DIED, this);
        } else {
            disposable = new Loop<>(
                new ActionSequence<>(
                    new Invoke<>(() -> {
                        if (this.health.getActualHealth() <= 0) {
                            this.setAnimation(playerDiedAnimation);
                            getScene().getMessageBus().publish(RIPLEY_DIED, this);
                            return;
                        } else {
                            this.health.drain(5);
                        }
                    }),
                    new Wait<>(1)
                )
            ).scheduleFor(this);
        }
    }

    public Disposable stopDecreasingEnergy() {
        return disposable;
    }


    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        playerAnimation.setRotation(direction.getAngle());
        playerAnimation.play();

    }

    @Override
    public void stoppedMoving() {
        playerAnimation.stop();
    }

    @Override
    public Backpack getBackpack() {
        return backpack;
    }

    public void showRipleyState() {
        int windowHeight = Objects.requireNonNull(getScene()).getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        getScene().getGame().getOverlay().drawText("Energy: " + health.getActualHealth(), 120, yTextPos);
        getScene().getGame().getOverlay().drawText("Your Ammo " + this.getFirearm().getAmmo(), 280, yTextPos);
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public Firearm getFirearm() {
        return firearm;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        this.firearm = weapon;
    }
}
