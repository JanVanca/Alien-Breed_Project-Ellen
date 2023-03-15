package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {

    private int perpetualReactorHeatingTemperature;

    public PerpetualReactorHeating(int perpetualReactorHeatingTemperature) {
        this.perpetualReactorHeatingTemperature = perpetualReactorHeatingTemperature;
    }

    @Override
    public void execute(float deltaTime) {
        if (getActor() != null) {
            getActor().increaseTemperature(perpetualReactorHeatingTemperature);
        }
    }
}
