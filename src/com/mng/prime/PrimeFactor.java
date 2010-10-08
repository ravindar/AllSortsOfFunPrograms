package com.mng.prime;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class PrimeFactor {
	static Set<Long> primeFactorOf(long number) {
		long factor, temp;
		Set<Long> factors = new HashSet<Long>(); 
		temp = number;
		while (temp % 2 == 0) {
			factors.add(2L);
			temp /= 2;
		}
		factor = 3;
		while (factor <= Math.sqrt(temp) + 1) {
			if (temp % factor == 0) {
				factors.add(factor);
				temp /= factor;
			} else
				factor += 2;
		}
		if (temp > 1)
			factors.add(temp);
		return factors; 
	}

	public static void main(String[] args) {
		System.out.println(Integer.parseInt("AA", 16));
		long p;
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLong()) {
			p = sc.nextLong();
			System.out.println("prime factorization of p=" + p + " ");
			Set<Long> primeFactorOf = primeFactorOf(p);
			for (Iterator iterator = primeFactorOf.iterator(); iterator
					.hasNext();) {
				System.out.println(iterator.next());
				
			}
		}
	}
}