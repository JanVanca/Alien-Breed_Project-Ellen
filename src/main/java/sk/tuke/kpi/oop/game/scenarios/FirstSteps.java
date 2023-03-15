package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.actions.StepNotifier;

public class FirstSteps implements SceneListener {

    private Ripley ripley;
    private Backpack backpack;

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        SceneListener.super.sceneInitialized(scene);
        this.ripley = new Ripley();
        scene.addActor(ripley, 0, 0);
        this.backpack = ripley.getBackpack();

        MovableController movableController = new MovableController(ripley);
        scene.getInput().registerListener(movableController);

        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(keeperController);

        Energy energy = new Energy();
        scene.addActor(energy, 50, 150);

        Ammo ammo = new Ammo();
        scene.addActor(ammo, 250, 250);

        new StepNotifier().scheduleFor(ripley);

        FireExtinguisher fireExtinguisher= new FireExtinguisher();
        Wrench wrench = new Wrench();
        Hammer hammer =  new Hammer();

        Hammer hammer2 =  new Hammer();
        scene.addActor(hammer2, 50, 180);



        backpack.add(hammer);
        backpack.add(wrench);
        backpack.add(fireExtinguisher);
        backpack.shift();

    }


    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;

        int energy = ripley.getHealth().getActualHealth();
        int ammo = ripley.getAmmo();
        String energyText = "Energy: " + energy;
        String ammoText = "Ammo: " + ammo;

        scene.getGame().getOverlay().drawText(energyText, 100, yTextPos);
        scene.getGame().getOverlay().drawText(ammoText, 230, yTextPos);
        scene.getGame().pushActorContainer(backpack);


    }
}
