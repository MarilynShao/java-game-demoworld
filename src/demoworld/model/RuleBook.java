package demoworld.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class spelling out what a Rulebook will have at the minimum for methods
 */
public abstract class RuleBook {
    /**
     * Gets the name of the rulebook.
     *
     * @return name of the rulebook
     */
    public abstract String getName();

    /**
     * Gets the edition of the rulebook.
     *
     * @return edition of the rulebook
     */
    public abstract double getEdition();

    /**
     * Gets a list of every {@link Feature} in the game.
     *
     * @return a List of every feature that exists for this ruleset.
     */
    public abstract List<Feature> getFeaturesReference();

    /**
     * Get the rulebooks initial {@link Experience} reference.
     *
     * @return the rulebooks initial {@link Experience} reference.
     */
    public abstract Experience getExperienceReference();

    /**
     * returns the number of feature a character can pick when being created as an {@code int}
     * @return the number of feature a character can pick when being created as an {@code int}
     */
    public abstract int getFeaturePickCount();

    /**
     * Returns a {@code List} of {@code ints} used
     * @return a {@code List} of {@code ints} used
     */
    public abstract List<Integer> getStatSpreadReference();

    /**
     * Returns a {@code List} of {@link Specialty}s that the given {@link Character} meets the
     * requirements for
     * @param character the character state that is being checked against for which specialties
     *                  can be picked
     * @return a {@code List} of {@link Specialty}s that the given {@link Character} meets the
     * requirements for
     */
    public abstract List<Specialty> getSpecialtiesFilteredByRequirements(Character character);

    /**
     * Returns a {@code List} of {@link Feature}s that the given {@link Character}
     * meets the requirements for.
     * @param character the character state that is being checked against for which specialties
     *                  can be picked
     * @return a {@code List} of {@link Feature}s that the given {@link Character}
     * meets the requirements for.
     */
    public abstract List<Feature> getFeaturesFilteredByRequirements(Character character);

    /**
     * creates a {@code List} of the initial {@link Stat}s for a starting {@link Character}.
     * @return  a {@code List} of the initial {@link Stat}s for a starting {@link Character}.
     */
    protected abstract List<Stat> createAndGetStats();

    /**
     * Creates an initial {@link Hitpoints} configured with the specific rulesets defaults.
     * @return {@link Hitpoints}
     */
    protected abstract Hitpoints setCharacterStartingHitpoints();


    /**
     * Creates an initial {@link Experience} configured with the specific rulesets defaults.
     * @return {@link Experience}
     */
    protected abstract Experience setCharacterStartingExperience();


    /**
     * creates a {@code List} of the initial {@link Feature}s for a starting {@link Character}.
     * @return  a {@code List} of the initial {@link Feature}s for a starting {@link Character}.
     */
    protected abstract List<Feature> createAndGetFeatures();

    /**
     * creates a {@code List} of the initial {@link Specialty}s for a starting {@link Character}.
     * @return  a {@code List} of the initial {@link Specialty}s for a starting {@link Character}.
     */
    protected abstract List<Specialty> createAndGetSpecialties();

    /**
     * creates a {@code List} of the initial {@link Requirement}s for a starting {@link Character}.
     * @return  a {@code List} of the initial {@link Requirement}s for a starting {@link Character}.
     */
    protected abstract List<Requirement> createAndGetRequirements();

    /**
     * Returns a reference to the rulebooks stat manager
     * @return {@link StatManager}
     */
    protected abstract StatManager getStat();

    /**
     * Returns a reference to the rulebooks stat manager
     * @return {@link FeatureManager}
     */
    protected abstract FeatureManager getFeature();


    /**
     * Returns a reference to the rulebooks stat manager
     * @return {@link SpecialtyManager}
     */
    protected abstract SpecialtyManager getSpecialty();

    /**
     * Returns a reference to the rulebooks stat manager
     * @return {@link RequirementManager}
     */
    protected abstract RequirementManager getRequirement();

    /**
     * Returns List of Stats
     * @return List of Stats
     */
    protected abstract List<Stat> getStatReference();

    /**
     * Returns List of {@link Specialty}'s
     * @return List of {@link Specialty}
     */
    protected abstract List<Specialty> getSpecialtiesReference();

}