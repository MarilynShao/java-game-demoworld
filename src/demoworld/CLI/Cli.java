package demoworld.CLI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * basic cli
 * handles providing options to the user and checking which they picked
 */
public class Cli {

    /**
     * Prompts the user to pick an option from a collection provided as a HashMap.
     *
     * @param <T>     the type of values in the options collection
     * @param options the HashMap containing options as keys and values
     * @return the value corresponding to the chosen key
     */
    public <T> T pickOptionFromCollection(HashMap<String, T> options) {
        HashMap<String, T> internalOptions = new HashMap<>(options);
        CliMenu<T> menu = new CliMenu<>(internalOptions);
        return menu.getValue();
    }

    /**
     * Prompts the user to pick an option from a collection provided as an ArrayList.
     *
     * @param <T>     the type of values in the options collection
     * @param options the ArrayList containing options
     * @return the chosen option
     */
    public <T> T pickOptionFromCollection(List<T> options) {
        List<T> internalOptions = new ArrayList<>(options);
        CliMenu<T> menu = new CliMenu<>(internalOptions);
        return menu.getValue();
    }

    /**
     * Prompts the user to pick multiple unique options from a collection provided as an ArrayList.
     *
     * @param <T>     the type of values in the options collection
     * @param picks   the number of options the user is allowed to pick
     * @param options the ArrayList containing options
     * @return an ArrayList containing the chosen options
     */
    public <T> ArrayList<T> pickOptionsFromCollection(int picks, ArrayList<T> options) {
        ArrayList<T> result = new ArrayList<>();
        ArrayList<T> internalOptions = new ArrayList<>(options);
        while (picks > 0 && !internalOptions.isEmpty()) { //keep picking while you can
            System.out.println("PICKS REMAINING: [" + picks + "]");
            T choice = this.pickOptionFromCollection(internalOptions);
            if (choice != null) {
                if (!result.contains(choice)) {
                    picks -= 1;
                    result.add(choice);
                    internalOptions.remove(choice);
                } else {
                    System.out.println("You've already picked that choice sorry!");
                }
            }
        }
        return result;
    }

    /**
     * Prompts the user to enter a string input.
     *
     * @return the user's input as a String
     */
    public String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input: ");
        return scanner.nextLine();
    }

    /**
     * Prints blank lines to the console.
     *
     * @param lines the number of blank lines to print
     */
    public void blank(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.println("\n");
        }
    }

    /**
     * The main method to demonstrate the usage of the CLI class.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Cli cli = new Cli();
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        options.add("Option 3");

        String pickedOption = cli.pickOptionFromCollection(options);
        System.out.println("Picked option: " + pickedOption);

        HashMap<String, String> optionMap = new HashMap<>();
        optionMap.put("A", "Option A");
        optionMap.put("B", "Option B");
        optionMap.put("C", "Option C");

        String pickedOptionFromMap = cli.pickOptionFromCollection(optionMap);
        System.out.println("Picked option from map: " + pickedOptionFromMap);
    }
}