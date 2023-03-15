package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm{


    public Gun(int maxAmmo, int actualAmmo) {
        super(maxAmmo, actualAmmo);
    }

    public Gun(int initialAmmo) {
        super(initialAmmo);
    }

    @Override
    protected Fireable createBullet() {
        return new Bullet();
    }
}
