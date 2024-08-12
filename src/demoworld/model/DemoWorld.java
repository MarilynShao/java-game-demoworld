package demoworld.model;

import demoworld.model.adjustments.MaxHpAdjustment;
import demoworld.model.adjustments.MaxXpAdjustment;
import demoworld.model.adjustments.StatAdjustment;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds a set of defaults used for characters using the demo world ruleset, this way the character
 * classes implementation is kept separate from rule specifics.
 * Someone could extend demo world with different editions for example that have different starting
 * defaults, different minimums, maximums, different features, specialties etc
 */
public class DemoWorld extends RuleBook {


    /**
     * Constructs the DemoWorld ruleset including details like the starting experience and hp for a
     * basic character
     */
    public DemoWorld() {
        this.xp = setCharacterStartingExperience();
        this.stats = new StatManager();
        for (Stat stat : this.createAndGetStats()) {
            this.stats.add(stat);
        }

        this.requirements = new RequirementManager();
        for (Requirement requirement : this.createAndGetRequirements()) {
            this.requirements.add(requirement);
        }

        this.features = new FeatureManager();
        for (Feature feature : this.createAndGetFeatures()) {
            this.features.add(feature);
        }

        this.specialties = new SpecialtyManager();
        for (Specialty specialty : this.createAndGetSpecialties()) {
            this.specialties.add(specialty);
        }
    }

    /**
     * Gets the name of the rulebook.
     *
     * @return name of the rulebook
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the edition of the rulebook.
     *
     * @return edition of the rulebook
     */
    public double getEdition() {
        return edition;
    }

    /**
     * Creates all the {@link Stat}s for this particular game.
     * Consult the provided rulebook pdf for details on the core game stats if you need
     * more context.
     * Primary Stats should be set to a min of -2, max of 5 and current of 0
     * Hp should be stored
     * PrimaryStat: force       :   Reflects the amount of force your character uses both physically
     * , mentally and socially. min:-2, max:5, current:0
     * PrimaryStat: quickness   :   Indicates how swiftly your character can react physically,
     * mentally and socially. min:-2, max:5, current:0
     * PrimaryStat: resilience  :   Indicates how well your character handles setbacks,
     * physically, socially and mentally. min:-2, max:5, current:0
     * PrimaryStat: analytical  :   Shows your character's willingness to learn
     * and explore new ideas. min:-2, max:5, current:0
     * PrimaryStat: empathy     :   Reflects your character's ability to gauge, understand and share
     * the feelings of others. Enabling better communication etc. min:-2, max:5, current:0
     * Hitpoints: hitpoints     : 6 max
     *
     * @return A list of {@link Stat}s
     */
    @Override
    protected List<Stat> createAndGetStats() {
        List<Stat> stats = new ArrayList<>();
        PrimaryStat force = new PrimaryStat("force", "Reflects the amount "
                + "of force your character uses both physically, mentally and socially.",
                STAT_MIN, STAT_MAX, STARTING_STAT_DEFAULT);
        stats.add(force);
        PrimaryStat quickness = new PrimaryStat("quickness",
                "Indicates how swiftly your character can react physically, "
                        + "mentally and socially.", STAT_MIN, STAT_MAX, STARTING_STAT_DEFAULT);
        stats.add(quickness);
        PrimaryStat resilience = new PrimaryStat("resilience",
                "Indicates how well your character handles setbacks, physically, "
                        + "socially and mentally.",
                STAT_MIN, STAT_MAX, STARTING_STAT_DEFAULT);
        stats.add(resilience);
        PrimaryStat analytical = new PrimaryStat("analytical", "Shows your "
                + "character's willingness to learn and explore new ideas.",
                STAT_MIN, STAT_MAX, STARTING_STAT_DEFAULT);
        stats.add(analytical);
        PrimaryStat empathy = new PrimaryStat("empathy", "Reflects your "
                + "character's ability to gauge, understand and share the feelings of others. "
                + "Enabling better communication etc", STAT_MIN, STAT_MAX, STARTING_STAT_DEFAULT);
        stats.add(empathy);
        stats.add(setCharacterStartingHitpoints());
        return stats;
    }

    /**
     * Creates all the {@link Feature} for this particular
     *
     * @return A List of all the features that exist in this game.
     * <p>
     * List of features
     * Feature: downed : you are near death
     * <p>
     * Feature: early riser : You've spent your life having to get up early,
     * you are not afflicted by drowsy or similar effects in the morning.
     * Requirements: resilience
     * <p>
     * Feature: heat resistant : You've spent your life working around intense heat,
     * it doesn't bother you much anymore. You can roll twice and take the best result
     * on checks related to heat.
     * Requirements: resilience
     * <p>
     * Feature: weather minded : You've developed a strong set of heuristics you use to
     * predict the weather and have gotten pretty good at it.
     * You can roll twice and take the best result when doing rolls related to the weather
     * <p>
     * Feature: read : You have learned to read!, a valuable rare skill.
     * Adjustments: MaxXpAdjustment -1
     * <p>
     * Feature: write : You have learned to write! a valuable rare skill.
     * <p>
     * Feature: violent : You are worryingly comfortable inflicting violence on others,
     * You can roll twice and take the best result when doing violent activities such as combat.
     * Adjustments: StatAdjustment empathy -2
     * <p>
     * Feature: basic mathematics : You have learned basic mathematics like arithmetic !
     * a valuable rare skill.
     * Requirements: analytical
     * <p>
     * Feature: analytical mind : You have developed a sharp analytical mind with steady
     * constant practice. +1 analytical
     * Adjustments: StatAdjustment analytical +1
     * Requirements: analytical
     * <p>
     * Feature: socially fluent : You have developed a strong social fluency. +1 empathy.
     * Adjustments: StatAdjustment empathy +1
     * Requirements: empathy
     * <p>
     * Feature: callous : You are indifferent to the impact of your actions on others.
     * -2 empathy, +1 force.
     * Adjustments: StatAdjustment empathy -2, StatAdjustment force +1
     * <p>
     * Feature: grit : Through your life you've built up a good tolerance for long hours
     * and hard work.
     * Adjustments: MaxHpAdjustment +1, StatAdjustment resilience +1
     */
    @Override
    protected List<Feature> createAndGetFeatures() {

        List<Feature> features = new ArrayList<>();

        Feature downed = new Feature("downed", "you are near death!");
        features.add(downed);

        Feature earlyRiser = new Feature("early riser", "You've spent your life "
                + "having to get up early, you are not afflicted by drowsy or similar"
                + " effects in the morning.");
        earlyRiser.addRequirement(this.requirements.byName("resilience"));
        features.add(earlyRiser);

        Feature workInHeat = new Feature("heat resistant", "You've spent your "
                + "life working around intense heat, it doesn't bother you much anymore. "
                + "You can roll twice and take the best result on checks related to heat.");
        workInHeat.addRequirement(this.requirements.byName("resilience"));
        features.add(workInHeat);

        Feature weatherMinded = new Feature("weather minded", "You've developed "
                + "a strong set of heuristics "
                + "you use to predict the weather and have gotten pretty good at it. "
                + "You can roll twice and take the best result when "
                + "doing rolls related to the weather");
        features.add(weatherMinded);

        Feature read = new Feature("read", "You have learned to read!, "
                + "a valuable rare skill.");
        MaxXpAdjustment learnFast = new MaxXpAdjustment(-1);
        read.addAdjustment(learnFast);
        features.add(read);

        Feature write = new Feature("write", "You have learned to write! "
                + "a valuable rare skill.");
        features.add(write);

        Feature violent = new Feature("violent", "You are worryingly comfortable"
                + "inflicting violence on others, You can roll twice and take the best result when "
                + "doing violent activities such as combat.");
        StatAdjustment violentEmpathyAdjustment = new StatAdjustment("empathy", DROP);
        violent.addAdjustment(violentEmpathyAdjustment);
        features.add(violent);

        Feature basicMath = new Feature("basic mathematics", "You have learned "
                + "basic mathematics like arithmetic ! a valuable rare skill.");
        basicMath.addRequirement(this.requirements.byName("analytical"));
        features.add(basicMath);

        Feature analyticalMind = new Feature("analytical mind", "You have "
                + "developed a sharp analytical mind with steady "
                + "constant practice. +"
                + BOOST
                + " analytical"
        );
        StatAdjustment analyticalStatAdjustment = new StatAdjustment("analytical", BOOST);
        analyticalMind.addRequirement(this.requirements.byName("analytical"));
        analyticalMind.addAdjustment(analyticalStatAdjustment);
        features.add(analyticalMind);

        Feature sociallyFluent = new Feature("socially fluent", "You have "
                + "developed a strong social fluency. +"
                + BOOST
                + " empathy.");
        StatAdjustment sociallyFluentAdjustment = new StatAdjustment("empathy", BOOST);
        sociallyFluent.addRequirement(this.requirements.byName("empathy"));
        analyticalMind.addAdjustment(sociallyFluentAdjustment);
        features.add(sociallyFluent);

        Feature callous = new Feature("callous", "You are indifferent to"
                + "the impact of your actions on others. "
                + DROP
                + " empathy, +" + BOOST + " force.");
        StatAdjustment callousEmpathyAdjustment = new StatAdjustment("empathy", DROP);
        StatAdjustment callousForceAdjustment = new StatAdjustment("force", BOOST);
        callous.addAdjustment(callousEmpathyAdjustment);
        callous.addAdjustment(callousForceAdjustment);
        features.add(callous);

        Feature grit = new Feature("grit", "Through your life"
                + " you've built up a good tolerance for long hours and hard work.");
        StatAdjustment gritStatAdjustment = new StatAdjustment("resilience", BOOST);
        MaxHpAdjustment gritHpAdjustment = new MaxHpAdjustment(BOOST);
        grit.addAdjustment(gritStatAdjustment);
        grit.addAdjustment(gritHpAdjustment);
        features.add(grit);

        return features;
    }

    protected Hitpoints setCharacterStartingHitpoints() {
        final int startingHp = 6;
        return new Hitpoints(startingHp, startingHp);
    }

    protected Experience setCharacterStartingExperience() {
        final int xpMax = 5;
        final int xpCurrent = 0;
        return new Experience(xpMax, xpCurrent);
    }

    @Override
    protected List<Specialty> createAndGetSpecialties() {
        final List<Specialty> specialtyReferences = new ArrayList<>();

        Specialty farmer = new Specialty("farmer", "You've spent much of your "
            + "life waking early and working hard on the field.");
        farmer.addFeature(this.getFeature().byName("grit"));
        farmer.addFeature(this.getFeature().byName("early riser"));
        farmer.addFeature(this.getFeature().byName("weather minded"));
        farmer.addRequirement(this.requirements.byName("resilience"));
        specialtyReferences.add(farmer);

        Specialty smith = new Specialty("smith", "You've spent much of your "
                + "life working in the heat of the smithy.");
        smith.addFeature(this.getFeature().byName("grit"));
        smith.addFeature(this.getFeature().byName("heat resistant"));
        smith.addFeature(this.getFeature().byName("analytical mind"));
        smith.addRequirement(this.requirements.byName("force"));
        specialtyReferences.add(smith);

        Specialty merchant = new Specialty("merchant", "You've spent much of your "
                + "life travelling, managing logistics and bartering with people.");
        merchant.addFeature(this.getFeature().byName("read"));
        merchant.addFeature(this.getFeature().byName("write"));
        merchant.addFeature(this.getFeature().byName("basic mathematics"));
        merchant.addFeature(this.getFeature().byName("socially fluent"));
        merchant.addRequirement(this.requirements.byName("analytical"));
        specialtyReferences.add(merchant);

        Specialty innkeeper = new Specialty("innkeeper", "You've spent "
                + "much of your "
                + "life as an innkeeper, acting as a community figurehead, "
                + "and running an establishment");
        innkeeper.addFeature(this.getFeature().byName("read"));
        innkeeper.addFeature(this.getFeature().byName("socially fluent"));
        innkeeper.addFeature(this.getFeature().byName("basic mathematics"));
        innkeeper.addFeature(this.getFeature().byName("early riser"));
        innkeeper.addRequirement(this.requirements.byName("empathy"));
        specialtyReferences.add(innkeeper);

        Specialty bandit = new Specialty("bandit", "You've spent much of "
                + "your life violently stealing from those weaker then you");
        bandit.addFeature(this.getFeature().byName("callous"));
        bandit.addFeature(this.getFeature().byName("violent"));
        //bandit has no requirement, you can always resort to banditry as a last resort
        specialtyReferences.add(bandit);

        return specialtyReferences;
    }

    @Override
    protected List<Requirement> createAndGetRequirements() {
        List<Requirement> requirements = new ArrayList<>();
        List<PrimaryStat> stats = this.getStat().allPrimaries();
        int mildThreshold = 0;
        for (PrimaryStat stat : stats) {
            requirements.add(new Requirement(stat.name(), stat.name(), mildThreshold));
        }
        return requirements;
    }

    @Override
    protected FeatureManager getFeature() {
        return this.features;
    }

    @Override
    protected SpecialtyManager getSpecialty() {
        return this.specialties;
    }

    @Override
    protected RequirementManager getRequirement() {
        return this.requirements;
    }

    /**
     * Creates and returns an {@code List} of the spread of values to allocate to a
     * {@link Character}s
     * initial primary stats
     *
     * @return List of the values that would be given to allocate for a characters initial
     * primary stats
     */
    @Override
    public List<Integer> getStatSpreadReference() {
        List<Integer> numbersToAllocate = new ArrayList<>();
        numbersToAllocate.add(-2);
        numbersToAllocate.add(-1);
        numbersToAllocate.add(0);
        numbersToAllocate.add(1);
        numbersToAllocate.add(2);
        return numbersToAllocate;
    }

    protected StatManager getStat() {
        return this.stats;
    }


    /**
     * Gets a list of every {@link Feature} in the game.
     *
     * @return a List of every feature that exists for this ruleset.
     */
    public List<Feature> getFeaturesReference() {
        return this.features.all();
    }

    /**
     * Gets a list of every {@link Stat} in the game.
     *
     * @return a List of every stat that exists for this ruleset.
     */
    @Override
    public List<Stat> getStatReference() {
        return this.stats.all();
    }

    /**
     * Get a list of every {@link Specialty} in the game.
     *
     * @return a List of every specialty that exists for this ruleset.
     */
    @Override
    public List<Specialty> getSpecialtiesReference() {
        return this.specialties.all();
    }

    /**
     * Get a list of every {@link Specialty} in the game that the given {@link Character}
     * meets the {@link Requirement}s for.
     *
     * @param character - The character whos states will be assessed for if requirements are
     * met per specialty.
     * @return a List of every specialty that exists for this ruleset that the character
     * is qualified for.
     */
    @Override
    public List<Specialty> getSpecialtiesFilteredByRequirements(Character character)
            throws IllegalStateException {
        List<Specialty> specialtiesFilteredByRequirements = new ArrayList<>();
        for (Specialty specialty : this.getSpecialtiesReference()) {
            if (specialty.meetsRequirements(character)) {
                specialtiesFilteredByRequirements.add(specialty);
            }
        }
        return specialtiesFilteredByRequirements;
    }

    /**
     * Get a list of every {@link Feature} in the game that the given {@link Character}
     * meets the {@link Requirement}s for.
     *
     * @param character - The character whos states will be assessed for if requirements
     * are met per feature.
     * @return a List of every feature that exists for this ruleset that the character
     * is qualified for.
     */
    public List<Feature> getFeaturesFilteredByRequirements(Character character) {
        List<Feature> featuresFilteredByRequirements = new ArrayList<>();
        for (Feature feature : this.getFeaturesReference()) {
            if (feature.meetsRequirements(character)) {
                featuresFilteredByRequirements.add(feature);
            }
        }
        return featuresFilteredByRequirements;
    }

    /**
     * Get the rulebooks initial {@link Experience} reference.
     *
     * @return the rulebooks initial {@link Experience} reference.
     */
    public Experience getExperienceReference() {
        return this.xp;
    }

    /**
     * gets the number of features a starting character gets to pick
     *
     * @return the number of features a starting character gets to pick
     */
    public int getFeaturePickCount() {
        return 2;
    }

    /**
     * StatManager
     */
    private final StatManager stats;

    /**
     * RequirementManager
     */
    private final RequirementManager requirements;

    /**
     * FeatureManager
     */
    private final FeatureManager features;

    /**
     * SpecialtyManager
     */
    private final SpecialtyManager specialties;

    /**
     * experience
     */
    private final Experience xp;
    /**
     * name
     */
    private final String name = "demo world";
    /**
     * edition
     */
    private final double edition = 1.0;

    /**
     * STARTING_STAT_DEFAULT
     */
    private static final int STARTING_STAT_DEFAULT = 0;
    /**
     * STAT_MIN
     */
    private static final int STAT_MIN = -2;
    /**
     * STAT_MAX
     */
    private static final int STAT_MAX = 5;

    /**
     * DROP
     */
    private static final int DROP = -2;
    /**
     * BOOST
     */
    private static final int BOOST = 1;
}