package com.mng.millioninteger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

public class MillionIntegerSort {
	private final int numberOfIntegersToBreak = 10;
	public String sort(File location) throws Exception {
		DataInputStream inputFile = new DataInputStream(
				new BufferedInputStream(new FileInputStream(location)));
		String sortedFileLocation = location.getCanonicalPath() + ".sorted";
		File sortedFileExists = new File(sortedFileLocation);
		if(sortedFileExists.exists()){
			sortedFileExists.delete();
		}
		try {
			int numOfIntsInFile = inputFile.available() / 4;
			int numOfTempFileCreated = numOfIntsInFile / numberOfIntegersToBreak;
			System.out.println("Number of temp files created -- "
					+ numOfTempFileCreated);
			File[] tempFiles = new File[numOfTempFileCreated];
			for (int i = 0; i < tempFiles.length; i++) {
				tempFiles[i] = File.createTempFile("00" + i, "tmp");
				tempFiles[i].deleteOnExit();
			}

			int startIntIndex = 0;
			int endIntIndex = numberOfIntegersToBreak;
			for (int tempFileNum = 0; tempFileNum < numOfTempFileCreated; tempFileNum++) {
				int[] tenThousandInt = readNextTenThousand(inputFile,
						startIntIndex, endIntIndex);
				Arrays.sort(tenThousandInt); // Could be any sort
				writeToFile(tempFiles[tempFileNum], tenThousandInt);
				startIntIndex = endIntIndex;
				endIntIndex += numberOfIntegersToBreak;
			}
			mergeFileAndWriteTo(tempFiles, sortedFileLocation);
		} finally {
			inputFile.close();
		}
		return sortedFileLocation;
	}

	private void mergeFileAndWriteTo(File[] tempFiles, String sortedFileLocation)
			throws Exception {
		DataInputStream[] inputStreams = new DataInputStream[tempFiles.length];
		try {
			int limit = 0;
			for (int i = 0; i < tempFiles.length; i++) {
				inputStreams[i] = new DataInputStream(new BufferedInputStream(
						new FileInputStream(tempFiles[i])));
				if ((inputStreams[i].available() / 4) > limit) {
					limit = inputStreams[i].available() / 4;
				}
			}
			System.out.println("Max Number of elements in each temp file -- "
					+ limit);
			File sortedFile = new File(sortedFileLocation);
			File tempSortedFile = new File(sortedFileLocation + ".tmp");
			for (int i = 0; i < limit; i++) {
				int[] toSort = getFirstIntFromEach(inputStreams);
				Arrays.sort(toSort);
				mergeWithFile(sortedFile, toSort, tempSortedFile);
			}
		} finally {
			for (int i = 0; i < inputStreams.length; i++) {
				if (inputStreams[i] != null)
					inputStreams[i].close();
			}
		}
	}

	private void mergeWithFile(File sortedFile, int[] toSort,
			File tempSortedFile) throws Exception {
		RandomAccessFile accessSortedFile = null;
		RandomAccessFile accessTempFile = null;
		try {
			if (!sortedFile.exists()) {
				sortedFile.createNewFile();
			}
			if (!tempSortedFile.exists()) {
				tempSortedFile.createNewFile();
			}
			accessSortedFile = new RandomAccessFile(sortedFile, "rws");
			long filePointer = accessSortedFile.getFilePointer();
			accessTempFile = new RandomAccessFile(tempSortedFile, "rws");
			long tempFilePointer = accessSortedFile.getFilePointer();
			long numOfIntSoFarInSortedFile = (accessSortedFile.length()) / 4;
			long mergeLimit = numOfIntSoFarInSortedFile + toSort.length;
			int leftPos = 0;
			int rightOffSet = 0;

			if (numOfIntSoFarInSortedFile == 0) {
				for (int i = 0; i < toSort.length; i++) {
					accessSortedFile.writeInt(toSort[i]);
				}
				return;
			}

			for (int i = 0; i < mergeLimit; i++) {
				int rightNumber = getNextIntFromFile(accessSortedFile);
				if (toSort[leftPos] <= rightNumber) {
					accessTempFile.writeInt(toSort[leftPos]);
					if((filePointer + rightOffSet) < accessSortedFile.length())
						accessSortedFile.seek(filePointer + rightOffSet);
					if(leftPos + 1 < toSort.length)
						leftPos++;
				} else {
					accessTempFile.writeInt(rightNumber);
					if((filePointer + rightOffSet + 4) < accessSortedFile.length())
						rightOffSet += 4;
					accessSortedFile.seek(filePointer + rightOffSet);
				}
			}

			accessTempFile.seek(tempFilePointer);
			accessSortedFile.seek(filePointer);
			for (int i = 0; i < mergeLimit; i++) {
				accessSortedFile.writeInt(accessTempFile.readInt());
			}
		} finally {
			if (accessSortedFile != null) {
				accessSortedFile.close();
			}
			if (accessTempFile != null) {
				accessTempFile.close();
			}
			tempSortedFile.delete();
		}
	}

	private int getNextIntFromFile(RandomAccessFile accessSortedFile) throws Exception {
		byte[] rightNumberAsByte = new byte[4];
		accessSortedFile.readFully(rightNumberAsByte);
		ByteBuffer bb = ByteBuffer.wrap(rightNumberAsByte);
		return bb.getInt();
	}

	private int[] getFirstIntFromEach(DataInputStream[] inputStreams)
			throws IOException {
		int[] temp = new int[inputStreams.length];
		int intIndex = 0;
		for (int i = 0; i < temp.length; i++) {
			int firstNum = Integer.MIN_VALUE;
			if (inputStreams[i].available() / 4 == 0) {
				i += 1;
				continue;
			}

			firstNum = inputStreams[i].readInt();
			if (firstNum != Integer.MIN_VALUE) {
				temp[intIndex] = firstNum;
				intIndex++;
			}
		}
		return temp;
	}

	private int[] readNextTenThousand(DataInputStream inputFile,
			int startIntIndex, int endIntIndex) throws IOException {
		int numOfIntsToRead = endIntIndex - startIntIndex;
		if (numOfIntsToRead > (inputFile.available() / 4)) {
			numOfIntsToRead = inputFile.available() / 4;
		}
		int temp[] = new int[numOfIntsToRead];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = inputFile.readInt();
		}
		System.out.println("Ints read so far from -" + startIntIndex + " - "
				+ endIntIndex + " == " + temp.length);
		return temp;
	}

	private void writeToFile(File fileToWrite, int[] intsToWrite)
			throws IOException {
		DataOutputStream tempFileStream = null;
		try {
			tempFileStream = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(fileToWrite)));
			for (int i = 0; i < intsToWrite.length; i++) {
				tempFileStream.writeInt(intsToWrite[i]);
			}
		} finally {
			if (tempFileStream != null)
				tempFileStream.close();
		}
	}

	@Test
	public void testMillionSort() throws Exception {
		File location = new File("/Users/ravindar/million/million.txt");
		writeMillionRandomIntTo(location);
		String sortedFile = sort(location);
		DataInputStream sortedFileStream = new DataInputStream(
				new BufferedInputStream(new FileInputStream(sortedFile)));
		int numOfIntsInFile = sortedFileStream.available() / 4;
		for (int i = 0; i < numOfIntsInFile; i++) {
			System.out.println(sortedFileStream.readInt());
		}
		Assert.assertEquals(1000000, numOfIntsInFile);

	}

	private void writeMillionRandomIntTo(File location)
			throws FileNotFoundException, IOException {
		DataOutputStream writeToFile = null;
		try {
			if (location.exists() && location.isFile()) {
				location.delete();
			}
			writeToFile = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(location)));
			Random random = new Random();
			for (int i = 0; i < 100; i++) {
				int nextInt = random.nextInt(1000000);
				// System.out.print(nextInt+ " ");
				writeToFile.writeInt(nextInt);
			}
		} finally {
			if (writeToFile != null)
				writeToFile.close();
		}
	}
}
