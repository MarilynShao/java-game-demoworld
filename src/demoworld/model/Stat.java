package demoworld.model;

/**
 * Representation of a game stat, an object constructed from multiple Value with a name and a description
 */
public class Stat {

    private String name;
    private String description;
    private Value base;
    private Value modifier;


    /**
     * Constructs a Stat
     * @param name the name of the new stat
     * @param description the description of the new stat
     * @param min initial minimum of the base and modifier values in the new stat
     * @param max initial maximum of the base and modifier values in the new stat
     * @param current initial current of the base and modifier values in the new stat
     */
    public Stat(String name,
                String description,
                int min,
                int max,
                int current){

        this.name = name;
        this.description = description;
        this.base = new Value(min, max, current);
        this.modifier = new Value(min, max, current);
    }

    /**
     * Gets the name of the stat
     * @return name the name of the stat
     */
    public String name(){
        return name;
    }

    /**
     * Gets the description of the stat
     * @return description the description of the stat
     */
    public String description(){
        return description;
    }

    /**
     * Gets the base Value
     * @return the base Value
     */
    public Value getBase(){
        return base;
    }

    /**
     * Gets the modifier Value
     * @return the modifier Value
     */
    public Value getModifier(){
        return modifier;
    }

    /**
     * Gets the current amount for the modifier Value
     * @return the amount to set the current amount of modifier Value to
     */
    public int currentModifier(){
        return modifier.current();
    }

    /**
     * Sets the current amount for the modifier Value
     * @param value the amount to set the current amount of modifier Value to
     */
    public void setCurrentModifier(int value){
        modifier.setCurrent(value);
    }

    /**
     * Adds a value to the current amount for the modifier Value
     * @param value to the current amount for the modifier Value
     */
    public void addToCurrentModifier(int value){
        modifier.addToCurrent(value);
    }

    /**
     * Sets the current amount for the base Value
     * @param value the current amount for the base Value
     */
    public void setCurrentBase(int value){
        base.setCurrent(value);
    }

    /**
     * Adds a value to the current amount for the base Value
     * @param value the current amount for the base Value
     */
    public void addToCurrentBase(int value){
        base.addToCurrent(value);
    }

    /**
     * Adds a value to the maximum amount for the base Value
     * @param value the maximum amount for the base Value
     */
    public void addToMaxBase(int value){
        base.addToMax(value);
    }

    /**
     * Sets the maximum amount for the base Value
     * @param value the maximum amount for the base Value
     */
    public void setMaxBase(int value){
        base.setMax(value);
    }

    /**
     * Sets the minimum amount for the base Value
     * @param value minimum amount for the base Value
     */
    public void setMinBase(int value){
        base.setMin(value);
    }

    /**
     * Gets the total of the base and modifier minimum amounts Value
     * @return the total of the base and modifier minimum amounts Value
     */
    public int getTotalMin(){
        return base.min() + modifier.min();
    }

    /**
     * Gets the total of the base and modifier maximum amounts Value
     * @return the total of the base and modifier maximum amounts Value
     */
    public int getTotalMax(){
        return base.max() + modifier.max();
    }

    /**
     * Gets the total of the base and modifier current amounts Value
     * @return the total of the base and modifier current amounts Value
     */
    public int getTotal(){
        return base.current() + modifier.current();
    }

    /**
     * Returns a string representation of the stats state
     * @return a string representation of the stats state
     */
    @Override
    public String toString(){
        return String.format("%-1s %d: (%d + %d)", name.toUpperCase(), getTotal(), base.current(), modifier.current());
    }
}
