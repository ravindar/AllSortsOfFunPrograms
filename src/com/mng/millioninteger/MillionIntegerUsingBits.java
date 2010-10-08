package com.mng.millioninteger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class MillionIntegerUsingBits {

	public static int MAX_NUMBERS = 1000000;
	private static int MAX_BYTE_LEN = (int) Math
			.ceil((MAX_NUMBERS / 8) + 0.5);
	private short[] bitMap = new short[MAX_BYTE_LEN];

	private void setBit(int num) {
		int byte_no = num / 8;
		int bit_pos = num % 8;
		//System.out.println("For num -- " + num);
		//System.out.println("Byte num -- " + byte_no);
		//System.out.println("Bit pos -- " + bit_pos);
		//System.out.println("Bit Map -- " +Integer.toBinaryString(bitMap[byte_no] | (((byte)1) << (7 - bit_pos))));
		//System.out.println("");
		bitMap[byte_no] |= (((byte)1) << (7 - bit_pos));
		//bitMap[byte_no][bit_pos] = 1;
	}

	private int getBit(int num) {
		int byte_no = num / 8;
		int bit_pos = num % 8;
		//System.out.println("Bit Map returned -- " + Integer.toBinaryString((bitMap[byte_no] >>> (7 - bit_pos)) & ((byte)1)));
		//System.out.println("Bit Map Value -- " + ((bitMap[byte_no] >>> (7 - bit_pos)) & ((byte)1)));
		
		return (bitMap[byte_no] >>> (7 - bit_pos)) & (byte)1;
		//return bitMap[byte_no][bit_pos];
	}

	private void readBitMap(File file) throws Exception {
		FileReader fp = new FileReader(file);
		BufferedReader reader = new BufferedReader(fp);
		try {
			String lineRead = null;
			while ((lineRead = reader.readLine()) != null) {
				setBit(Integer.parseInt(lineRead));
			}
		} finally {
			reader.close();
			fp.close();
		}
	}

	private void writeBitMap(File file) throws Exception {
		FileWriter writer = new FileWriter(file);
		BufferedWriter writeToFile = new BufferedWriter(writer);
		try {
			int i = 0;

			for (i = 0; i <= MAX_NUMBERS; i++) {
				if (getBit(i) == 1) {
					writeToFile.write(""+i);
					//System.out.println("Number written "+ i);
					writeToFile.newLine();
				}
			}
		} finally {
			writeToFile.close();
			writer.close();
		}
	}

	@Test
	public void testSort10Integer() throws Exception {
		File location = new File("/Users/ravindar/million/million.txt");
		File sortedFile = new File("/Users/ravindar/million/million.txt.sorted");

		location.createNewFile();
		sortedFile.createNewFile();
		MAX_NUMBERS = 10;
		writeRandomIntTo(location, MAX_NUMBERS);

		for (int i = 0; i < MAX_BYTE_LEN; i++) {
			bitMap[i] = (byte) 0;
		}
		readBitMap(location);

		writeBitMap(sortedFile);
		
		assertFileIsSorted(sortedFile);
	}

	//@Test
	public void testSort40Integer() throws Exception {
		File location = new File("/Users/ravindar/million/million.txt");
		File sortedFile = new File("/Users/ravindar/million/million.txt.sorted");
		
		location.createNewFile();
		sortedFile.createNewFile();
		
		MAX_NUMBERS =40;
		
		writeRandomIntTo(location, MAX_NUMBERS);
		
		for (int i = 0; i < MAX_BYTE_LEN; i++) {
			bitMap[i] = (byte) 0;
		}
		readBitMap(location);
		
		writeBitMap(sortedFile);
		
		assertFileIsSorted(sortedFile);
	}

	//@Test
	public void testSortMillionInteger() throws Exception {
		File location = new File("/Users/ravindar/million/million.txt");
		File sortedFile = new File("/Users/ravindar/million/million.txt.sorted");
		
		location.createNewFile();
		sortedFile.createNewFile();
		
		MAX_NUMBERS =1000000;
		
		writeRandomIntTo(location, MAX_NUMBERS);
		
		for (int i = 0; i < MAX_BYTE_LEN; i++) {
			bitMap[i] = (byte) 0;
		}
		readBitMap(location);
		
		writeBitMap(sortedFile);
		
		assertFileIsSorted(sortedFile);
	}
	
	private void assertFileIsSorted(File sortedFile) throws IOException {
		FileReader fp = new FileReader(sortedFile);
		BufferedReader reader = new BufferedReader(fp);
		try{
			String lineRead = null;
			int expected = 1;
			while ((lineRead = reader.readLine()) != null) {
				Assert.assertEquals(""+expected, lineRead);
				expected++;
			}
		}finally{
			reader.close();
			fp.close();
		}
	}

	private void writeRandomIntTo(File location, int maxNumbers) throws IOException {

		List<Integer> numbers = new ArrayList<Integer>();
		FileWriter writer = new FileWriter(location);
		BufferedWriter writeToFile = new BufferedWriter(writer);
		try {
			for (int i = 0; i < maxNumbers; i++) {
				numbers.add(i+1);
			}

			Collections.shuffle(numbers);

			for (int next : numbers) {
				writeToFile.write("" + next);
				writeToFile.newLine();
			}
		} finally {
			writeToFile.flush();
			writer.flush();
			writeToFile.close();
			writer.close();
		}
	}
}
