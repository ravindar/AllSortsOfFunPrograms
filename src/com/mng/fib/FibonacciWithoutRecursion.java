package com.mng.fib;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciWithoutRecursion {
	public static int[] generateUpTo(int n){
		int[] response = new int[n];
		response[0] = 0;
		response[1] = 1;
		for(int i =2; i<n ;i++){
			response[i] = response[i-1]+response[i-2];
		}
		return response;
	}

	@Test
	public void testGenerateFib(){
		int[] response = generateUpTo(10);
		Assert.assertArrayEquals(new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34}, response);
	}
}
