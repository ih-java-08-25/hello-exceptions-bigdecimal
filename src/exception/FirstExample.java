package exception; // (Tip: 'exceptions' is a clearer package name, but this works)

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FirstExample {

/*
Exceptions quick ref:
- Checked (extends Exception, not RuntimeException): external/recoverable; must catch OR declare with `throws`.
  Example: new FileInputStream(name) → FileNotFoundException.
- Unchecked (extends RuntimeException): programming errors/invalid input; no catch/throws required.
  Examples: NullPointerException, IllegalArgumentException.
- Handle where you can act (boundary), otherwise declare and propagate.
- Prevent unchecked via validation
*/

    public static void main(String[] args) {
        String fileName = "notes.txt";

        // 1) CHECKED: handle right where we call it (try/catch here)
        demoChecked_HandleInPlace(fileName);

        // 2) CHECKED: propagate from a helper (method declares throws; caller catches)
        demoChecked_PropagateFromHelper(fileName);

        // 3) UNCHECKED: show typical programming-error style exceptions
        demoUnchecked();

        System.out.println("This line is reachable even if exceptions happened above.");
    }

    // ====== 1) CHECKED — handle in place ======
    private static void demoChecked_HandleInPlace(String fileName) {
        System.out.println("\n[Checked] Handle in place:");
        try (Scanner sc = new Scanner(new FileInputStream(fileName))) {
            // This is a ternary, same as an if, just in one line: condition ? if condition is true do/return this : else do/return this;
            String firstLine = sc.hasNextLine() ? sc.nextLine() : "(EMPTY FILE)";
            System.out.println("First line: " + firstLine);
        } catch (FileNotFoundException e) {
            // Checked: must catch or declare
            System.err.println("Couldn't open " + fileName + ": " + e.getMessage());
        }
    }

    // ====== 2) CHECKED — propagate to caller (caller decides how to handle) ======
    private static void demoChecked_PropagateFromHelper(String fileName) {
        System.out.println("\n[Checked] Propagate from helper:");
        try {
            String firstLine = readFirstLine(fileName); // may throw FileNotFoundException
            System.out.println("First line: " + firstLine);
        } catch (FileNotFoundException e) {
            System.err.println("File missing (from helper): " + e.getMessage());
        }
    }

    // The helper declares the checked exception. Caller MUST catch or declare.
    private static String readFirstLine(String fileName) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new FileInputStream(fileName))) {
            return sc.hasNextLine() ? sc.nextLine() : "(EMPTY FILE)";
        }
    }

    // ====== 3) UNCHECKED — usually programming errors / invalid inputs ======
    private static void demoUnchecked() {
        System.out.println("\n[Unchecked] Examples:");

        // Example A: NullPointerException (we avoid it by validating)
        String name = null;
        if (name != null) {
            System.out.println(name.toUpperCase());
        } else {
            System.out.println("Name is null (avoided NullPointerException by checking).");
        }

        // Example B: IllegalArgumentException for invalid input
        System.out.println("safeDivide(10, 2) = " + safeDivide(10, 2));
        try {
            System.out.println("safeDivide(10, 0) = " + safeDivide(10, 0)); // will throw
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid divide: " + e.getMessage());
        }
    }

    private static int safeDivide(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Divider must not be zero.");
        return a / b;
    }
}

