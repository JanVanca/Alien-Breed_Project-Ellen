package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.actions.StepNotifier;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;

import java.util.List;

public class FirstSteps implements SceneListener {


    private Ripley ripley;
    private Backpack backpack;

    @Override
   public void sceneInitialized(Scene scene) {
        this.ripley = new Ripley();
        scene.addActor(ripley, 50, 30);
        backpack = (Backpack) ripley.getBackpack();


        //Move move = new Move(Direction.EAST, 2.00f);
        //move.scheduleFor(ripley);

        MovableController movableController = new MovableController(ripley);
        scene.getInput().registerListener(movableController);

        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(keeperController);


        Energy energy = new Energy();
        scene.addActor(energy, 50, 50);

        Ammo ammo = new Ammo();
        scene.addActor(ammo, 250, 250);

        Hammer hammer = new Hammer();
        scene.addActor(hammer, 100, 100);

        Reactor reactor = new Reactor();
        scene.addActor(reactor, 150, 30);

       // new Use<>(energy).scheduleFor(ripley);
        //new Use<>(hammer).scheduleFor(reactor);


        new StepNotifier().scheduleFor(ripley);


        //scene.getGame().pushActorContainer(backpack);
        FireExtinguisher fireExtinguisher= new FireExtinguisher();
        Wrench wrench = new Wrench();
        Hammer hammer2 = new Hammer();
        backpack.add(hammer);
        backpack.add(wrench);
        backpack.add(fireExtinguisher);
        backpack.shift();

   }



    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;

        int energy = ripley.getEnergy();
        int ammo = ripley.getAmmo();
        String energyText = "Energy: " + energy;
        String ammoText = "Ammo: " + ammo;

        scene.getGame().getOverlay().drawText(energyText, 100, yTextPos);
        scene.getGame().getOverlay().drawText(ammoText, 230, yTextPos);
        scene.getGame().pushActorContainer(backpack);
    }
}
