package com.mng.heapsort;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class HeapSort {
	public int[] sort(int[] toSort, int count) {
		toSort = heapify(toSort, count);
		int end = count - 1;
		while (end > 0) {
			toSort = swap(toSort, 0, end);
			end--;
			toSort = bubbleDown(toSort, 0, end);
		}
		return toSort;
	}

	private int[] bubbleDown(int[] toSort, int start, int end) {
		int root = start;
		while(root*2+1 <= end){
			int child = root *2+1;
			if(child+1 <= end && toSort[child] < toSort[child+1]){
				child = child +1;
			}
			
			if(toSort[root] < toSort[child]){
				toSort = swap(toSort, root, child);
				root = child;
			}else{
				return toSort;
			}
		}
		return toSort;
	}

	private int[] swap(int[] toSort, int start, int end) {
		int temp = toSort[start];
		toSort[start] = toSort[end];
		toSort[end] = temp;
		return toSort;
	}

	private int[] heapify(int[] toSort, int count) {
		int start = (count - 2) / 2;
		while (start >= 0) {
			toSort = bubbleDown(toSort, start, count - 1);
			start--;
		}
		return toSort;
	}
	
	@Test
	public void testHeapSort(){
		int[] k = {5, 2, 4, 6, 1, 3};
		int[] sorted = sort(k, 6);
		assertEquals(1,	sorted[0]);
		assertEquals(2,	sorted[1]);
		assertEquals(3,	sorted[2]);
		assertEquals(4,	sorted[3]);
		assertEquals(5,	sorted[4]);
		assertEquals(6,	sorted[5]);
		
	}

}