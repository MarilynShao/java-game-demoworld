package demoworld.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for holding and managing access to various Requirements of a RuleBook or Feature
 */
public class RequirementManager {

    private List<Requirement> requirements;

    /**
     * Constructs an empty requirements manager
     */
    public RequirementManager(){
        this.requirements = new ArrayList<>();
    }

    /**
     * Adds a Requirement to the RequirementManager
     * @param requirement requirement to add to the RequirementManager
     */
    public void add(Requirement requirement){
        requirements.add(requirement);
    }

    /**
     * Get the first Requirement with a matching name and return it
     * @param name the name of the Requirement you are looking for
     * @return that Requirement if it exists
     * @throws IllegalStateException throw an illegalStateException if it can't find any such requirement as
     *                               that indicates there is a potential misalignment of the expected
     *                               state between systems
     */
    public Requirement byName(String name)
            throws IllegalStateException{
        for (Requirement requirement : requirements) {
            if (requirement.getName().equals(name)) {
                return requirement;
            }
        }
        throw new IllegalStateException("Requirement with name " + name + " not found");
    }

    /**
     * Removes a given Requirement from the RequirementManager
     * @param requirement the requirement to be removed from the RequirementManager
     */
    public void remove(Requirement requirement){
        requirements.remove(requirement);
    }

    /**
     * Creates a new List holding references to all the
     * Requirements managed by the RequirementManager and returns it
     * @return a new List holding references to all the Requirements managed by the RequirementManager
     */
    public List<Requirement> all(){
        return new ArrayList<>(requirements);
    }
}
