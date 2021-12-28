
public class AlgoritmsIntro119 {

	public static void main(String[] args) 
	{
		//Below some test cases for the function
		//should return 1010
		String ten = toBinary(10);	
		//should return 1011
		String eleven = toBinary(11);
		System.out.printf("ten: %s", ten);
		System.out.printf("Eleven: %s", eleven);
	}
	/**
	 * Takes an integer and return a string repr.
	 * of that integer in binary instead of decimal
	 * @param n, positive int
	 * @return
	 */
	public static String toBinary(int n)
	{
		String returnString = "";
		
		
		while (n >0)
		{
			returnString =  (n % 2 ) + returnString ;
			n /=2;
		}
		
		return returnString;
	}

}
