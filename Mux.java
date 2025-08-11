package logic;

public abstract class Mux extends BooleanLogic {

    public static int MUX(int a, int b, int sel) {
        return OR(AND(NOT(sel), a), AND(sel, b));
    }

    public static int[] DMUX(int in, int sel) {
        return new int[]{AND(NOT(sel), in), AND(sel, in)};
    }

    public static int[] MUX16(int[] a, int[] b, int sel) {
        int[] out = new int[16];

        out[0] = MUX(a[0], b[0], sel);
        out[1] = MUX(a[1], b[1], sel);
        out[2] = MUX(a[2], b[2], sel);
        out[3] = MUX(a[3], b[3], sel);
        out[4] = MUX(a[4], b[4], sel);
        out[5] = MUX(a[5], b[5], sel);
        out[6] = MUX(a[6], b[6], sel);
        out[7] = MUX(a[7], b[7], sel);
        out[8] = MUX(a[8], b[8], sel);
        out[9] = MUX(a[9], b[9], sel);
        out[10] = MUX(a[10], b[10], sel);
        out[11] = MUX(a[11], b[11], sel);
        out[12] = MUX(a[12], b[12], sel);
        out[13] = MUX(a[13], b[13], sel);
        out[14] = MUX(a[14], b[14], sel);
        out[15] = MUX(a[15], b[15], sel);

        return out;
    }

    /*
     * IMPORTANT:
     * Real world BUS will be indexed from right to left,
     * but arrays are indexed from left to right
     * beacuse of that we reverse every real-world (HDL) index to array index
     * BUS: 110
     * Array: [1, 1, 0]
     * in BUS bit at index 0 is 0
     * in Array bit at index 0 is 1
     *
     * NOTE: population of array stays withou index change, just populate it as usual in Java
     */
    public static int[] MUX4WAY16(int[] a, int[] b, int[] c, int[] d, int[] sel) {
        int[] ab = MUX16(a, b, sel[1]); // HDL i=0
        int[] cd = MUX16(c, d, sel[1]);

        return MUX16(ab, cd, sel[0]); // HDL i=1
    }

    public static int[] MUX8WAY16(int[] a, int[] b, int[] c, int[] d,
                                  int[] e, int[] f, int[] g, int[] h,
                                  int[] sel) {
        int[] ab = MUX16(a, b, sel[2]); // HDL i=0
        int[] cd = MUX16(c, d, sel[2]);
        int[] ef = MUX16(e, f, sel[2]);
        int[] gh = MUX16(g, h, sel[2]);

        int[] abcd = MUX16(ab, cd, sel[1]); // HDL i=1 (middle)
        int[] efgh = MUX16(ef, gh, sel[1]);

        return MUX16(abcd, efgh, sel[0]); // HDL i=2
    }

    public static int[] DMUX4WAY(int in, int[] sel) {
        int[] out = new int[4];

        int[] abcd = DMUX(in, sel[0]); // HDL i=1

        int[] ab = DMUX(abcd[0], sel[1]); // HDL i=0
        int[] cd = DMUX(abcd[1], sel[1]);

        out[0] = ab[0];
        out[1] = ab[1];
        out[2] = cd[0];
        out[3] = cd[1];

        return out;
    }

    public static int[] DMUX8WAY(int in, int[] sel) {
        int[] out = new int[8];

        int[] abcdefgh = DMUX(in, sel[0]); // HDL i=2


        int[] abcd = DMUX(abcdefgh[0], sel[1]); // HDL i=1 (middle)
        int[] efgh = DMUX(abcdefgh[1], sel[1]);

        int[] ab = DMUX(abcd[0], sel[2]); // HDL i=0
        int[] cd = DMUX(abcd[1], sel[2]);
        int[] ef = DMUX(efgh[0], sel[2]);
        int[] gh = DMUX(efgh[1], sel[2]);

        out[0] = ab[0];
        out[1] = ab[1];

        out[2] = cd[0];
        out[3] = cd[1];

        out[4] = ef[0];
        out[5] = ef[1];

        out[6] = gh[0];
        out[7] = gh[1];

        return out;
    }

}
