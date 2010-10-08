package com.mng.quicksort;

import static junit.framework.Assert.*;

import org.junit.Test;

public class QuickSortTest {

	@Test
	public void testInsertionSort(){
		int[] k = {5, 2, 4, 6, 1, 3};
		QuickSort.sort(k);
		assertEquals(1,	k[0]);
		assertEquals(2,	k[1]);
		assertEquals(3,	k[2]);
		assertEquals(4,	k[3]);
		assertEquals(5,	k[4]);
		assertEquals(6,	k[5]);
		
	}
}
