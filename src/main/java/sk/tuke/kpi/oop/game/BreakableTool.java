package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.oop.game.items.Usable;

public abstract class BreakableTool<T extends Actor> extends AbstractActor implements Usable<T> {

    int remainingUses;


   public BreakableTool(int remainingUses) {
        this.remainingUses = remainingUses;
    }

    @Override
    public void useWith(T actor) {
        remainingUses = remainingUses - 1;
        if (remainingUses == 0) {
            Scene remainingUsesScene = getScene();
            remainingUsesScene.removeActor(this);
        }
    }

     public int getRemainingUses() {
         return remainingUses;
     }
 }
