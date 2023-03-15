package sk.tuke.kpi.oop.game.characters;

import java.util.HashSet;
import java.util.Set;

public class Health {

    private int actualHealth;
    private int maxHealth;
    private Set<ExhaustionEffect> exhaustionEffects;

    public Health(int actualHealth, int maxHealth) {
        this.actualHealth = actualHealth;
        this.maxHealth = maxHealth;
        this.exhaustionEffects = new HashSet<>();
    }

    public Health(int initialValue) {
        this.actualHealth = initialValue;
        this.maxHealth = initialValue;
        this.exhaustionEffects = new HashSet<>();
    }

    public int getActualHealth() {
        return actualHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setActualHealth(int actualHealth) {
        this.actualHealth = actualHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void refill(int amount)  {
        if (actualHealth + amount < maxHealth) {
            actualHealth = actualHealth + amount;
        }
        else {
            restore();
        }
    }

    public void restore() {
        actualHealth = maxHealth;
    }

    public void drain(int amount) {
        if (actualHealth > amount) {
            actualHealth = actualHealth - amount;
        }
        else {
            exhaust();
        }
    }

    public void exhaust() {
        if (actualHealth != 0) {
            actualHealth = 0;

            if (exhaustionEffects != null) {
                for (ExhaustionEffect effect : exhaustionEffects) {
                    effect.apply();
                }
            }
        }
    }

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public void onExhaustion(ExhaustionEffect effect) {
        if (exhaustionEffects!=null)
            this.exhaustionEffects.add(effect);

    }


}
