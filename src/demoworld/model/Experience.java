package demoworld.model;

/**
 * The Experience class represents a character current experience and how far they are from hitting a level up.
 * It extends the Value class to manage the experience value, the minimum should always be 0
 */
public class Experience extends Value{

    /**
     * Constructs a new experience object
     * @param max the maximum amount for the experience value
     * @param current the initial current amount for the experience value
     */
    public Experience(int max,
                      int current){
        super(0, max, current);
    }

    /**
     * Checks if experience bar is full
     * @return true if the current experience is equal to the maximum experience, false otherwise
     */
    public boolean canLevelUp(){
        return current() == max();
    }

    /**
     * Resets the current experience to the minimum value.
     */
    public void resetToMin(){
        setCurrent(min());
    }

    /**
     * returns a String representation of the current state of this instance of Experience
     * @return String representation of the current state of this instance of Experience
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // XP display in "XP: current/max" format
        sb.append("XP: ").append(current()).append("/").append(max()).append("\n");

        // Visual representation of the XP bar
        for (int i = 0; i < current(); i++) {
            sb.append("[*] ");
        }
        for (int i = current(); i < max(); i++) {
            sb.append("[ ] ");
        }
        sb.append("\n");

        return sb.toString();
    }

}
