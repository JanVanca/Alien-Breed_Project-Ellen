package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.*;

public class MovableController implements KeyboardListener {


    private Movable movable;
    private Move move;
    private final Set<Input.Key> Set = new HashSet<>();
    private final Set<Input.Key> UP = new HashSet<>(Collections.singletonList(Input.Key.UP));
    private final Set<Input.Key> DOWN = new HashSet<>(Collections.singletonList(Input.Key.DOWN));
    private final Set<Input.Key> RIGHT = new HashSet<>(Collections.singletonList(Input.Key.RIGHT));
    private final Set<Input.Key> LEFT = new HashSet<>(Collections.singletonList(Input.Key.LEFT));

    private final Set<Input.Key> NORTHEAST = new HashSet<>(Arrays.asList(Input.Key.UP, Input.Key.RIGHT));
    private final Set<Input.Key> NORTHWEST = new HashSet<>(Arrays.asList(Input.Key.UP, Input.Key.LEFT));
    private final Set<Input.Key> SOUTHWEST = new HashSet<>(Arrays.asList(Input.Key.DOWN, Input.Key.LEFT));
    private final Set<Input.Key> SOUTHEAST = new HashSet<>(Arrays.asList(Input.Key.DOWN, Input.Key.RIGHT));


    public MovableController(Movable movable) {
        this.movable = movable;

    }

    //private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
    //    Map.entry(Input.Key.UP, Direction.NORTH),
    //    Map.entry(Input.Key.BACK, Direction.SOUTH),
    //    Map.entry(Input.Key.RIGHT, Direction.EAST),
    //    Map.entry(Input.Key.LEFT, Direction.WEST)
    //);

    private final Map<Set<Input.Key>, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(UP, Direction.NORTH),
        Map.entry(RIGHT, Direction.EAST),
        Map.entry(DOWN, Direction.SOUTH),
        Map.entry(LEFT, Direction.WEST),
        Map.entry(SOUTHEAST, Direction.SOUTHEAST),
        Map.entry(SOUTHWEST, Direction.SOUTHWEST),
        Map.entry(NORTHEAST, Direction.NORTHEAST),
        Map.entry(NORTHWEST, Direction.NORTHWEST)

    );


    @Override
    public void keyReleased(@NotNull Input.Key key) {
        Set.remove(key);
        move();

    }



    @Override
    public void keyPressed(@NotNull Input.Key key) {
        Set.add(key);
        move();

    }


    private void move() {
        if (move != null) {
            move.stop();
        }

        if (keyDirectionMap.containsKey(Set)) {
            Direction direction = keyDirectionMap.get(Set);
            if (move != null) {
                move.stop();
            }

            move = new Move(direction, 9999);
            move.scheduleFor(movable);

        }
    }


}
