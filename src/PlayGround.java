import java.util.Arrays;

import static logic.Alu.*;

public class PlayGround {

    public static void main(String[] args) {

        int[] a = numberToBinaryArray((short) 15);
        int[] b = numberToBinaryArray((short) 10);

        int isXZero = 0;
        int isXInverted = 0;
        int isYZero = 0;
        int isYInverted = 0;
        int addOrand = 1;
        int isFinalInverted = 0;


        int[][] out = ALU(a, b, isXZero, isXInverted, isYZero, isYInverted, addOrand, isFinalInverted);
        System.out.println(binaryArrayToNumber(out[0]));

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
