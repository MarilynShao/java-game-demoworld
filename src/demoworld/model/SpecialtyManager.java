package demoworld.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for holding and managing access to various Specialty of a Character or RuleBook
 */
public class SpecialtyManager {

    private List<Specialty> specialties;

    /**
     * Constructs a new empty SpecialtyManager
     */
    public SpecialtyManager(){
        this.specialties = new ArrayList<>();
    }

    /**
     * Adds a Specialty to the SpecialtyManager
     * @param specialty the specialty being added to the SpecialtyManager
     */
    public void add(Specialty specialty) {
        if (!specialties.contains(specialty)) {
            specialties.add(specialty);
        }
    }

    /**
     * Creates a new List holding references to all the Specialty managed by the SpecialtyManager and returns it
     * @return a new List holding references to all the Specialty managed by the SpecialtyManager
     */
    public List<Specialty> all(){
        return new ArrayList<>(specialties);
    }

    /**
     * Returns a string representation of the contents of the specialty manager
     * @return a string representation of the contents of the specialty manager
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("        |==============================\\\n");
        sb.append("[0]#####>-------- SPECIALTIES ---------->\n");
        sb.append("        |==============================/\n");

        int index = 1;
        for (Specialty specialty : specialties) {
            sb.append(index++).append(". ").append(specialty.getName().toUpperCase()).append("\n");
            sb.append(specialty.getDescription()).append("\n\n");
        }

        return sb.toString();
    }
}
