package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static logic.BooleanLogic.*;

class BooleanLogicTest {

    private final int[] a = {0, 0, 1, 1};
    private final int[] b = {0, 1, 0, 1};

    @Test
    void shouldBeEqualAND() {
        int[] excpected = {0, 0, 0, 1};

        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], AND(a[i], b[i]));
        }
    }

    @Test
    void shouldBeEqualNOT() {
        int[] excpected = {1, 1, 0, 0};

        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], NOT(a[i]));
        }
    }

    @Test
    void shouldBeEqualNAND() {
        int[] excpected = {1, 1, 1, 0};

        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], NAND(a[i], b[i]));
        }
    }

    @Test
    void shouldBeEqualNOTFromNAND() {
        int[] excpected = {1, 1, 0, 0};
        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], NOTFromNAND(a[i]));
        }
    }

    @Test
    void shouldBeEqualANDFromNAND() {
        int[] excpected = {0, 0, 0, 1};

        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], ANDFromNAND(a[i], b[i]));
        }
    }

    @Test
    void shouldBeEqualOR() {
        int[] excpected = {0, 1, 1, 1};

        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], OR(a[i], b[i]));
        }
    }

    @Test
    void shouldBeEqualNOR() {
        int[] excpected = {1, 0, 0, 0};

        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], NOR(a[i], b[i]));
        }
    }

    @Test
    void shouldBeEqualXOR() {
        int[] excpected = {0, 1, 1, 0};

        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], XOR(a[i], b[i]));
        }
    }

    @Test
    void shouldBeEqualXNOR() {
        int[] excpected = {1, 0, 0, 1};

        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], XNOR(a[i], b[i]));
        }
    }

    @Test
    void shouldBeEqualIMPLY() {
        int[] excpected = {1, 1, 0, 1};

        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], IMPLY(a[i], b[i]));
        }
    }

    @Test
    void shouldBeEqualNIMPLY() {
        int[] excpected = {0, 0, 1, 0};

        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], NIMPLY(a[i], b[i]));
        }
    }

    @Test
    void shouldBeEqualintToBoolean() {
        boolean[] excpected = {false, false, true, true};

        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], IntToBoolean(a[i]));
        }
    }

    @Test
    void shouldBeEqualbooleanToInt() {
        boolean[] in = {false, false, true, true};
        int[] excpected = {0, 0, 1, 1};

        for (int i = 0; i < excpected.length; i++) {
            assertEquals(excpected[i], BooleanToInt(in[i]));
        }
    }


}