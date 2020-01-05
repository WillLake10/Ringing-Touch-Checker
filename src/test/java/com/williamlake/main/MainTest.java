package com.williamlake.main;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void numOfBellsValidCorrectInputTest() {
        int[] numOfBells = new int[3];
        Boolean result;
        numOfBells[0] = 4;
        numOfBells[1] = 8;
        numOfBells[2] = 12;

        for (int i = 0; i<=2; i++){
            result = Main.numOfBellsValid(numOfBells[i]);
            assertEquals(true,result);
        }
    }

    @Test
    public void numOfBellsValidIncorrectInputTest() {
        int[] numOfBells = new int[3];
        Boolean result;
        numOfBells[0] = 3;
        numOfBells[1] = 13;
        numOfBells[2] = 100;

        for (int i = 0; i<=2; i++){
            result = Main.numOfBellsValid(numOfBells[i]);
            assertEquals(false,result);
        }
    }
}
