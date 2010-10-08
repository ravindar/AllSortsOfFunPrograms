package com.mng.mergesort;

public class MergeSort {
	public static void sort(int[] toSort){
		mergeSort(toSort, 0, toSort.length);
	}

	private static void mergeSort(int[] toSort, int left, int right) {
		if(left< right){
			int middle = (left+right)/2;
			mergeSort(toSort, left, middle);
			mergeSort(toSort, middle +1, right);
			merge(toSort, left, middle, right);
		}
	}

	private static void merge(int[] toSort, int left, int middle, int right) {
		int lengthLeftArray = middle-left +1;
		int lengthRightArray = right-middle;
		int[] leftArray = new int[lengthLeftArray+1];
		int[] rightArray = new int[lengthRightArray+1];

		for(int i=0;i<lengthLeftArray;i++){
			leftArray[i] = toSort[left+i-1] ;
		}

		for(int i=0;i<lengthRightArray;i++){
			rightArray[i] = toSort[middle+i] ;
		}
		int leftPos=0;
		int rightPos = 0;
		for(int k=left;k<=right;k++){
			if(leftArray[leftPos]<=rightArray[rightPos]){
				toSort[k]=leftArray[leftPos];
				leftPos++;
			}else{
				toSort[k]=rightArray[rightPos];
				rightPos++;
			}
		}
	}
}
