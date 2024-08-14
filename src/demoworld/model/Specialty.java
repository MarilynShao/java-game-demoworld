package demoworld.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A game specialty in the game that holds features it can bestow on a character and requirements
 * that can be checked against implements the Adjuster interface to adjust and unadjust a characters state
 */
public class Specialty implements Adjuster{

    private String name;
    private String description;
    private List<Feature> features;
    private List<Requirement> requirements;

    /**
     * Constructs a specialty that can hold Features and Requirement related to that specialty.
     * @param name name for the specialty
     * @param description description for the specialty
     */
    public Specialty(String name,
                     String description){
        this.name = name;
        this.description = description;
        this.features = new ArrayList<>();
        this.requirements = new ArrayList<>();
    }

    /**
     * Gets the name of the specialty
     * @return name of the specialty
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the text description of the specialty
     * @return text description of the specialty
     */
    public String getDescription(){
        return description;
    }

    /**
     * Adds a Feature to the requirements this specialty uses
     * @param feature the feature being added to this specialty
     */
    public void addFeature(Feature feature){
        features.add(feature);
    }

    /**
     * Adds a Requirement to the requirements this feature uses
     * @param requirement the requirement being added to this specialty
     */
    public void addRequirement(Requirement requirement){
        requirements.add(requirement);
    }

    /**
     * Returns a new list of references to the Requirements stored in this specialty
     * @return new List of requirements
     */
    public List<Requirement> getRequirements(){
        return new ArrayList<>(requirements);
    }

    /**
     * Returns a new list of references to the Features stored in this specialty
     * @return new List of features
     */
    public List<Feature> getFeatures(){
        return new ArrayList<>(features);
    }

    /**
     * Returns if the given character meets the requirements for this specialty
     * @param character the character being checked for requirements
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
     * Adds the specialties features to the Character
     * @param character the Character to adjust
     * @return the adjusted character
     */
    public Character adjust(Character character) {
        if (!character.getSpecialty().all().contains(this)) {
            character.getSpecialty().add(this);
        }

        for (Feature feature : features) {
            character = feature.adjust(character);
        }
        return character;
    }

    /**
     * Remove the specialties features from the Character
     * @param character the Character to unadjust
     * @return the unadjusted character
     */
    public Character unadjust(Character character) {
        character.getSpecialty().all().remove(this);

        for (Feature feature : features) {
            character = feature.unadjust(character);
        }
        return character;
    }

    /**
     * Returns a string representation of the specialty
     * @return a string representation of the specialty
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name.toUpperCase());
        sb.append("\n").append(description).append("\n");

        return sb.toString();
    }
}
