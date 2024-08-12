package demoworld.model.adjustments;

import demoworld.model.Adjuster;
import demoworld.model.Character;
import demoworld.model.Experience;

/**
 * This is an implementation for adjusting the maximum Experience of a Character
 */
public class MaxXpAdjustment extends Adjustment implements Adjuster {

    private int value;

    /**
     * This is the constructor for the MaxXpAdjustment it takes a value indicating how
     * much it should alter the max of Experience
     * @param value indicates how much to alter the maximum experience required to achieve a level up
     */
    public MaxXpAdjustment(int value){
        this.value = value;
    }

    /**
     * Adjusts the Experience max of the given character
     * @param character the Character to adjust
     * @return the adjusted Character
     */
    public Character adjust(Character character){
        Experience experience = character.getExperience();
        experience.setMax(experience.max() + value);
        return character;
    }

    /**
     * Undoes the adjustment of the Experience max for the given character
     * @param character the Character to unadjust
     * @return the adjusted Character
     */
    public Character unadjust(Character character){
        Experience experience = character.getExperience();
        experience.setMax(experience.max() - value);
        return character;
    }
}
