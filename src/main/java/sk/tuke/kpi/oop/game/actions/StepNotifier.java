package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;

import java.util.List;

public class StepNotifier extends AbstractAction<Ripley> {

    @Override
    public void execute(float deltaTime) {
        Ripley ripley = getActor();
        Scene scene = ripley.getScene();

        List<Actor> allActors = scene.getActors();

        for (Actor actor : allActors) {
            if (actor instanceof Energy) {
                Energy energy = (Energy) actor;
                if (ripley.intersects(energy)) {
                    new Use<>(energy).scheduleFor(ripley);
                }
            }
            if (actor instanceof Ammo) {
                Ammo ammo = (Ammo) actor;
                if (ripley.intersects(ammo)) {
                    new Use<>(ammo).scheduleFor(ripley);
                }
            }

        }
    }
}
