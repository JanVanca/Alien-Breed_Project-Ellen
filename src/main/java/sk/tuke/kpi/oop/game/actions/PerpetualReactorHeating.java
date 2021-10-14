package sk.tuke.kpi.oop.game.actions;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {

    int perpetualReactorHeatingTemperature;

    public PerpetualReactorHeating(int value) {
        perpetualReactorHeatingTemperature = value;

    }

    @Override
    public void execute(float deltaTime) {
        Reactor reactor = getActor();

        reactor.increaseTemperature(perpetualReactorHeatingTemperature);
    }


}
