package com.mng.prime;

import org.junit.Assert;
import org.junit.Test;

public class PrimeGeneratorTest {
    
    @Test
    public void generatePrimesUptoZero() {
        int[] result = PrimeGenerator.generatePrimes(0);
        Assert.assertEquals(0, result.length);
    }
    
    @Test
    public void generatePrimesUptoTwo(){
        int[] result = PrimeGenerator.generatePrimes(2);
        Assert.assertEquals(1, result.length);
        Assert.assertEquals(2, result[0]);
    }
    
    @Test
    public void generatePrimesUptoThree(){
        int[] result = PrimeGenerator.generatePrimes(3);
        Assert.assertEquals(2, result.length);
        Assert.assertEquals(2, result[0]);
        Assert.assertEquals(3, result[1]);
    }
    
    @Test
    public void generatePrimesUptoTen(){
    	int[] result = PrimeGenerator.generatePrimes(10);
    	Assert.assertEquals(4, result.length);
    	Assert.assertEquals(2, result[0]);
    	Assert.assertEquals(3, result[1]);
    	verifyPrimeList(result);
    }
    
    @Test
    public void generatePrimesUptoHundred(){
        int[] result = PrimeGenerator.generatePrimes(100);
        Assert.assertEquals(25, result.length);
        Assert.assertEquals(2, result[0]);
        Assert.assertEquals(3, result[1]);
        Assert.assertEquals(97, result[24]);
    }
    
    @Test
    public void generatePrimesExhastively(){
    	for (int i =2; i <500; i++)
    		verifyPrimeList (PrimeGenerator.generatePrimes(i));
    }

	private void verifyPrimeList(int[] list) {
		for (int i=0;i<list.length;i++)
			verifyPrime(list[i]);
	}

	private void verifyPrime(int n) {
		for(int factor = 2; factor <n; factor++)
			Assert.assertTrue(n%factor != 0);
	}
}
