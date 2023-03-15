package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.Objects;

public class MissionImpossible implements SceneListener {

    private Ripley ellen;


    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ellen = scene.getFirstActorByType(Ripley.class);
        scene.follow(ellen);
        Disposable movableController = scene.getInput().registerListener(new MovableController(ellen));
        Disposable keeperController = scene.getInput().registerListener(new KeeperController(ellen));
        scene.getGame().pushActorContainer(ellen.getBackpack());

        scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Ripley) -> ellen.decreaseEnergy());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> movableController.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> keeperController.dispose());
        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, (Ripley) -> ellen.stopDecreasingEnergy().dispose());


    }

    public static class Factory implements ActorFactory {

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            if (name != null) {
                if (Objects.equals(name, "ellen")) {
                    return new Ripley();
                } else if (Objects.equals(name, "energy")) {
                    return new Energy();
                } else if (Objects.equals(name, "door")) {
                    return new LockedDoor("door,", Door.Orientation.VERTICAL);
                } else if (Objects.equals(name, "access card")) {
                    return new AccessCard();
                } else if (Objects.equals(name, "ventilator")) {
                    return new Ventilator();
                } else if (Objects.equals(name, "locker")) {
                    return new Locker();
                }
            }
            return null;
        }
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        if (ellen != null) {
            ellen.showRipleyState();
        }
        else {
            return;
        }

    }


}
