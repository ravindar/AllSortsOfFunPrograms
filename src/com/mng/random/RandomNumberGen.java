package com.mng.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class RandomNumberGen {
	private List<Integer> zeroToNine = new ArrayList<Integer>();

	public String generateNext(){
		int random;
		initializeSeed();
		StringBuilder number = new StringBuilder();
		while (number.length() < 10) {
			random = ((int) ((Math.random())*100))%zeroToNine.size();
			number.append(zeroToNine.get(random));
			zeroToNine.remove(random);
		}
		return number.toString();
	}

	private void initializeSeed() {
		for (int i = 0; i < 10; i++) {
			zeroToNine.add(i);
		}
	}
	
	@Test
	public void testNumberIsUnique(){
		for(int i = 0 ; i<10; i++){
			final String generateNext = generateNext();
			assertAllDigitsUnique(generateNext);
			//System.out.println(generateNext);
		}
	}

	private void assertAllDigitsUnique(String generateNext) {
		HashMap<Integer, Boolean> numberMap = new HashMap<Integer, Boolean>();
		for (int i = 0; i < generateNext.length(); i++) {
			if(numberMap.containsKey((int)generateNext.charAt(i))){
				Assert.fail("found a repeating number -- "+ generateNext.charAt(i) +" -- in -- " + generateNext);
			}else{
				numberMap.put((int)generateNext.charAt(i), true);
			}
		}
		Assert.assertTrue(true);
	}
	
}
