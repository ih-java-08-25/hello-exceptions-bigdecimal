package bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        demoCreation();
        demoBasicOps();
        demoDivision();
        demoScaleAndRounding();
        demoEqualityVsCompare();
    }

    private static void demoCreation() {
        System.out.println("---- Creating BigDecimal correctly ----");
        BigDecimal fromString = new BigDecimal("0.1");        // ✅ safest (exact decimal)
        BigDecimal viaValueOf = BigDecimal.valueOf(0.2);       // ✅ OK (uses Double.toString)
        BigDecimal wrongDouble = new BigDecimal("0.2");           // ❌ DON'T (binary floating artifact)

        System.out.println("fromString     = " + fromString.toPlainString());
        System.out.println("viaValueOf     = " + viaValueOf.toPlainString());
        System.out.println("wrong (double) = " + wrongDouble.toPlainString());
    }

    private static void demoBasicOps() {
        System.out.println("\n---- Basic operations (immutable) ----");
        BigDecimal a = new BigDecimal("2.50");
        BigDecimal b = new BigDecimal("1.25");

        BigDecimal sum = a.add(b);
        BigDecimal difference = a.subtract(b);
        BigDecimal product = a.multiply(b);

        System.out.println("SUM         : " + sum);
        System.out.println("DIFFERENCE  : " + difference);
        System.out.println("PRODUCT     : " + product);
        System.out.println("A = " + a + ", B = " + b);
    }

    // Division needs scale+rounding when not exact
    private static void demoDivision() {
        System.out.println("\n---- Divisions ----");
        BigDecimal one = BigDecimal.ONE;
        BigDecimal three = new BigDecimal("3");

        try {
            one.divide(three); // throws ArithmeticException (non-terminating decimal)
        } catch (ArithmeticException e) {
            System.out.println("Exact divide failed: " + e.getMessage());
        }

        BigDecimal third2 = one.divide(three, 2, RoundingMode.HALF_UP);
        BigDecimal third4 = one.divide(three, 4, RoundingMode.HALF_UP);

        System.out.println("1/3 (2 dp, HALF_UP): " + third2);
        System.out.println("1/3 (4 dp, HALF_UP): " + third4);
    }

    private static void demoScaleAndRounding() {
        System.out.println("\n---- Setting the scale & RoundingMode ----");
        BigDecimal n = new BigDecimal("2.355");

        System.out.println("Original            : " + n);
        System.out.println("2 dp, HALF_UP       : " + n.setScale(2, RoundingMode.HALF_UP));
        System.out.println("2 dp, UP            : " + n.setScale(2, RoundingMode.UP));
        System.out.println("2 dp, HALF_DOWN     : " + n.setScale(2, RoundingMode.HALF_DOWN));
        System.out.println("2 dp, DOWN          : " + n.setScale(2, RoundingMode.DOWN));

        // Bankers' rounding (HALF_EVEN) demo on ties:
        BigDecimal tie1 = new BigDecimal("2.345");
        BigDecimal tie2 = new BigDecimal("2.355");
        System.out.println("2.345 → 2 dp, HALF_EVEN: " + tie1.setScale(2, RoundingMode.HALF_EVEN)); // → 2.34
        System.out.println("2.355 → 2 dp, HALF_EVEN: " + tie2.setScale(2, RoundingMode.HALF_EVEN)); // → 2.36
    }

    private static void demoEqualityVsCompare() {
        System.out.println("\n---- Equality and Comparison ----");
        BigDecimal c = new BigDecimal("2.50");
        BigDecimal d = new BigDecimal("2.5");

        System.out.println("c.equals(d) ?        " + c.equals(d));                 // false (different scale)
        System.out.println("c.compareTo(d) == 0? " + (c.compareTo(d) == 0));       // true  (numerically equal)
        System.out.println("Strip zeros equals?  " +
                c.stripTrailingZeros().equals(d.stripTrailingZeros()));
    }
}

