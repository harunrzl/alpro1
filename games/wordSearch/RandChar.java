import java.util.Random;
public class RandChar {
	private Random r = new Random();
	
	private final char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
									  'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
									  'U', 'V', 'W', 'X', 'Y', 'Z' };
	
	public char nextChar() {
		int c = r.nextInt(26);
		
		return alphabet[c];
	}
}
