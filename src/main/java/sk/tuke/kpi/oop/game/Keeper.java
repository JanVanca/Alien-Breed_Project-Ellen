package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;
import sk.tuke.kpi.oop.game.items.Backpack;

public interface Keeper  <A extends Actor> extends Actor {

    ActorContainer<A> getBackpack();
}
