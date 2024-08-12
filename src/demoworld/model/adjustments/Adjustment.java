package demoworld.model.adjustments;

import demoworld.model.Adjuster;
import demoworld.model.Character;

/**
 * Adjustment provides a base implementation for adjusting and unadjusting
 * a characters state. Implements the Adjuster interface
 */
public abstract class Adjustment implements Adjuster {

    /**
     * Constructs an adjustment
     */
    public Adjustment(){}

    /**
     * Adjusts the given characters state. Implementations will define how the character is adjusted
     * @param character the Character to adjust
     * @return the adjusted Character
     */
    public abstract Character adjust(Character character);

    /**
     * Undoes the adjustments made to the given characters state
     * (i.e. if 1 was added to something before, 1 would be subtracted now).
     * Implementations will define how the character is unadjusted
     * @param character the Character to unadjust
     * @return the unadjusted Character
     */
    public abstract Character unadjust(Character character);
}
