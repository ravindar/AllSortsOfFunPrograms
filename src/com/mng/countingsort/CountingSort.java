package com.mng.countingsort;

public class CountingSort {
	public static int[] sort(int[] toSort, int k){
		int[] resp = new int[toSort.length];
		int[] intermediate = new int[k];
		for (int i = 0; i < k; i++) {
			intermediate[i] = 0;
		}
		
		for (int i = 0; i < resp.length; i++) {
			intermediate[toSort[i]-1] = intermediate[toSort[i]-1]+1;
		}
		
		for (int i = 1; i < k; i++) {
			intermediate[i] = intermediate[i]+intermediate[i-1];
		}
		
		for (int j = toSort.length-1; j >= 0; j--) {
			resp[intermediate[toSort[j]-1]-1]= toSort[j];
			intermediate[toSort[j]-1] = intermediate[toSort[j]-1] -1;
		}
		
		return resp;
	}
	
}
