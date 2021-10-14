package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorFactory;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Energy;

public class MissionImpossible implements SceneListener {


    public static class Factory implements ActorFactory {


        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {

            if (name == "energy") {
                return new Energy();
            }
            if (name.equals("ripley")) {
               return new Ripley();
            }

            return null;
        }
    }
}
