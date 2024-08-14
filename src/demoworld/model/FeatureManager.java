package demoworld.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for holding and managing access to various Features of a Character or RuleBook
 */
public class FeatureManager {
    private List<Feature> features;

    /**
     * Constructs an empty requirements manager
     */
    public FeatureManager(){
        this.features = new ArrayList<>();
    }

    /**
     * Adds a Feature to the FeatureManager
     * @param feature the feature being added to the FeatureManager
     */
    public void add(Feature feature){
        features.add(feature);
    }

    /**
     * Get the first Feature with a matching name and return it
     * @param name the name of the Feature you are looking for
     * @return that Feature if it exists
     * @throws IllegalStateException throw an illegalStateException
     * if it can't find any such feature as that indicates there is a potential misalignment
     * of the expected state between systems.
     */
    public Feature byName(String name)
            throws IllegalStateException{
        for (Feature feature : features) {
            if (feature.getName().equals(name)) {
                return feature;
            }
        }
        throw new IllegalStateException("Feature with name " + name + " not found");
    }

    /**
     * Removes a given Feature from the FeatureManager
     * @param feature the feature to remove from the FeatureManager
     */
    public void remove(Feature feature){
        features.remove(feature);
    }

    /**
     * Creates a new List holding references to all the Features managed by the FeatureManager and returns it
     * @return a new List holding references to all the Features managed by the FeatureManager
     */
    public List<Feature> all(){
        return new ArrayList<>(features);
    }

    /**
     * Returns a string representation of the contents of the feature manager
     * @return a string representation of the contents of the feature manager
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("        |===========================\\\n");
        sb.append("[0]#####>-------- FEATURES ---------->\n");
        sb.append("        |===========================/\n");

        int index = 1;

        for (Feature feature : features) {
            sb.append(index++).append(". ").append(feature.toString()).append("\n");
        }

        return sb.toString();
    }
}
