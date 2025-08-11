package logic;

public abstract class BooleanLogic {

    public static final int ZERO = 0;
    public static final int ONE = 1;

    public static boolean IntToBoolean(int a) {
        return a == ONE;
    }

    public static int BooleanToInt(boolean b) {
        return b ? ONE : ZERO;
    }

    /*
     * Since this is only an imitation, we implement AND and NOT using arithmetic operations.
     * After implementing the NAND method, we could derive AND and NOT from it, though we won't use this approach here.
     *
     * We can`t use any builtIn logic operators or if() for() or while() in any methods,
     * so any multi-pin gate will be hardcoded
     *
     * Any bus will be represented as builtIn array
     *
     * NOTE: methods IntToBoolean and BooleanToInt use builtIn equality and ternary operator,
     * but these methods exsist only for purpose of actual usage of this class (if you are brave enough),
     * so the above rule does not apply for them
     */

    public static int AND(int a, int b) {
        return a * b;
    }

    public static int NOT(int n) {
        return ONE - n;
    }

    public static int NAND(int a, int b) {
        return NOT(AND(a, b));
    }

    // this methods purpose only to demonstrate the possibility of deriving anything from NAND
    public static int NOTFromNAND(int a) {
        return NAND(a, a);
    }

    public static int ANDFromNAND(int a, int b) {
        return NOT(NAND(a, b));
    }

    public static int OR(int a, int b) {
        return NOT(AND(NOT(a), NOT(b)));
    }

    public static int NOR(int a, int b) {
        return NOT(OR(a, b));
    }

    public static int XOR(int a, int b) {
        return OR(AND(NOT(a), b), AND(a, NOT(b)));
    }

    public static int XNOR(int a, int b) {
        // in other words - XNOR is EQUAL method
        return NOT(XOR(a, b));
    }

    public static int IMPLY(int a, int b) {
        return OR(NOT(a), b);
    }

    public static int NIMPLY(int a, int b) {
        return AND(a, NOT(b));
    }

}
