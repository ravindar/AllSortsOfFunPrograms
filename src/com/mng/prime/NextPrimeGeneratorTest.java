package com.mng.prime;

import org.junit.Assert;
import org.junit.Test;

public class NextPrimeGeneratorTest {

	@Test
    public void generateNextPrimesAfterZero() {
        int result = NextPrimeGenerator.nextPrimeAfter(0);
        Assert.assertEquals(2, result);
    }
    
    @Test
    public void generateNextPrimesAfterOne(){
        int result = NextPrimeGenerator.nextPrimeAfter(1);
        Assert.assertEquals(2, result);
    }
    
    @Test
    public void generateNextPrimesAfterTwo(){
        int result = NextPrimeGenerator.nextPrimeAfter(2);
        Assert.assertEquals(3, result);
    }
    
    @Test
    public void generateNextPrimesAfterThree(){
    	int result = NextPrimeGenerator.nextPrimeAfter(3);
    	Assert.assertEquals(5, result);
    }

    @Test
    public void generateNextPrimesAfterFour(){
    	int result = NextPrimeGenerator.nextPrimeAfter(4);
    	Assert.assertEquals(5, result);
    }

    @Test
    public void generateNextPrimesAfterFive(){
    	int result = NextPrimeGenerator.nextPrimeAfter(5);
    	Assert.assertEquals(7, result);
    }

    @Test
    public void generateNextPrimesAfterSeven(){
    	int result = NextPrimeGenerator.nextPrimeAfter(7);
    	Assert.assertEquals(11, result);
    }

    @Test
    public void generateNextPrimesAfterEighty(){
    	int result = NextPrimeGenerator.nextPrimeAfter(80);
    	Assert.assertEquals(83, result);
    }

    @Test
    public void generateNextPrimesAfterEightyThree(){
    	int result = NextPrimeGenerator.nextPrimeAfter(83);
    	Assert.assertEquals(89, result);
    }

    @Test
    public void generateNextPrimesAfterNinty(){
    	int result = NextPrimeGenerator.nextPrimeAfter(90);
    	Assert.assertEquals(97, result);
    }
    
    @Test
    public void generateNextPrimesAfterOneMillion(){
    	int result = NextPrimeGenerator.nextPrimeAfter(1000000);
    	Assert.assertEquals(1000003, result);
    }

    @Test
    public void generateNextPrimesAfterTenMillion(){
    	int result = NextPrimeGenerator.nextPrimeAfter(10000000);
    	Assert.assertEquals(10000019, result);
    }
}

