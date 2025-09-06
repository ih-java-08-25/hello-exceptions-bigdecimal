package exception.custom;

/*
Checked vs Unchecked quick note:
- Checked (extends Exception): caller MUST catch or declare (good for recoverable, expected cases).
- Unchecked (extends RuntimeException): caller is NOT forced (good for programming errors).
*/

public class CustomExceptionEx {
    public static void main(String[] args) {
        int age = 17;

        try {
            validateAge(age); // may throw InvalidAgeException (checked)
            System.out.println("Welcome! You can enter the application.");
        } catch (InvalidAgeException e) {
            System.err.println("Registration failed: " + e.getMessage());
            // Here you could ask the user to re-enter age, or route to a help screen, etc.
        }

        // Try a valid age to see the success path
        try {
            validateAge(20);
            System.out.println("Welcome! You can enter the application.");
        } catch (InvalidAgeException ignored) { /* not reached */ }
    }

    // Business rule: must be 18+
    // Because InvalidAgeException is CHECKED, we declare it with 'throws'.
    private static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("User must be 18 or older.");
        }
    }
}

/*
If you want this to be UNCHECKED instead, change the base class:
class InvalidAgeException extends RuntimeException { ... }
Then remove 'throws InvalidAgeException' from validateAge and callers.
*/


