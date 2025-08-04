import java.util.Arrays;

import static logic.BooleanLogic.*;

public class Main {
    public static void main(String[] args) {
        String[] names = {
                "ZERO",
                "AND",
                "NOT X",
                "NOT Y",
                "NAND",
                "OR",
                "XOR",
                "EQUAL/XNOR",
                "IMPLY",
                "NIMPLY",
                "ONE"
        };
        String[][] result = new String[names.length][4];

        int[] x = {0, 0, 1, 1};
        int[] y = {0, 1, 0, 1};
        // 0
        for (int i = 0; i < 4; i++) {
            result[0][i] = "0";
        }
        // AND
        for (int i = 0; i < 4; i++) {
            result[1][i] = String.valueOf(AND(x[i], y[i]));
        }
        // NOT X
        for (int i = 0; i < 4; i++) {
            result[2][i] = String.valueOf(NOT(x[i]));
        }
        // NOT Y
        for (int i = 0; i < 4; i++) {
            result[3][i] = String.valueOf(NOT(y[i]));
        }
        // NAND
        for (int i = 0; i < 4; i++) {
            result[4][i] = String.valueOf(NAND(x[i], y[i]));
        }
        // OR
        for (int i = 0; i < 4; i++) {
            result[5][i] = String.valueOf(OR(x[i], y[i]));
        }
        // XOR
        for (int i = 0; i < 4; i++) {
            result[6][i] = String.valueOf(XOR(x[i], y[i]));
        }
        // EQUAL
        for (int i = 0; i < 4; i++) {
            result[7][i] = String.valueOf(XNOR(x[i], y[i]));
        }
        // IMPLY
        for (int i = 0; i < 4; i++) {
            result[8][i] = String.valueOf(IMPLY(x[i], y[i]));
        }
        // NIMPLY
        for (int i = 0; i < 4; i++) {
            result[9][i] = String.valueOf(NIMPLY(x[i], y[i]));
        }
        // 1
        for (int i = 0; i < 4; i++) {
            result[10][i] = "1";
        }
        System.out.println("X" + " ".repeat(11) + ": " + Arrays.toString(x));
        System.out.println("Y" + " ".repeat(11) + ": " + Arrays.toString(y));


        for (int i = 0; i < result.length; i++) {
            System.out.printf("%-11s : ", names[i]);
            System.out.println(Arrays.toString(result[i]));
        }
    }

}
