public class MultiplicationTable {
	public static void multiplicationTableUpTo(int max) {
		for (int i = 1; i <= max; i++) {
			for (int j = 1; j <= max; j++) {
				System.out.print(String.format ( "%4d", j * i));
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		multiplicationTableUpTo(14);
	}
}
