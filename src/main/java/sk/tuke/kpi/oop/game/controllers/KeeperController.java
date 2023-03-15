package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;

public class KeeperController implements KeyboardListener {
    private Keeper ripley;

    public KeeperController(Keeper ripley) {
        this.ripley = ripley;
    }


    @Override
    public void keyPressed(@NotNull Input.Key key) {

        if (key == Input.Key.ENTER) {
            new Take<>().scheduleFor(ripley);
        }
        if (key == Input.Key.BACKSPACE) {
            new Drop<>().scheduleFor(ripley);
        }
        if (key == Input.Key.S) {
            new Shift<>().scheduleFor(ripley);
        }
        if (key == Input.Key.U) {
            useAction();
        }
        if (key == Input.Key.B) {
            useBAction();
        }

    }

    private void useAction() {
        Usable<?> usable = ripley.getScene().getActors().stream().filter(Usable.class::isInstance).filter(ripley::intersects).map(Usable.class::cast).findFirst().orElse(null);
        if (usable != null) {
            new Use<>(usable).scheduleForIntersectingWith(ripley);
        }
    }

    private void useBAction() {
        if (ripley.getBackpack().peek() instanceof Usable) {
            Use<?> use = new Use<>((Usable<?>)ripley.getBackpack().peek());
            use.scheduleForIntersectingWith(ripley);
        }

    }


}
