package demoworld.model;

import demoworld.model.adjustments.Adjustment;
import demoworld.model.Requirement;
import java.util.ArrayList;
import java.util.List;

/**
 * A game feature in the game that can include automated adjustments and Requirement that can be
 * checked against implements the Adjuster interface to adjust and unadjust a characters state
 */
public class Feature implements Adjuster{

    private String name;
    private String description;
    private List<Adjustment> adjustments;
    private List<Requirement> requirements;

    /**
     * Constructs a feature that can hold Adjustments and Requirement related to that feature
     * @param name the name of the feature
     * @param description the description of the feature
     */
    public Feature(String name,
                   String description){

        this.name = name;
        this.description = description;
        this.adjustments = new ArrayList<>();
        this.requirements = new ArrayList<>();
    }

    /**
     * Gets the name of the feature
     * @return name of the feature
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the text description of the feature
     * @return text description of the feature
     */
    public String getDescription(){
        return description;
    }

    /**
     * Adds an Adjustment to the adjustments this feature uses
     * @param adjustment the adjustment
     */
    public void addAdjustment(Adjustment adjustment){
        adjustments.add(adjustment);
    }

    /**
     * Adds a Requirement to the requirements this feature uses
     * @param requirement the requirement to be added to the feature
     */
    public void addRequirement(Requirement requirement){
        requirements.add(requirement);
    }

    /**
     * Returns if the given Character meets the requirements for this feature
     * @param character Character whos state will be checked to determine if they meet
     *                  the Requirement for this feature
     * @return True if they do, False otherwise
     */
    public boolean meetsRequirements(Character character){
        for (Requirement requirement : requirements) {
            if (!requirement.check(character)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a new list of references to the Requirements stored in this feature
     * @return new List of Requirement
     */
    public List<Requirement> getRequirements(){
        return new ArrayList<>(requirements);
    }

    /**
     * Applies all adjusts required by the feature
     * @param character the Character to adjust
     * @return the adjusted character
     */
    public Character adjust(Character character){
        for (Adjustment adjustment : adjustments) {
            character = adjustment.adjust(character);
        }
        return character;
    }

    /**
     * Applies all unadjusts required by the feature
     * @param character the Character to unadjust
     * @return the unadjusted character
     */
    public Character unadjust(Character character){
        for (Adjustment adjustment : adjustments) {
            character = adjustment.unadjust(character);
        }
        return character;
    }

    /**
     * Returns a string representation of the Feature
     * @return a string representation of the Feature
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Format the name
        sb.append(name.toUpperCase());
        sb.append(" [");

        // Append markers based on the number of adjustments
        if (!adjustments.isEmpty()) {
            sb.append("*".repeat(adjustments.size()));
        }
        sb.append("]");

        // Add requirements
        if (!requirements.isEmpty()) {
            sb.append("\nrequires: ");
            for (Requirement req : requirements) {
                sb.append(req.getStatKey()).append(" >= ").append(req.getThreshold()).append(" ");
            }
        }

        // Add description
        sb.append("\n\"").append(description).append("\"\n");

        return sb.toString();
    }
}
