package com.mng.insertsort;

public class InsertionSort {

	public static int[] sort(int[] toSort){
		int key = 0;
		for (int j = 1; j < toSort.length; j++) {
			key = toSort[j];
			int i = j-1;
			while (i>=0 && toSort[i] > key) {
				toSort[i+1] = toSort[i];
				i=i-1;
			}
			toSort[i+1]=key;
		}
		return toSort;
	}

	public static int[] sortDesc(int[] toSort){
		int key = 0;
		for (int j = 1; j < toSort.length; j++) {
			key = toSort[j];
			int i = j-1;
			while (i>=0 && toSort[i] < key) {
				toSort[i+1] = toSort[i];
				i=i-1;
			}
			toSort[i+1]=key;
		}
		return toSort;
	}
}
