package exception.unchecked;

public class UncheckedExceptionEx {

    public static void main(String[] args) {
        // Demo 1: Raw division => ArithmeticException (unchecked) if b == 0
        try {
            System.out.println("[raw] 1 / 0 = " + divideRaw(1, 0));
        } catch (ArithmeticException e) {
            System.err.println("[raw] Unchecked exception caught: " + e);
        }

        // Demo 2: Validate and explicitly throw an unchecked exception with a clear message
        try {
            System.out.println("[throw] 1 / 0 = " + divideOrThrow(1, 0));
        } catch (IllegalArgumentException e) {
            System.err.println("[throw] Bad input: " + e.getMessage());
        }

        // Demo 3: Validate and return a documented default value instead of throwing
        int result = divideOrDefault(1, 0, 0);
        System.out.println("[default] 1 / 0 (default 0) = " + result);
    }

    // --- Pattern 1: Raw computation (will throw ArithmeticException if b == 0)
    // Unchecked: ArithmeticException extends RuntimeException → no 'throws' needed.
    private static int divideRaw(int a, int b) {
        return a / b; // may throw ArithmeticException at runtime
    }

    // --- Pattern 2: Validate and throw a clear unchecked exception
    // Good for LIBRARY/UTILITY code: forces callers (wherever this method is used-called) to pass valid inputs or handle the error.
    private static int divideOrThrow(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Divider must not be zero.");
        return a / b;
    }

    // --- Pattern 3: Validate and return a default value
    // Good for APP/BOUNDARY code (e.g., UI) when you prefer not to fail and you have a sensible fallback.
    // IMPORTANT: document the default behavior so it's not surprising.
    private static int divideOrDefault(int a, int b, int defaultValue) {
        if (b == 0) return defaultValue;
        return a / b;
    }
}

