package com.mng.stringman;

import org.junit.Assert;
import org.junit.Test;

public class ExcelColumnMapping {

	public static int excelColNum(char[] name) {
		int s = 0;
		for (int i = name.length - 1, e = 1; i >= 0; i--, e *= 26) {
			s += e * (name[i] - 'A' + 1);
		}
		return s;
	}

	public static String excelColName(int num) {
		StringBuilder name = new StringBuilder();
		while (num != 0) {
			name.append((char)('A' + ((num - 1) % 26)));
			num /= 26;
		}
		return name.toString();
	}
	
	@Test
	public void testExcelColNum(){
		Assert.assertEquals(703, excelColNum(new char[]{'A', 'A', 'A'}));
	}

	@Test
	public void testExcelColNumAB(){
		Assert.assertEquals(28, excelColNum(new char[]{'A', 'B'}));
	}
	
	@Test
	public void testIntToExcelCol(){
		Assert.assertEquals("AAA", excelColName(703));
	}
}
