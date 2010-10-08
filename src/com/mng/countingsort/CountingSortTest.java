package com.mng.countingsort;

import static junit.framework.Assert.*;

import org.junit.Test;

public class CountingSortTest {

	@Test
	public void testBubbleSort(){
		int[] k = {5, 2, 4, 6, 1, 3};
		int[] resp = CountingSort.sort(k, 6);
		assertEquals(1,	resp[0]);
		assertEquals(2,	resp[1]);
		assertEquals(3,	resp[2]);
		assertEquals(4,	resp[3]);
		assertEquals(5,	resp[4]);
		assertEquals(6,	resp[5]);
		
	}
}
