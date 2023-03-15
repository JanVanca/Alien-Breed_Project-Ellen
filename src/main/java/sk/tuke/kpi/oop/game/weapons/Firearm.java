package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {
    private int maxAmmo;
    private int actualAmmo;

    public Firearm(int maxAmmo, int actualAmmo) {
        this.maxAmmo = maxAmmo;
        this.actualAmmo = actualAmmo;
    }

    public Firearm(int initialAmmo) {
        this.actualAmmo = initialAmmo;
        this.maxAmmo = initialAmmo;
    }

    public int getAmmo() {
        return actualAmmo;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void reload(int newAmmo) {

        if (actualAmmo >= maxAmmo) {
            return;
        }

        if (actualAmmo + newAmmo <= maxAmmo) {
            actualAmmo = actualAmmo + newAmmo;
        } else {
            actualAmmo = maxAmmo;
        }
    }

    public Fireable fire(){
        if (actualAmmo == 0) {
            return null;
        }else {
            actualAmmo--;
            return createBullet();
        }

    }

    protected abstract Fireable createBullet();
}
