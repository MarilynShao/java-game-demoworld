package demoworld.scribe;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The Scribe class provides functionality to write contents to a file.
 */
public class Scribe {

    /**
     * Writes the specified contents to a file with the given filename and appends the student ID
     * at the top.
     *
     * @param filename  the name of the file (without extension) to write the contents to
     * @param contents  the content to be written to the file
     * @param studentId the student ID to be appended at the top of the file
     */
    public static void writeToFile(String filename, String contents, int studentId) {
        String fullPath = "./" + filename + ".sheet";
        try (FileWriter writer = new FileWriter(fullPath)) {
            writer.write("Student ID: " + studentId + "\n");
            writer.write(contents);
            System.out.println("File written successfully to " + fullPath);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     * The main method to demonstrate the usage of the Scribe class.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Example usage
        String filename = "example";
        String contents = "This is the content of the file.";
        int studentId = 123456;

        writeToFile(filename, contents, studentId);
    }
}