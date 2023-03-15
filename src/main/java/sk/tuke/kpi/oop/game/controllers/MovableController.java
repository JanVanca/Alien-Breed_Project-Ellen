package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.*;

public class MovableController implements KeyboardListener {

    private final Set<Input.Key> UP = new HashSet<>(Collections.singletonList(Input.Key.UP));
    private final Set<Input.Key> DOWN = new HashSet<>(Collections.singletonList(Input.Key.DOWN));
    private final Set<Input.Key> RIGHT = new HashSet<>(Collections.singletonList(Input.Key.RIGHT));
    private final Set<Input.Key> LEFT = new HashSet<>(Collections.singletonList(Input.Key.LEFT));
    private final Set<Input.Key> NORTHEAST = new HashSet<>(Arrays.asList(Input.Key.UP, Input.Key.RIGHT));
    private final Set<Input.Key> NORTHWEST = new HashSet<>(Arrays.asList(Input.Key.UP, Input.Key.LEFT));
    private final Set<Input.Key> SOUTHWEST = new HashSet<>(Arrays.asList(Input.Key.DOWN, Input.Key.LEFT));
    private final Set<Input.Key> SOUTHEAST = new HashSet<>(Arrays.asList(Input.Key.DOWN, Input.Key.RIGHT));
    private Movable movable;
    private Move move;
    private Set<Input.Key> pressedKeys;
    private final Map<Set<Input.Key>, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(UP, Direction.NORTH),
        Map.entry(DOWN, Direction.SOUTH),
        Map.entry(LEFT, Direction.WEST),
        Map.entry(RIGHT, Direction.EAST),
        Map.entry(SOUTHEAST, Direction.SOUTHEAST),
        Map.entry(SOUTHWEST, Direction.SOUTHWEST),
        Map.entry(NORTHEAST, Direction.NORTHEAST),
        Map.entry(NORTHWEST, Direction.NORTHWEST)
    );

    public MovableController(Movable movable) {
        this.movable = movable;
        pressedKeys= new HashSet<>();
    }

    @Override
    public void keyPressed(Input.@NotNull Key key) {
        pressedKeys.add(key);
        move();

    }

    @Override
    public void keyReleased(Input.@NotNull Key key) {
        pressedKeys.remove(key);
        move();
    }

    private void move() {
        if (move != null) {
            move.stop();
        }

        if (keyDirectionMap.containsKey(pressedKeys)) {
            Direction direction = keyDirectionMap.get(pressedKeys);
            if (move != null) {
                move.stop();
            }

            move = new Move(direction, Float.MAX_VALUE);
            move.scheduleFor(movable);

        }
    }

}
