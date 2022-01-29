package com.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.SeatingArragement;

public class AirplaneSeatTest {


   @Test
    public void testSeatFillGivenScenario() {
        int[][] givenInput =  {
                {2, 3},
                {3, 4},
                {3, 2},
                {4, 3}
        };
        int passengerCount = 30;

        int[][][] output = SeatingArragement.fillSeats(givenInput, passengerCount);

        Assert.assertEquals(output[0][0][givenInput[0][1] - 1], 1);//Always number 1
    }

    @Test
    public void testSeatFillCheckNoCenterSeatScenario() {
        int[][] givenInput =  {
                {2, 2},
                {3, 2},
                {3, 2},
                {4, 2}
        };
        int passengerCount = 30;

        int[][][] output = SeatingArragement.fillSeats(givenInput, passengerCount);

        Assert.assertEquals(output[0][0][givenInput[0][1] - 1], 1);//Always number 1
    }
    
    @Test
    public void testSeatFillCheckNoAisleSeatScenario() {
        int[][] givenInput =  {
                {2, 1},
                {3, 2},
                {3, 2},
                {4, 2}
        };
        int passengerCount = 30;

        int[][][] output = SeatingArragement.fillSeats(givenInput, passengerCount);

        Assert.assertNotEquals(output[0][0][givenInput[0][1] - 1], 1);//Always number 1
    }
    
}
