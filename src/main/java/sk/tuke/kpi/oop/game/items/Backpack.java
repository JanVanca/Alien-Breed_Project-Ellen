package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.*;

public class Backpack implements ActorContainer<Collectible> {

    private String name;
    private int capacity;
    private int size;
    private List<Collectible> items;

    public Backpack(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.items = new ArrayList<>();

    }

    @Override
    public @NotNull List<Collectible> getContent() {
        return items;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void add(@NotNull Collectible actor) {
        int capacity = 0;
        if (capacity >= this.capacity) {
            throw new IllegalStateException(name + "is full");
        }
        else {
            items.add(actor);
            capacity++;
        }
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        if (items != null) {
            items.remove(actor);
        }


    }

    @Nullable
    @Override
    public Collectible peek() {
        if (items != null && items.size() > 0) {
           return items.get(items.size()-1);
            }
        return null;
    }

    @Override
    public void shift() {
        for (int i = 0; i < items.size()-1; i++) {
            Collections.swap(items, i, i+1);
        }
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {

        for (Collectible item : items) {
            // pouzitie predmetu (item) z batohu

        }
        return items.iterator();
    }
}
