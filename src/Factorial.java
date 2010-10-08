import java.util.Scanner;

public class Factorial {

	static final int MAXN = 100;

	static long factorial(int n) {
		if (n <= 1)
			return 1;
		return n * factorial(n - 1);
	}

	public static void main(String[] args) {
		int a;
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			a = sc.nextInt();
			System.out.printf("%d\n", factorial(a));
		}
	}

}
