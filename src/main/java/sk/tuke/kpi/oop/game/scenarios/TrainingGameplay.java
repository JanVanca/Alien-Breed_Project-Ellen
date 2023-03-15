package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.gamelib.map.MapMarker;
import sk.tuke.kpi.oop.game.*;
import sk.tuke.kpi.oop.game.items.FireExtinguisher;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Mjolnir;

import java.util.Map;

public class TrainingGameplay extends Scenario {

    @Override
    public void setupPlay(@NotNull Scene scene) {
        Reactor reactor = new Reactor();
        Reactor reactor1 = new Reactor();
        Cooler cooler = new Cooler(reactor);
        Cooler cooler1 = new Cooler(reactor1);
        Cooler cooler2 = new Cooler(reactor1);
        SmartCooler smartCooler = new SmartCooler(reactor1);
        SmartCooler smartCooler1 = new SmartCooler(reactor);
        Computer computer = new Computer();
        Computer computer1 = new Computer();
        Hammer hammer = new Hammer();
        Hammer hammer1 = new Hammer();
        Mjolnir mjolnir = new Mjolnir();
        Mjolnir mjolnir1 = new Mjolnir();
        PowerSwitch powerSwitch = new PowerSwitch(reactor);
        PowerSwitch powerSwitch1 = new PowerSwitch(reactor1);
        Light light = new Light();
        DefectiveLight defectiveLight = new DefectiveLight();
        FireExtinguisher fireExtinguisher = new FireExtinguisher();
        FireExtinguisher fireExtinguisher1 = new FireExtinguisher();
        Helicopter helicopter = new Helicopter();

        addReactors(scene, reactor, reactor1);

        addComputer(scene, computer, reactor);

        addCoolers(scene, cooler, cooler1, cooler2);

        addHammer(scene, reactor, hammer, 114, 240);
        addHammer(scene, reactor1, hammer1, 114, 177);

        addMjolnir(scene, mjolnir, reactor, 178, 367);
        addMjolnir(scene, mjolnir1, reactor1, 178, 32);

        addController(scene, powerSwitch, 177, 193);
        addController(scene, powerSwitch1, 177, 225);

        addFireExtinguisher(scene, fireExtinguisher, reactor, 250, 169);
        addFireExtinguisher(scene, fireExtinguisher1, reactor1, 250, 250);

        addLight(scene, light, reactor, 40, 208);

        addDefectiveLight(scene, defectiveLight, reactor1, 300, 208);

        addHelicopter(scene, helicopter, 140, 40);

    }

    private void addReactors(@NotNull Scene scene, Reactor reactor, Reactor reactor1) {
        Map<String, MapMarker> markers = scene.getMap().getMarkers();
        MapMarker reactorArea0 = markers.get("reactor-area-1");
        scene.addActor(reactor, reactorArea0.getPosX(), reactorArea0.getPosY());
        MapMarker reactorArea1 = markers.get("reactor-area-2");
        scene.addActor(reactor1, reactorArea1.getPosX(), reactorArea1.getPosY());
        reactor.turnOn();
        reactor1.turnOn();
    }

    private void addComputer(@NotNull Scene scene, Computer computer, @NotNull Reactor reactor) {
        Map<String, MapMarker> markers = scene.getMap().getMarkers();
        MapMarker computerArea = markers.get("computer-area");
        scene.addActor(computer, computerArea.getPosX(), computerArea.getPosY());
        reactor.addDevice(computer);
    }

    private void addCoolers(Scene scene, Cooler cooler, Cooler cooler1, Cooler cooler2) {
        Map<String, MapMarker> markers = scene.getMap().getMarkers();
        MapMarker coolerArea = markers.get("cooler-area-1");
        scene.addActor(cooler, coolerArea.getPosX(), coolerArea.getPosY());
        MapMarker coolerArea1 = markers.get("cooler-area-2");
        scene.addActor(cooler1, coolerArea1.getPosX(), coolerArea1.getPosY());
        MapMarker coolerArea2 = markers.get("cooler-area-3");
        scene.addActor(cooler2, coolerArea2.getPosX(), coolerArea2.getPosY());
        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(cooler::turnOn),
            new Invoke<>(cooler1::turnOn),
            new Invoke<>(cooler2::turnOn)
        ).scheduleFor(cooler);
    }

    private void addHammer(@NotNull Scene scene, Reactor reactor, Hammer hammer, int posX, int posY) {
        scene.addActor(hammer, posX, posY);
        new When<>(
            () -> reactor.getTemperature() >= 3000,
            new Invoke<>(() -> reactor.repair())
        ).scheduleFor(reactor);
    }

    private void addMjolnir(@NotNull Scene scene, Mjolnir mjolnir, Reactor reactor, int posX, int posY) {
        scene.addActor(mjolnir, posX, posY);
        new When<>(
            () -> reactor.getTemperature() >= 5000,
            new Invoke<>(() -> reactor.repair(mjolnir))
        ).scheduleFor(reactor);
    }

    private void addController(@NotNull Scene scene, PowerSwitch powerSwitch, int posX, int posY) {
        scene.addActor(powerSwitch, posX, posY);

    }

    private void addLight(@NotNull Scene scene, Light light, @NotNull Reactor reactor, int posX, int posY) {
        scene.addActor(light, posX, posY);
        reactor.addDevice(light);
        light.toggle();
    }

    private void addDefectiveLight(@NotNull Scene scene, DefectiveLight defectiveLight, @NotNull Reactor reactor, int posX, int posY) {
        scene.addActor(defectiveLight, posX, posY);
        reactor.addDevice(defectiveLight);
    }

    private void addSmartCooler(@NotNull Scene scene, SmartCooler smartCooler, int posX, int posY) {
        scene.addActor(smartCooler, posX, posY);
    }

    private void addFireExtinguisher(@NotNull Scene scene, FireExtinguisher fireExtinguisher, Reactor reactor, int posX, int posY) {
        scene.addActor(fireExtinguisher, posX, posY);
        new When<>(
            () -> reactor.getDamage() == 50,
            new Invoke<>(() -> reactor.extinguish())
        ).scheduleFor(reactor);
    }

    private void addHelicopter(Scene scene, Helicopter helicopter, int posX, int posY) {
        scene.addActor(helicopter, posX, posY);
    }

}
