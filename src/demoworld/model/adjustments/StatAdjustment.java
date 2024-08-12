package demoworld.model.adjustments;

import demoworld.model.Adjuster;
import demoworld.model.Character;
import demoworld.model.Stat;

/**
 * This is an implementation for adjusting a target Stat Stat of a Character
 */
public class StatAdjustment extends Adjustment implements Adjuster {
    private String statKey;
    private int value;

    /**
     * Constructs a Stat Adjustment with a string indicating the stat it is
     * intended to adjust and a value indicating by how much
     * @param stat the key used to indicate which stat it should be used on
     * @param value the value indicating how much the targeted stat should be adjusted by
     */
    public StatAdjustment(String stat,
                          int value){
        this.statKey = stat;
        this.value = value;
    }

    /**
     * Gets the statKey assigned to this StatAdjustment
     * @return the statKey assigned to this StatAdjustment
     */
    public String getStatKey(){
        return statKey;
    }

    /**
     * Gets the value assigned to the statAdjustment
     * @return the value assigned to the statAdjustment
     */
    public int getValue(){
        return value;
    }

    /**
     * Adjusts the target Stat's current modifier using the given value
     * @param character the Character to adjust
     * @return the adjusted Character
     */
    public Character adjust(Character character){
        Stat stat = character.getStatByName(statKey);
        if (stat != null) {
            stat.addToCurrentModifier(value);
        }
        return character;
    }

    /**
     * Unadjusts the target Stat's current modifier using the given value, undoing the state change done by adjust
     * @param character the Character to unadjust
     * @return the adjusted Character
     */
    public Character unadjust(Character character){
        Stat stat = character.getStatByName(statKey);
        if (stat != null) {
            stat.addToCurrentModifier(-value);
        }
        return character;
    }
}
