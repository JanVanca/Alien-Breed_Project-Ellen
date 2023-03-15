package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.*;

public class Backpack implements ActorContainer<Collectible> {

    private String name;
    private int capacity;

    private List<Collectible> items;

    public Backpack(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    @Override
    public @NotNull List<Collectible> getContent() {
        return List.copyOf(items);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
       return items.size();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void add(@NotNull Collectible item) {
        if (items.size() < capacity) {
            items.add(item);
        }
        else {
            throw new IllegalStateException(name + " is full");
        }
    }

    @Override
    public void remove(@NotNull Collectible item) {
        if (items != null) {
            items.remove(item);
        }
    }

    @Override
    public @Nullable Collectible peek() {
        if (items != null && items.size() > 0) {
            return items.get(items.size()-1);
        }
        return null;
    }

    @Override
    public void shift() {
        Collections.rotate(items, 1);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
       return items.iterator();
    }
}
