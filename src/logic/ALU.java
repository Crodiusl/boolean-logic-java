package logic;

import static logic.Bit16Gates.*;
import static logic.Mux.MUX16;

public abstract class ALU extends BooleanLogic {
    public static final int[] constOne = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
    public static final int[] constZero = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static int[] HALFADDER(int a, int b) {
        // Xor = sum, return 1 when only one of them is 1
        // And = carry returns 1 when a=1 and b=1
        return new int[]{XOR(a, b), AND(a, b)};
    }

    public static int[] FULLADDER(int a, int b, int c) {
        int[] sumCarryAB = HALFADDER(a, b);
        int sumAB = sumCarryAB[0];
        int carryAB = sumCarryAB[1];

        int[] sumCarryABC = HALFADDER(sumAB, c);
        int sumABC = sumCarryABC[0];
        int carryABC = sumCarryABC[1];

        int finalCarry = OR(carryAB, carryABC);

        return new int[]{sumABC, finalCarry};
    }

    /*
     * IMPORTANT:
     * BUS index != Array index;
     * we can't use loops
     * as per project constraints
     */
    public static int[] ADD16(int[] a, int[] b) {
        // The most significant carry bit is ignored
        int[] out = new int[16];

        int[] sumCarry = HALFADDER(a[15], b[15]);
        out[15] = sumCarry[0];
        int carry = sumCarry[1];

        sumCarry = FULLADDER(a[14], b[14], carry);
        out[14] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[13], b[13], carry);
        out[13] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[12], b[12], carry);
        out[12] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[11], b[11], carry);
        out[11] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[10], b[10], carry);
        out[10] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[9], b[9], carry);
        out[9] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[8], b[8], carry);
        out[8] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[7], b[7], carry);
        out[7] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[6], b[6], carry);
        out[6] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[5], b[5], carry);
        out[5] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[4], b[4], carry);
        out[4] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[3], b[3], carry);
        out[3] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[2], b[2], carry);
        out[2] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[1], b[1], carry);
        out[1] = sumCarry[0];
        carry = sumCarry[1];

        sumCarry = FULLADDER(a[0], b[0], carry);
        out[0] = sumCarry[0];
        // we ignore last carry

        return out;
    }

    public static int[] INC16(int[] in) {
        // we need to increment only by one, so we use constant array
        return ADD16(in, constOne);
    }

    public static int[][] ALU(int[] x, int[] y,
                              int isXZero, int isXInverted,
                              int isYZero, int isYInverted,
                              int addOrand, int isFinalInverted) {

        /**
         *     operation    | isXZero | isXInverted | isYZero | isYInverted | addOrand | isFinalInverted |
         * out = 0          |    1    |      0      |    1    |      0      |    1     |        0        |
         * out = 1          |    1    |      1      |    1    |      1      |    1     |        1        |
         * out = -1         |    1    |      1      |    1    |      0      |    1     |        0        |
         * out = x          |    0    |      0      |    1    |      1      |    0     |        0        |
         * out = y          |    1    |      1      |    0    |      0      |    0     |        0        |
         * out = !x         |    0    |      0      |    1    |      1      |    0     |        1        |
         * out = !y         |    1    |      1      |    0    |      0      |    0     |        1        |
         * out = -x         |    0    |      0      |    1    |      1      |    1     |        1        |
         * out = -y         |    1    |      1      |    0    |      0      |    1     |        1        |
         * out = x + 1      |    0    |      1      |    1    |      1      |    1     |        1        |
         * out = y + 1      |    1    |      1      |    0    |      1      |    1     |        1        |
         * out = x - 1      |    0    |      0      |    1    |      1      |    1     |        0        |
         * out = y - 1      |    1    |      1      |    0    |      0      |    1     |        0        |
         * out = x + y      |    0    |      0      |    0    |      0      |    1     |        0        |
         * out = x - y      |    0    |      1      |    0    |      0      |    1     |        1        |
         * out = y - x      |    0    |      0      |    0    |      1      |    1     |        1        |
         * out = x & y      |    0    |      0      |    0    |      0      |    0     |        0        |
         * out = x | y      |    0    |      1      |    0    |      1      |    0     |        1        |
         * */
        /*
         * ALU (Arithmetic Logic Unit):
         * Computes out = one of the following functions:
         *                0, 1, -1,
         *                x, y, !x, !y, -x, -y,
         *                x + 1, y + 1, x - 1, y - 1,
         *                x + y, x - y, y - x,
         *                x & y, x | y
         * on the 16-bit inputs x, y,
         * according to the input bits.
         * In addition, computes the two output bits:
         * if (out == 0) isZero = 1, else isZero = 0
         * if (out < 0)  isNegative = 1, else isNegative = 0
         */
        int[] probzerox = MUX16(x, constZero, isXZero);
        int[] notx = NOT16(probzerox);
        int[] xval = MUX16(probzerox, notx, isXInverted);

        int[] probzeroy = MUX16(y, constZero, isYZero);
        int[] noty = NOT16(probzeroy);
        int[] yval = MUX16(probzeroy, noty, isYInverted);

        int[] xyand = AND16(xval, yval);
        int[] xysum = ADD16(xval, yval);
        int[] out1 = MUX16(xyand, xysum, addOrand);

        int[] notout1 = NOT16(out1);
        int[] out = MUX16(out1, notout1, isFinalInverted);

        int or0to16 = OR16WAY(out);

        int isZero = NOT(or0to16);
        int isNegative = out[0]; // bus index != array index

        return new int[][]{out, {isZero}, {isNegative}};
    }


}
