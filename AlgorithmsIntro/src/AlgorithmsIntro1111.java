
public class AlgorithmsIntro1111 {

	public static void main(String[] args) {
		// Test case
		boolean[][] a = { {true, false,true}
						, {false, true, false}};
		
		//Should print pattern 123
		//                    1*_*
		//                    2_*_
		printBoolArray(a);
		
	}
	/**
	 * Takes a bool array as input and prints the
	 * array, representing true with a star and
	 * false with a space, also printing row
	 * and column numbers as headers
	 * @param a
	 */
	public static void printBoolArray(boolean[][] a) {
		final char truVal = '*';
		final char falsVal = ' ';
		System.out.printf("  1 2 3%n");
		for (int i = 0; i < a.length; i++) {
			System.out.printf("%d:", i);
			for (int j = 0; j < a[0].length; j++) {
				if (a[i][j] == true)
				{
					System.out.printf("%s ", truVal);
				}
				else {
					System.out.printf("%s ", falsVal);
				}
			}
			System.out.println();
		}
		
	}
}
