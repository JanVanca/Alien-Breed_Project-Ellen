package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.behaviours.Observing;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;

public class EscapeRoom implements SceneListener {

    private Ripley ellen;
    private boolean won;
    private boolean alive;

    public static class Factory implements ActorFactory {

        @Override
        public Actor create(@Nullable String type, @Nullable String name) {
            if (name != null) {
                switch (name) {
                    case "ellen":
                        return new Ripley();
                    case "energy":
                        return new Energy();
                    case "ammo":
                        return new Ammo();
                    case "alien":
                        switch (type) {
                            case "running":
                                return new Alien(100, new RandomlyMoving());
                            case "waiting1":
                                return new Alien(100, new Observing<>(Door.DOOR_OPENED, actor -> actor.getName().endsWith("front door")
                                    , new RandomlyMoving()));
                            case "waiting2":
                                return new Alien(100, new Observing<>(Door.DOOR_OPENED, actor -> actor.getName().endsWith("back door")
                                    , new RandomlyMoving()));
                            default:
                                return new Alien();
                        }
                    case "alien mother":
                        return new AlienMother(200, new RandomlyMoving());
                    case "front door":
                        return new Door("front door", Door.Orientation.VERTICAL);
                    case "back door":
                        return new Door("back door", Door.Orientation.HORIZONTAL);
                    case "exit door":
                        if (type.equals("vertical")) {
                            return new Door("exit door", Door.Orientation.VERTICAL);
                        }
                        if (type.equals("horizontal")) {
                            return new Door("exit door", Door.Orientation.HORIZONTAL);
                        }
                        return null;
                    default:
                        return null;
                }
            }
            return null;
        }
    }

    @Override
    public void sceneCreated(@NotNull Scene scene) {
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ellen = scene.getFirstActorByType(Ripley.class);
        won = false;
        alive = true;
        scene.follow(ellen);
        Disposable movableController = scene.getInput().registerListener(new MovableController(ellen));
        Disposable keeperController = scene.getInput().registerListener(new KeeperController(ellen));
        Disposable shooterCon = scene.getInput().registerListener(new ShooterController(ellen));
        scene.getGame().pushActorContainer(ellen.getBackpack());


        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> movableController.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> keeperController.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> shooterCon.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> setAlive());
        System.out.println("ziva" + alive);



        scene.getMessageBus().subscribe(Door.DOOR_OPENED, door -> {
            if (door.getName().equals("exit door")) {
                this.won = true;

            }
        });

    }

    public void setAlive() {
        this.alive = false;
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        if (ellen != null) {
            ellen.showRipleyState();
            if(won == true) {
                scene.getOverlay().drawText("Well done", 0, 0);
            }
            if (alive == false) {
                scene.getOverlay().drawText("Ripley Died!", 0, 0);
            }
        } else {
            return;
        }
    }

}

