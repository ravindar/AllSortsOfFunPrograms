package com.mng.insertsort;

import static junit.framework.Assert.*;

import org.junit.Test;

public class InsertionSortTest {

	@Test
	public void testInsertionSort(){
		int[] k = {5, 2, 4, 6, 1, 3};
		int[] sorted = InsertionSort.sort(k);
		assertEquals(1,	k[0]);
		assertEquals(2,	k[1]);
		assertEquals(3,	k[2]);
		assertEquals(4,	k[3]);
		assertEquals(5,	k[4]);
		assertEquals(6,	k[5]);
		
	}

	@Test
	public void testInsertionSortDesc(){
		int[] k = {5, 2, 4, 6, 1, 3};
		int[] sorted = InsertionSort.sortDesc(k);
		assertEquals(6,	k[0]);
		assertEquals(5,	k[1]);
		assertEquals(4,	k[2]);
		assertEquals(3,	k[3]);
		assertEquals(2,	k[4]);
		assertEquals(1,	k[5]);
		
	}
}
