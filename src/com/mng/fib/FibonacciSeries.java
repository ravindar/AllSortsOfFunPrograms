package com.mng.fib;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciSeries {
	public static int[] generateUpTo(int n){
		int[] response = new int[n];
		for(int i =0; i<n ;i++){
			response[i] = fib(i);
		}
		return response;
	}

	private static int fib(int n) {
		if(n == 0) return 0;
		if(n == 1) return 1;
		
		return fib(n-1) + fib(n-2);
	}
	
	@Test
	public void testGenerateFib(){
		int[] response = generateUpTo(10);
		Assert.assertArrayEquals(new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34}, response);
	}
}
