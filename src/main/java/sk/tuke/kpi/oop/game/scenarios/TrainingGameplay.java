package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.gamelib.map.MapMarker;
import sk.tuke.kpi.oop.game.Computer;
import sk.tuke.kpi.oop.game.Cooler;
import sk.tuke.kpi.oop.game.Reactor;

import java.util.Map;

public class TrainingGameplay extends Scenario {

    @Override
    public void setupPlay(@NotNull Scene scene) {
        Reactor reactor = new Reactor();
        Map<String, MapMarker> markers = scene.getMap().getMarkers();
        MapMarker reactorArea1 = markers.get("reactor-area-1");
        scene.addActor(reactor, reactorArea1.getPosX(), reactorArea1.getPosY());
        reactor.turnOn();

        Reactor reactor1 = new Reactor();
        Map<String, MapMarker> markers2 = scene.getMap().getMarkers();
        MapMarker reactorArea2 = markers2.get("reactor-area-2");
        scene.addActor(reactor1, reactorArea2.getPosX(), reactorArea2.getPosY());
        reactor1.turnOn();




        Cooler cooler = new Cooler(reactor);
        Map<String, MapMarker> makers3 = scene.getMap().getMarkers();
        MapMarker coolerArea1 = makers3.get("cooler-area-1");
        scene.addActor(cooler, coolerArea1.getPosX(), coolerArea1.getPosY());
        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(cooler::turnOn)
        ).scheduleFor(cooler);

        Cooler cooler1 = new Cooler(reactor1);
        Map<String, MapMarker> makers4 = scene.getMap().getMarkers();
        MapMarker coolerArea2 = makers4.get("cooler-area-2");
        scene.addActor(cooler1, coolerArea2.getPosX(), coolerArea2.getPosY());
        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(cooler1::turnOn)
        ).scheduleFor(cooler1);

        Cooler cooler2 = new Cooler(reactor1);
        Map<String, MapMarker> makers5 = scene.getMap().getMarkers();
        MapMarker coolerArea3 = makers5.get("cooler-area-3");
        scene.addActor(cooler2, coolerArea3.getPosX(), coolerArea3.getPosY());
        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(cooler2::turnOn)
        ).scheduleFor(cooler2);

        Computer computer = new Computer();
        Map<String, MapMarker> makers6 = scene.getMap().getMarkers();
        MapMarker computerArea = makers6.get("computer-area");
        scene.addActor(computer, computerArea.getPosX(), computerArea.getPosY());



        //Hammer hammer = new Hammer();
        //scene.addActor(hammer, 64, 64);
        //new When<>(
        //    () -> reactor.getTemperature() >= 3000,
        //    new Invoke<>(() -> reactor.repair(hammer))
        //).scheduleFor(reactor);

    }


}
