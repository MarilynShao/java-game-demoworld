package demoworld;

import demoworld.model.DemoWorld;
import demoworld.model.RuleBook;

public class Main {
    public static void main(String[] args) {
        RuleBook rulebook = new DemoWorld();
        CharacterBuilder builder = new CharacterBuilder(rulebook);

        builder.start();
    }
}