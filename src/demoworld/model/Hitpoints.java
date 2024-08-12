package demoworld.model;

/**
 * Hitpoints represents the hitpoints of a Character.
 * It extends the Stat class and provides methods to manage the characters hitpoints
 */
public class Hitpoints extends Stat{

    private int tempHp;

    /**
     * Constructs a new Hitpoints instance
     * @param max initial maximum amount for the base and modifier values in hitpoints
     * @param current initial current amount for the base and modifier values in hitpoints
     */
    public Hitpoints(int max,
                     int current){
        super("hitpoints", "Represents the hitpoints of the character", 0, max, current);
        this.tempHp = 0;
    }

    /**
     * Checks if the hitpoints state would indicate the character is downed
     * @return true if the current hit points are equal to the minimum hit points, false otherwise
     */
    public boolean isDead(){
        return getTotal() == getTotalMin();
    }

    /**
     * Applies damage to the character's hit points. Damage is taken
     * from temporary hitpoints first, then from base hitpoints
     * @param value the amount of damage to apply
     * @return the new total hitpoints
     */
    public int damage(int value){
        if (tempHp > 0) {
            if (value <= tempHp) {
                tempHp -= value;
                return getTotal();
            } else {
                value -= tempHp;
                tempHp = 0;
            }
        }
        setCurrentBase(getBase().current() - value);
        return getTotal();
    }

    /**
     * Heals the characters current hitpoints (temporary extra hitpoints can not be healed)
     * @param value the amount of healing to apply
     * @return the new total hitpoints
     */
    public int heal(int value){
        setCurrentBase(getBase().current() + value);
        return getTotal();
    }

    /**
     * Sets the temporary hitpoints for the Character.
     * This can never be set to a negative number, if its given a negative number turns it to 0
     * @param value the temporary hitpoints to set
     */
    public void setTempHp(int value){
        if (value < 0) {
            tempHp = 0;
        } else {
            tempHp = value;
        }
    }

    /**
     * Gets the current temporary hitpoints
     * @return the current temporary hitpoints
     */
    public int getTempHp(){
        return tempHp;
    }

    /**
     * Resets the temporary hit points to zero
     */
    public void resetTempHp(){
        tempHp = 0;
    }

    /**
     * returns a String representation of the current state of this instance of Hitpoints
     * @return String representation of the current state of this instance of Hitpoints
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("HITPOINTS: ").append(getTotal()).append("/").append(getTotalMax()).append("\n");

        for (int i = 0; i < getTotal(); i++) {
            sb.append("[*] ");
        }
        for (int i = getTotal(); i < getTotalMax(); i++) {
            sb.append("[ ] ");
        }
        sb.append("\n");

        sb.append("TEMP HP: ").append(tempHp).append("\n");

        return sb.toString();
    }
}
