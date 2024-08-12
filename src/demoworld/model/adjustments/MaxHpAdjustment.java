package demoworld.model.adjustments;

import demoworld.model.Adjuster;
import demoworld.model.Character;
import demoworld.model.Hitpoints;

/**
 * This is an implementation for adjusting the maximum Hitpoints of a Character.
 */
public class MaxHpAdjustment extends Adjustment implements Adjuster {

    private int value;

    /**
     * Constructs a MaxHpAdjustment with the specified value
     * @param value the value to adjust the hitpoints max with
     */
    public MaxHpAdjustment(int value){
        this.value = value;
    }

    /**
     * Adjusts the hitpoints max of the given character
     * @param character the Character to adjust
     * @return the adjusted Character
     */
    public Character adjust(Character character){
        Hitpoints hp = (Hitpoints) character.getStatByName("hitpoints");
        if (hp != null) {
            hp.addToMaxBase(value);
        }
        return character;
    }

    /**
     * Undoes the adjustment of the Hitpoints max for the given Character
     * @param character the Character to unadjust
     * @return the adjusted Character
     */
    public Character unadjust(Character character){
        Hitpoints hp = (Hitpoints) character.getStatByName("hitpoints");
        if (hp != null) {
            hp.addToMaxBase(-value);
        }
        return character;
    }
}
