import ru.homework.java.Spiral;

import static org.junit.Assert.assertEquals;

class SpiralTest {
    /**
     * test how Spiral works with different size of matrix. Include tests for methods spiral() and sort.
     */
    @org.junit.jupiter.api.Test
    void spiralTestSimple() {
        int[][] m = new int[1][1];
        int c = 1;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                m[i][j] = c++;
            }
        }
        assertEquals(Spiral.spiral(m), "1");
    }

    @org.junit.jupiter.api.Test
    void spiralTestThree() {
        int[][] m = new int[3][3];
        int c = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m[i][j] = c++;
            }
        }
        assertEquals(Spiral.spiral(m), "569874123");
    }

    @org.junit.jupiter.api.Test
    void spiralTestFive() {
        int[][] m = new int[5][5];
        int c = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                m[i][j] = c++;
            }
        }
        assertEquals(Spiral.spiral(m), "13141918171278910152025242322211611612345");
    }

    @org.junit.jupiter.api.Test
    void spiralTestEvenSize() {
        int[][] m = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                m[i][j] = 0;
            }
        }
        try {
            Spiral.spiral(m);
        } catch (IllegalArgumentException thrown) {
            assertEquals("Even matrix size", thrown.getMessage());
        }
    }

    /**
     * sort matrix size [1x1]
     */
    @org.junit.jupiter.api.Test
    void spiralTestSimpleSort() {
        int[][] m = new int[1][1];
        int c = 1;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                m[i][j] = c++;
            }
        }
        Spiral.sortMatrix(m);
        assertEquals(m[0][0], 1);
    }

    /**
     * sort matrix size [5x5] full numbers from 1 to 25
     */
    @org.junit.jupiter.api.Test
    void spiralTestSort() {
        int[][] m = new int[5][5];
        int c = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                m[i][j] = c++;
            }
        }
        Spiral.sortMatrix(m);
        int[][] n = new int[5][5];
        for (int i = 0; i < 5; i++) {
            c = (i + 1) * 5;
            for (int j = 0; j < 5; j++) {
                n[i][j] = c - j;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(m[i][j], n[i][j]);
            }
        }
    }
}