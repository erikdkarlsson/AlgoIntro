import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

	public static void main(String[] args) {
		
		String currChampion ="";
		int currCount = 0;
		
		while(!StdIn.isEmpty())
		{
			currCount +=1;
			String temp = StdIn.readString();
			//System.out.println("Är vi här?");
			if (StdRandom.bernoulli(1.0/currCount))
			{
				currChampion = temp;
			}
			//System.out.println("OCh efter if satsen");
		}
		StdOut.println(currChampion);

	}

}
