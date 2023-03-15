package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import java.util.Random;

public class DefectiveLight extends Light implements Repairable {

    private Disposable disposable;



    private void changeStatus() {
        if (getRandomNumber() == 1) {
            toggle();
        }
    }

    private int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(20 - 0) + 0;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this. disposable = new Loop<>(new Invoke<>(this::changeStatus)).scheduleFor(this);
    }

    @Override
    public boolean repair() {
        if (disposable == null) {
            return false;
        }
        else {
            turnOn();
            disposable.dispose();
            this.disposable = new ActionSequence<>(new Wait<>(10),new Loop<>(new Invoke<>(this::changeStatus))).scheduleFor(this);
            return true;
        }

    }
}
