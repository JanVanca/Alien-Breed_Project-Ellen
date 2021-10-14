package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Collectible;


public class KeeperController implements KeyboardListener {


    private Keeper<Collectible> ripley;

    public KeeperController(Keeper<Collectible> ripley) {
        this.ripley = ripley;
    }


    @Override
    public void keyPressed(@NotNull Input.Key key) {

        if (key == Input.Key.ENTER) {
            new Take().scheduleFor(ripley);
        }
        if (key == Input.Key.BACKSPACE) {
            new Drop().scheduleFor(ripley);
        }
        if (key == Input.Key.S) {
            new Shift().scheduleFor(ripley);
        }

    }
}
