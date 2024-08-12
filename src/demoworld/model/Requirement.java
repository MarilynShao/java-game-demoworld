package demoworld.model;

/**
 * Requirements check if a Character has a given Stat that matches the statKey held by this and
 * if that Character Stat is equal to or above the threshold requirement is enforcing
 */
public class Requirement {

    private String name;
    private String statKey;
    private int threshold;

    /**
     * Construct a new requirement with a given name, statKey and threshold
     * @param name the name of the requirement
     * @param statKey the name of the Stat you want the requirement to check against
     * @param threshold how much the Stat Value current total amount needs to be equal to or above
     */
    public Requirement(String name,
                       String statKey,
                       int threshold){
        this.name = name;
        this.statKey = statKey;
        this.threshold = threshold;
    }

    /**
     * Gets the name of the Requirement
     * @return name of the Requirement
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the statKey for this requirement.
     * @return the statKey for this requirement
     */
    public String getStatKey() {
        return statKey;
    }

    /**
     * Gets the threshold for this requirement.
     * @return the threshold for this requirement
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     * Returns if the Character current state fulfills the criteria of this specific Requirement
     * @param character the character state that will be checked
     * @return true if the characters Stat that matches the statKey
     */
    public boolean check(Character character){
        try {
            Stat stat = character.getStat().byName(statKey);
            return stat.getTotal() >= threshold;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    /**
     * Returns a string representation of the contents of the requirement
     * @return a string representation of the contents of the requirement
     */
    @Override
    public String toString(){
        return String.format("Requirement{name='%s', statKey='%s', threshold=%d}", name, statKey, threshold);
    }
}
