import static org.junit.Assert.assertEquals;

class SpiralTest {
    /**
     * test how Spiral works with different arguments.
     * Include test for Object = Integer.
     */
    @org.junit.jupiter.api.Test
    void spiralTestSimple() {
        Integer[][] m = new Integer[1][1];
        int c = 1;
        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 1; j++) {
                m[i][j] = c++;
            }
        assertEquals(Spiral.spiral(m), "1");
    }

    @org.junit.jupiter.api.Test
    void spiralTestThree() {
        Integer[][] m = new Integer[3][3];
        int c = 1;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                m[i][j] = c++;
            }
        assertEquals(Spiral.spiral(m), "569874123");
    }

    @org.junit.jupiter.api.Test
    void spiralTestFive() {
        Integer[][] m = new Integer[5][5];
        int c = 1;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                m[i][j] = c++;
            }
        assertEquals(Spiral.spiral(m), "13141918171278910152025242322211611612345");
    }

    @org.junit.jupiter.api.Test
    void spiralTestSimpleSort() {
        Integer[][] m = new Integer[1][1];
        int c = 1;
        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 1; j++) {
                m[i][j] = c++;
            }
        Spiral.sortMatrix(m);
        assertEquals(m[0][0].toString(), "1");
    }

    @org.junit.jupiter.api.Test
    void spiralTestSort() {
        Integer[][] m = new Integer[5][5];
        int c = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                m[i][j] = c++;
            }
        }
        Spiral.sortMatrix(m);
        Integer[][] n = new Integer[5][5];
        for (int i = 0; i < 5; i++) {
            c = (i + 1) * 5;
            for (int j = 0; j < 5; j++) {
                n[i][j] = c - j;
            }
        }
        for (int i = 0 ; i < 5; i++) {
            for (int j = 0; j < 5; j++){
                assertEquals(m[i][j], n[i][j]);
            }
        }
    }
}