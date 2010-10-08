package com.mng.quicksort;

public class QuickSort {
	public static void sort(int s[]){
		quicksort(s, 0, s.length-1);
	}

	private static void quicksort(int[] s, int l, int h) {
		int pivot;
		if((h-l)>0){
			pivot = partition(s, l, h);
			quicksort(s, l, pivot-1);
			quicksort(s, pivot+1, h);
		}
	}

	private static int partition(int[] s, int l, int h) {
		int p = h; 
		int firsthigh = l; 
		for (int i=l; i<h; i++){
			if (s[i] < s[p]) {
				swap(s, firsthigh, i);
				firsthigh ++;
			}
		}
		swap(s, p, firsthigh);
		return(firsthigh);
	}

	private static void swap(int[] s, int firsthigh, int i) {
		int temp = s[i];
		s[i]=s[firsthigh];
		s[firsthigh] =temp;
	}	
}
