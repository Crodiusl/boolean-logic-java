package logic;

public class BooleanLogic {
    private static final int ZERO = 0;
    private static final int ONE = 1;

    public static int AND(int x, int y) {
        return x * y;
    }

    public static int NOT(int n) {
        return ONE - n;
    }

    public static int NAND(int x, int y) {
        return NOT(AND(x, y));
    }

    public static int OR(int x, int y) {
        return NOT(AND(NOT(x), NOT(y)));
    }

    public static int XOR(int x, int y) {
        return OR(AND(NOT(x), y), AND(x, NOT(y)));
    }

    public static int EQUAL(int x, int y) {
        return OR(AND(x, y), AND(NOT(x), NOT(y)));
    }

    public static int IFX(int x, int y) {
        return OR(NOT(x), y);
    }

    public static int IFY(int x, int y) {
        return OR(x, NOT(y));
    }

}
