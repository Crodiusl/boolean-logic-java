import java.util.Arrays;
import java.util.Random;

import static logic.Alu.*;

public class PlayGround {

    public static void main(String[] args) {

        int[] a;
        int[] b;

        int isXZero = 0;
        int isXInverted = 0;
        int isYZero = 0;
        int isYInverted = 0;
        int addOrand = 1;
        int isFinalInverted = 0;

        Random random = new Random();
        System.out.println("16-bit");
        for (int i = 0; i < 30; i++) {
            short x = (short) random.nextInt(-1000, 1000);
            short y = (short) random.nextInt(-1000, 1000);

            a = numberToBinaryArray(x);
            b = numberToBinaryArray(y);

            int[][] out = ALU(a, b, isXZero, isXInverted, isYZero, isYInverted, addOrand, isFinalInverted);
            short excpected = (short) (x + y);
            short ans = binaryArrayToNumber(out[0]);

            System.out.printf("a = %-6d b = %-6d SUM = %-7d ALU = %-7d%n", x, y, excpected, ans);
        }

    }

    public static int[] numberToBinaryArray(short number) {
        int[] binaryArray = new int[16];
        for (int i = 15; i >= 0; i--) {
            binaryArray[15 - i] = (number >> i) & 1;
        }
        return binaryArray;
    }

    // Метод для перевода 16-битного массива обратно в число
    public static short binaryArrayToNumber(int[] binaryArray) {
//        if (binaryArray.length != 16) {
//            throw new IllegalArgumentException("Массив должен содержать ровно 16 элементов (0 или 1)");
//        }
        short number = 0;
        for (int i = 0; i < 16; i++) {
//            if (binaryArray[i] != 0 && binaryArray[i] != 1) {
//                throw new IllegalArgumentException("Элементы массива должны быть 0 или 1");
//            }
            number = (short) ((number << 1) | binaryArray[i]);
        }
        return number;
    }

}
