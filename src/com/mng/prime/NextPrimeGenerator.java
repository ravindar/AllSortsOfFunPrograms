package com.mng.prime;

public class NextPrimeGenerator {
	public static int nextPrimeAfter(int number) {
		if (number == 0 || number < 0) {
			return 2;
		}

		int nextPrime = number + 1;
		boolean primeFound = false;
		while (!primeFound) {
			primeFound = isPrime(nextPrime);
			if (!primeFound) {
				nextPrime++;
			}
		}
		return nextPrime;
	}

	private static boolean isPrime(int number) {
		if (number <= 1)
			return false;
		if (number == 2)
			return true;
		if (number % 2 == 0)
			return false;
		int m = (int) Math.sqrt(number);

		for (int i = 3; i <= m; i += 2)
			if (number % i == 0)
				return false;

		return true;
	}
}
