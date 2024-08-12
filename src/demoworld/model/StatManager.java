package demoworld.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for holding and managing access to the various Stats of a Character
 */
public class StatManager {

    private List<Stat> stats;

    /**
     * Constructs a new empty StatManager
     */
    public StatManager(){
        this.stats = new ArrayList<>();
    }

    /**
     * Add the given Stat to the stat manager for it to manage
     * @param stat the given Stat to the stat manager for it to manage
     */
    public void add(Stat stat){
        stats.add(stat);
    }

    /**
     * Returns a List holding all PrimaryStats held by the StatManager
     * @return List holding all PrimaryStats held by the StatManager
     */
    public List<PrimaryStat> allPrimaries(){
        List<PrimaryStat> primaryStats = new ArrayList<>();
        for (Stat stat : stats) {
            if (stat instanceof PrimaryStat) {
                primaryStats.add((PrimaryStat) stat);
            }
        }
        return primaryStats;
    }

    /**
     * Get the first Stat with a matching name and return it
     * @param name the name of the Stat you are looking for
     * @return that Stat if it exists
     * @throws IllegalStateException throw an illegalStateException
     *         if it can't find any such stat as that indicates there
     *         is a potential misalignment of the expected state between systems
     */
    public Stat byName(String name)
            throws IllegalStateException {
        for (Stat stat : stats) {
            if (stat.name().equals(name)) {
                return stat;
            }
        }
        throw new IllegalStateException("Stat with name " + name + " not found");
    }

    /**
     * Creates a new List and returns it holding references to all the Stat managed by the stat manager
     * @return a new List and returns it holding references to all the Stat managed by the stat manager
     */
    public List<Stat> all(){
        return new ArrayList<>(stats);
    }

    /**
     * Remove the given Stat from the stat manager
     * @param stat the given Stat from the stat manager
     */
    public void remove(Stat stat){
        stats.remove(stat);
    }

    /**
     * Returns a string representation of the contents of the primary Stats in stat manager
     * @return a string representation of the contents of the primary Stats in stat manager
     */
    public String primaryToString(){
        StringBuilder sb = new StringBuilder();
        for (PrimaryStat primaryStat : allPrimaries()) {
            sb.append(primaryStat.toString()).append("\n");
        }
        return sb.toString();
    }
}
