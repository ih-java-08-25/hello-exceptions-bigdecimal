package exception.slava;

import java.util.Objects;

public class SlavaEx {
    public static void main(String[] args) {
        String existingName = "Slava";
        String newName = "Slava";

        try {
            fakeSetName(newName, existingName);
            System.out.println("Name set successfully.");
        } catch (InvalidNameException e) {
            System.err.println(e.getMessage());
            // In a real app, you might ask the user for a different name here.
        }
    }

    // Simulates a setter that enforces uniqueness
    private static void fakeSetName(String newName, String existingName) {
        if (Objects.equals(newName, existingName)) {
            throw new InvalidNameException("'" + newName + "' already exists.");
        }

        // more code to update the name...
        // this is where you'd do: this.name = candidate;
    }
}
