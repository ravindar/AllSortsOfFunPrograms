package com.mng.binarysearch;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearch {
	public int level = 0;
	int binarySearch(int input[], int key, int low, int high) {
		int middle; 
		if (low > high)
			return (-1); 
		middle = (low + high) / 2;
		if (input[middle] == key){
			level++;
			return (middle);
		}
		if (input[middle] > key){
			level++;
			return (binarySearch(input, key, low, middle - 1));
		}else{
			level++;
			return (binarySearch(input, key, middle + 1, high));
		}
	}
	
	@Test
	public void testBinarySearch(){
		int[] input = new int[100];
		for(int i =0; i<100; i++){
			input[i] = i;
		}
		int foundAt = binarySearch(input, 2, 0, 99);
		Assert.assertEquals(2, foundAt);
		Assert.assertEquals(5, level);
	}
}
