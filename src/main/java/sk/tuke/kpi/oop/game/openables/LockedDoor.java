package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

public class LockedDoor extends Door {

    private boolean locked;

    public LockedDoor(String name, Orientation orientation) {
        super(name, orientation);
        locked = true;
    }

    public void lock() {
        if (locked == false) {
            locked = true;
            this.close();
        }

    }

    public void unlock() {
        if (locked == true) {
            locked = false;
            this.open();
        }

    }

    public boolean isLocked() {
        return locked;
    }

    public void useLock() {
        if (isLocked() == true) {
            unlock();
        }
        else {
            lock();
        }
    }

    @Override
    public void useWith(Actor actor) {
        if (!isLocked())
        super.useWith(actor);
    }

}

