
public class RgbAsHex {
	public static String formatRGB ( int r, int g, int b ) {
        return String.format ( "%02X%02X%02X", r, g, b );
    }
	
	public static void main(String[] args) {
		System.out.println(formatRGB(255, 0, 128));
	}
}
