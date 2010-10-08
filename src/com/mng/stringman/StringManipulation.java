package com.mng.stringman;

import java.util.HashMap;

import javax.swing.SpringLayout.Constraints;

import org.junit.Assert;
import org.junit.Test;

public class StringManipulation {
	public static int strToInt(String str){
		int i =0; 
		int num =0;
		int len = str.length();
		boolean isNeg = false;
		
		if(str.charAt(0) == '-'){
			isNeg = true;
		}
		
		while(i<len){
			num *=10;
			num += (str.charAt(i++) - '0');
		}
		
		if(isNeg)
			num *= -1;
		
		return num;
	}

	public static String intToStr(int num){
		int i =0;
		boolean isNeg = false;
		char[] temp = new char[10];
		if(num <0){
			num *= -1;
			isNeg = true;
		}
		
		do{
			temp[i++] = (char)((num % 10) + '0');
			num /= 10;
		}while(num != 0);
		
		StringBuilder buffer = new StringBuilder();
		
		if(isNeg)
			buffer.append('-');
		
		while(i>0){
			buffer.append(temp[--i]);
		}
		return buffer.toString();
	}
	
	public static String removeCharsFrom(String source, String remove){
		char[] removeArray = remove.toCharArray();
		HashMap<Character, Boolean> deleteMap = new HashMap<Character, Boolean>();
		StringBuilder sourceBuilder = new StringBuilder();
		
		for (char c : removeArray) {
			deleteMap.put(Character.toLowerCase(c), true);
		}
		
		for (int i = 0; i < source.length(); i++) {
			char charAt = source.charAt(i);
			if(!deleteMap.containsKey(Character.toLowerCase(charAt))){
				sourceBuilder.append(charAt);
			}
		}
		return sourceBuilder.toString();
	}
	
	public static String reverseElegant(String toReverse){
		char[] reverse = toReverse.toCharArray();
		int wordStart = 0;
		int wordEnd = 0;
		int length = reverse.length;
		reverseCharString(reverse, 0, length-1);
		while(wordEnd < length){
			if(reverse[wordEnd] != ' '){
				wordStart = wordEnd;
				
				while(wordEnd < length && reverse[wordEnd] != ' ')
					wordEnd++;
				
				wordEnd--;
				reverseCharString(reverse, wordStart, wordEnd);
			}
			wordEnd++;
		}
		return new String(reverse);
	}
	
	private static void reverseCharString(char[] reverse, int start, int end) {
		char temp;
		while(end > start){
			temp = reverse[start];
			reverse[start] = reverse[end];
			reverse[end] = temp;
			
			start++;
			end--;
		}
		
	}

	public static String reverse(String toReverse){
		StringBuilder buffer = new StringBuilder();
		int tokenReadPos = toReverse.length()-1;
		int wordEnd = 0;
		int wordReadPos = 0;
		char[] reverse = toReverse.toCharArray();
		
		while(tokenReadPos >= 0){
			if(reverse[tokenReadPos] == ' '){
				buffer.append(reverse[tokenReadPos--]);
			}else{
				wordEnd = tokenReadPos;
				while (tokenReadPos >= 0 && reverse[tokenReadPos] != ' ') {
					tokenReadPos--;
				}
				
				wordReadPos = tokenReadPos + 1 ;
				
				while(wordReadPos <= wordEnd) {
					buffer.append(reverse[wordReadPos++]);
				}
			}
		}
		
		return buffer.toString();
	}
		
	@Test
	public void testCharRemoval() {
		String newString = StringManipulation.removeCharsFrom("Battle Of the Vowels: HawaII vs. GRonzy", "aeiou");
		Assert.assertEquals("Bttl f th Vwls: Hw vs. GRnzy", newString);
	}

	@Test
	public void testReversal() {
		String newString = StringManipulation.reverse(" Do  or do not, there is no try. ");
		Assert.assertEquals(" try. no is there not, do or  Do ", newString);
	}

	@Test
	public void testReversalElegant() {
		String newString = StringManipulation.reverseElegant(" Do  or do not, there is no try. ");
		Assert.assertEquals(" try. no is there not, do or  Do ", newString);
	}
}
