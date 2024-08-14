package demoworld.model;

/**
 * A representation of a characters state in a rpg game
 */
public class Character {

    private String name;
    private RuleBook rulebook;
    private StatManager statManager;
    private Experience experience;
    private FeatureManager featureManager;
    private SpecialtyManager specialtyManager;

    /**
     * Constructs a new Character with the given initial name and rulebook
     * @param name the initial name for the character, which should be a String
     * @param rulebook the DemoWorld rulebook the character should refer to
     */
    public Character(String name, RuleBook rulebook){

        this.name = name;
        this.rulebook = rulebook;
        this.statManager = rulebook.getStat();
        this.experience = rulebook.getExperienceReference();
        this.featureManager = new FeatureManager();
        this.specialtyManager = new SpecialtyManager();
    }

    /**
     * Sets the name of the character
     * @param name the string you want to set the characters name to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the character
     * @return name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the StatManager for the character
     * @return reference to the characters StatManager
     */
    public StatManager getStat() {
        return statManager;
    }

    /**
     * Gets the Stat with the given name from the character
     * @param name name of stat looking for
     * @return the Stat with the given name from the character
     */
    public Stat getStatByName(String name){
        return statManager.byName(name);
    }

    /**
     * Gets the FeatureManager for the character
     * @return reference to the characters FeatureManager
     */
    public FeatureManager getFeature(){
        return featureManager;
    }

    /**
     * Gets the SpecialtyManager for the character
     * @return reference to the characters SpecialtyManager
     */
    public SpecialtyManager getSpecialty(){
        return specialtyManager;
    }

    /**
     * Gets the characters Experience object
     * @return reference to the Experience for the character
     */
    public Experience getExperience(){
        return experience;
    }

    /**
     * adds a specialty to the character and adjusts the character based on that Specialty
     * @param specialty the Specialty being added to the character
     */
    public void addSpecialty(Specialty specialty){
        specialtyManager.add(specialty);
        specialty.adjust(this);
        for (Feature feature : specialty.getFeatures()) {
            addFeature(feature);
        }
    }

    /**
     * adds a feature to the character and adjusts the character based on that feature
     * @param feature the Feature being added to the character
     */
    public void addFeature(Feature feature){
        featureManager.add(feature);
        feature.adjust(this);
    }

    /**
     * removes a feature to the character and unadjusts the character based on that feature
     * @param feature the Feature being removed from the character
     */
    public void removeFeature(Feature feature){
        featureManager.remove(feature);
        feature.unadjust(this);
    }

    /**
     * damages the character and then checks for death
     * @param value the amount of damage to be inflicted on the character
     */
    public void damage(int value){
        Hitpoints hpStat = (Hitpoints) getStatByName("hitpoints");
        if (hpStat != null) {
            hpStat.damage(value);
            if (hpStat.isDead()) {
                // Handle character death, will develop later
            }
        }
    }

    /**
     * heals the character and then checks for death
     * @param value the amount of hitpoints to be healed
     */
    public void heal(int value){
        Hitpoints hpStat = (Hitpoints) getStatByName("hitpoints");
        if (hpStat != null) {
            hpStat.heal(value);
        }
    }

    /**
     * adjusts the characters Experience then check if the character should level up
     * and reset the xp back to the minimum
     * @param value the value to add to the characters current experience
     */
    public void adjustXp(int value){
        experience.addToCurrent(value);
        if (experience.canLevelUp()) {
            experience.resetToMin();
        }
    }

    /**
     * creates and returns a string representation of the characters current state
     * @return the string representation of the characters current state
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Top bar
        sb.append("  _______________________________________\n");
        sb.append("/ \\                                      |\n");
        sb.append("\\__|                                     |\n");

        // Character name centered with padding
        sb.append("   |             * ").append(name).append(" *                |\n");

        // Padding for spacing
        sb.append("   |                                     |\n");
        sb.append("   |                                     |\n");

        // Adding primary stats using StatManager's primaryToString() method
        String[] statsLines = statManager.primaryToString().split("\n");
        for (String line : statsLines) {
            sb.append("   |      ").append(String.format("%-31s", line)).append("|\n");
        }

        // Bottom border
        sb.append("   |                                     |\n");
        sb.append("   |  ___________________________________|__\n");
        sb.append("   \\_/_____________________________________/\n");

        // Adding hitpoints
        sb.append(getStatByName("hitpoints").toString());

        // Adding experience
        sb.append(getExperience().toString());

        // Specialties
        sb.append(getSpecialty().toString()).append("\n");

        // Features
        sb.append(getFeature().toString()).append("\n");

        return sb.toString();
    }

}
