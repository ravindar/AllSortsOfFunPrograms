
public class PrintEvens {
	public static void printEvensUpTo(int n) {
        for (int i = 2; i < 100; i += 2) {
            System.out.println ( i );
        }
    }
	
	public static void main(String[] args) {
		printEvensUpTo(100);
	}
}
