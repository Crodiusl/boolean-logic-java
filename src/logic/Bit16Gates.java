package logic;

public abstract class Bit16Gates extends BooleanLogic {
    /*
     * REMINDER:
     * Implementation is repetitive because we can't use loops
     * as per project constraints (simulating hardware gates)
     * https://youtu.be/5_vVGPy4-rc?si=Y2SyAe1SlQL7v2qY
     */

    public static int[] NOT16(int[] in) {
        int[] out = new int[16];

        out[0] = NOT(in[0]);
        out[1] = NOT(in[1]);
        out[2] = NOT(in[2]);
        out[3] = NOT(in[3]);
        out[4] = NOT(in[4]);
        out[5] = NOT(in[5]);
        out[6] = NOT(in[6]);
        out[7] = NOT(in[7]);
        out[8] = NOT(in[8]);
        out[9] = NOT(in[9]);
        out[10] = NOT(in[10]);
        out[11] = NOT(in[11]);
        out[12] = NOT(in[12]);
        out[13] = NOT(in[13]);
        out[14] = NOT(in[14]);
        out[15] = NOT(in[15]);

        return out;
    }

    public static int[] AND16(int[] a, int[] b) {
        int[] out = new int[16];

        out[0] = AND(a[0], b[0]);
        out[1] = AND(a[1], b[1]);
        out[2] = AND(a[2], b[2]);
        out[3] = AND(a[3], b[3]);
        out[4] = AND(a[4], b[4]);
        out[5] = AND(a[5], b[5]);
        out[6] = AND(a[6], b[6]);
        out[7] = AND(a[7], b[7]);
        out[8] = AND(a[8], b[8]);
        out[9] = AND(a[9], b[9]);
        out[10] = AND(a[10], b[10]);
        out[11] = AND(a[11], b[11]);
        out[12] = AND(a[12], b[12]);
        out[13] = AND(a[13], b[13]);
        out[14] = AND(a[14], b[14]);
        out[15] = AND(a[15], b[15]);

        return out;
    }

    public static int[] OR16(int[] a, int[] b) {
        int[] out = new int[16];

        out[0] = OR(a[0], b[0]);
        out[1] = OR(a[1], b[1]);
        out[2] = OR(a[2], b[2]);
        out[3] = OR(a[3], b[3]);
        out[4] = OR(a[4], b[4]);
        out[5] = OR(a[5], b[5]);
        out[6] = OR(a[6], b[6]);
        out[7] = OR(a[7], b[7]);
        out[8] = OR(a[8], b[8]);
        out[9] = OR(a[9], b[9]);
        out[10] = OR(a[10], b[10]);
        out[11] = OR(a[11], b[11]);
        out[12] = OR(a[12], b[12]);
        out[13] = OR(a[13], b[13]);
        out[14] = OR(a[14], b[14]);
        out[15] = OR(a[15], b[15]);

        return out;
    }

    public static int OR8WAY(int[] in) {
        int a = OR(in[0], in[1]);
        int b = OR(a, in[2]);
        int c = OR(b, in[3]);
        int d = OR(c, in[4]);
        int e = OR(d, in[5]);
        int f = OR(e, in[6]);

        return OR(f, in[7]);
    }

    public static int OR16WAY(int[] in) {

        int[] in0to7 = new int[8];
        in0to7[0] = in[0];
        in0to7[1] = in[1];
        in0to7[2] = in[2];
        in0to7[3] = in[3];
        in0to7[4] = in[4];
        in0to7[5] = in[5];
        in0to7[6] = in[6];
        in0to7[7] = in[7];
        int[] in8to15 = new int[8];
        in8to15[0] = in[8];
        in8to15[1] = in[9];
        in8to15[2] = in[10];
        in8to15[3] = in[11];
        in8to15[4] = in[12];
        in8to15[5] = in[13];
        in8to15[6] = in[14];
        in8to15[7] = in[15];

        int or0to7 = OR8WAY(in0to7);
        int or8to15 = OR8WAY(in8to15);

        return OR(or0to7, or8to15);
    }

}
