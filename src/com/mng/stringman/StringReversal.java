package com.mng.stringman;

import org.junit.Assert;
import org.junit.Test;

public class StringReversal {
	public static String reverse(String reverseString){
		char[] charArray = reverseString.toCharArray();
		int last = charArray.length-1;
		for (int i = 0; i < charArray.length/2; i++) {
			char temp = charArray[i];
			charArray[i] = charArray[last-i];
			charArray[last-i] = temp;
		}
		return new String(charArray);
	}

	private static void reverseChars(char[] toReverse, int start, int end) {
		char temp; 
		while(end > start){
			temp = toReverse[start];
			toReverse[start]= toReverse[end];
			toReverse[end] = temp;
			start++;
			end--;
		}
	}
	
	public static String reverseStringWithoutReversingWord(String reverseString){
		int length = reverseString.length()-1;
		char[] charArray = reverseString.toCharArray();
		reverseChars(charArray, 0, length);
		int wordStart = 0;
		int wordEnd = 0;
		while (wordEnd < length) {
			if(charArray[wordEnd] != ' '){
				wordStart = wordEnd;
				while(wordEnd < length && charArray[wordEnd] != ' ')
					wordEnd++;
				wordEnd--;
				reverseChars(charArray, wordStart, wordEnd);
			}
			wordEnd++;
		}
		
		return new String(charArray);
	}
	
	@Test
	public void testReversal() {
		String newString = StringReversal.reverse(" Do  or do not, there is no try. ");
		Assert.assertEquals(" .yrt on si ereht ,ton od ro  oD ", newString);
	}

	@Test
	public void testReversalWithoutReversingWord() {
		String newString = StringReversal.reverseStringWithoutReversingWord(" Do  or do not, there is no try. ");
		Assert.assertEquals(" try. no is there not, do or  Do ", newString);
	}
	
}
