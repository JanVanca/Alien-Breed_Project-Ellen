package sk.tuke.kpi.oop.game;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.items.Repairable;

public class DefectiveLight extends Light implements Repairable {

    int time = 0;

    public void flashingLight() {
        time = (int) (Math.random()*20);
        if (time == 1) {
            toggle();
        }
    }


    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::flashingLight)).scheduleFor(this);
    }

    @Override
    public boolean repair() {
        return false;
    }
}
