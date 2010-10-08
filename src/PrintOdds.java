
public class PrintOdds {
	public static void printOddsUpTo(int n) {
        for (int i = 1; i < 100; i += 2) {
            System.out.println ( i );
        }
    }
	
	public static void main(String[] args) {
		printOddsUpTo(100);
	}
}
