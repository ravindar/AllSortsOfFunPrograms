package com.mng.bubblesort;

public class BubbleSort {
	public static void sort(int[] toSort){
		for (int i = 0; i < toSort.length; i++) {
			for (int j = toSort.length-1; j > i; j--) {
				if(toSort[j]<toSort[j-1]){
					int swap = toSort[j];
					toSort[j]=toSort[j-1];
					toSort[j-1]=swap;
				}
			}
		}
	}
}
