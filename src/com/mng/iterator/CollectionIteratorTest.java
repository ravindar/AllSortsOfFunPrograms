package com.mng.iterator;

import static junit.framework.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class CollectionIteratorTest {
	
	@Test
	public void collectionTestWithStrings(){
		List<Collection<String>> strings = new ArrayList<Collection<String>>();
		
		ArrayList<String> firstSetOfString = new ArrayList<String>();
		firstSetOfString.add("1-First");
		firstSetOfString.add("1-Second");
		firstSetOfString.add("1-Third");
		strings.add(firstSetOfString);
		
		ArrayList<String> secondSetOfStrings = new ArrayList<String>();
		secondSetOfStrings.add("2-First");
		secondSetOfStrings.add("2-Second");
		secondSetOfStrings.add("2-Third");
		strings.add(secondSetOfStrings);
		
		CollectionIterator<String> typeIterator = new CollectionIterator<String>(strings);
//		for (String string : typeIterator) {
//			System.out.println(string);
//		}
		assertEquals("1-First", typeIterator.next());
		assertEquals("1-Second", typeIterator.next());
		assertEquals("1-Third", typeIterator.next());
		assertEquals("2-First", typeIterator.next());
		assertEquals("2-Second", typeIterator.next());
		assertEquals("2-Third", typeIterator.next());
	}

	@Test
	public void collectionTestAndRemovingElements(){
		List<Collection<String>> strings = new ArrayList<Collection<String>>();
		
		ArrayList<String> firstSetOfString = new ArrayList<String>();
		firstSetOfString.add("1-First");
		firstSetOfString.add("1-Second");
		firstSetOfString.add("1-Third");
		strings.add(firstSetOfString);
		
		ArrayList<String> secondSetOfStrings = new ArrayList<String>();
		secondSetOfStrings.add("2-First");
		secondSetOfStrings.add("2-Second");
		secondSetOfStrings.add("2-Third");
		strings.add(secondSetOfStrings);
		
		CollectionIterator<String> typeIterator = new CollectionIterator<String>(strings.iterator());
		
		assertEquals("1-First", typeIterator.next());
		typeIterator.remove();
		try{
			typeIterator.remove();
			fail("Should have thrown exception as next has not been called on iterator");
		}catch(IllegalStateException e){
			assertTrue(true);
		}
		assertEquals("1-Second", typeIterator.next());
		assertEquals("1-Third", typeIterator.next());
		assertEquals("2-First", typeIterator.next());
		assertEquals("2-Second", typeIterator.next());
		assertEquals("2-Third", typeIterator.next());
	}
	
	@Test
	public void collectionTestWithIntegers(){
		List<Collection<Integer>> integers = new ArrayList<Collection<Integer>>();
		
		ArrayList<Integer> firstSetOfIntegers = new ArrayList<Integer>();
		firstSetOfIntegers.add(11);
		firstSetOfIntegers.add(12);
		firstSetOfIntegers.add(13);
		integers.add(firstSetOfIntegers);

		ArrayList<Integer> secondSetOfIntegers = new ArrayList<Integer>();
		secondSetOfIntegers.add(21);
		integers.add(secondSetOfIntegers);
		
		ArrayList<Integer> thirdSetOfIntegers = new ArrayList<Integer>();
		thirdSetOfIntegers.add(31);
		thirdSetOfIntegers.add(32);
		thirdSetOfIntegers.add(33);
		integers.add(thirdSetOfIntegers);
		
		CollectionIterator<Integer> typeIterator = new CollectionIterator<Integer>(integers.iterator());
		assertEquals(11, typeIterator.next().intValue());
		assertEquals(12, typeIterator.next().intValue());
		assertEquals(13, typeIterator.next().intValue());
		assertEquals(21, typeIterator.next().intValue());
		assertEquals(31, typeIterator.next().intValue());
		assertEquals(32, typeIterator.next().intValue());
		assertEquals(33, typeIterator.next().intValue());
	}

}
