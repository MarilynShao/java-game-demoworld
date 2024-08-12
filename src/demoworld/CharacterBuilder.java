package demoworld;

import demoworld.model.Character;

import demoworld.model.DemoWorld;
import demoworld.model.PrimaryStat;
import demoworld.model.RuleBook;
import demoworld.model.Specialty;
import demoworld.model.Feature;
import demoworld.scribe.Scribe;
import demoworld.CLI.Cli;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages walking through the build process for a new character.
 */
public class CharacterBuilder {
    /**
     * cli
     */
    private final Cli cli;
    /**
     * rulebook
     */
    private final RuleBook rulebook;
    /**
     * character
     */
    private Character character;

    /**
     * Constructs a demoworld.CharacterBuilder for the given rulebook
     *
     * @param rulebook the rulebook the character builder will be using
     */
    public CharacterBuilder(RuleBook rulebook) {
        this.cli = new Cli();
        this.rulebook = rulebook;
    }

    /**
     * starts and runs the entire interactive char building process
     */
    public void start() {
        welcomeMessage();
        String name = getNameFromUser();
        character = new Character(name, rulebook);
        allocateStats();
        Specialty pickedSpecialty = pickSpecialty();
        pickFeatures(pickedSpecialty);
        finalizeCharacter();
    }

    private void welcomeMessage() {
        System.out.println("Welcome to "
                + rulebook.getName()
                + " edition:#"
                + rulebook.getEdition()
                + " A character generator for a simplified version of a simple world character!. "
                + "(there will be a pdf written and provided)");
        System.out.println("--------------------------------------------------------------");
    }

    private String getNameFromUser() {
        System.out.println("What is your name? (INPUT HERE)");
        return cli.getStringInput();
    }

    private void allocateStats() {
        final List<PrimaryStat> primaryStats = character.getStat().allPrimaries();
        final List<Integer> numbersToAllocate = rulebook.getStatSpreadReference();

        System.out.println("Stat numbers to distribute");
        for (Integer numberToAllocate : numbersToAllocate) {
            System.out.println(numberToAllocate);
        }
        System.out.println("----------------------------");

        for (PrimaryStat primaryStat : primaryStats) {
            System.out.println(primaryStat.name());
        }

        while (!numbersToAllocate.isEmpty()) {
            Integer numberToAllocate = numbersToAllocate.removeFirst();
            System.out.println("----------------------------\n");
            printCurrentStats();
            System.out.println("Which stat do you want to allocate " + numberToAllocate + " to?");
            PrimaryStat choice = cli.pickOptionFromCollection(primaryStats);
            primaryStats.remove(choice);
            choice.setCurrentBase(numberToAllocate);
        }
        cli.blank(80);
    }

    private void printCurrentStats() {
        System.out.println(
                 "f:" + character.getStat().byName("force").getTotal()
                        + " q:" + character.getStat().byName("quickness").getTotal()
                        + " r:" + character.getStat().byName("resilience").getTotal()
                        + " a:" + character.getStat().byName("analytical").getTotal()
                        + " e:" + character.getStat().byName("empathy").getTotal()
        );
    }

    private Specialty pickSpecialty() {
        System.out.println("SPECIALTY");
        System.out.println("----------------------------");

        Specialty pickedSpecialty = cli.pickOptionFromCollection(
                rulebook.getSpecialtiesFilteredByRequirements(character)
        );
        cli.blank(80);
        character.addSpecialty(pickedSpecialty);

        return pickedSpecialty;
    }

    private void pickFeatures(Specialty pickedSpecialty) {
        System.out.println("FEATURES");
        System.out.println("----------------------------");

        //clean out any featuresRef that are in the specialties from featuresRef
        // so the player can't double up
        ArrayList<Feature> featuresRef = new ArrayList<>(
                rulebook.getFeaturesFilteredByRequirements(character)
        );
        for (Feature feature : pickedSpecialty.getFeatures()) {
            featuresRef.remove(feature);
        }
        try {
            for (Feature feature : featuresRef) {
                if (feature.getName() == "downed") {
                    featuresRef.remove(feature);
                }
            }
        } catch (Exception e) {
            //there is no downed featured
        }

        ArrayList<Feature> pickedFeatures = cli.pickOptionsFromCollection(
                rulebook.getFeaturePickCount(), featuresRef
        );
        cli.blank(80);

        for (Feature feature : pickedFeatures) {
            character.addFeature(feature);
        }
    }

    private void finalizeCharacter() {
        character.damage(2);

        System.out.println("What do you want to call the file?");
        String filename = cli.getStringInput();

        Scribe.writeToFile(filename, character.toString(), 43442148);
        System.out.println(character);
    }
}