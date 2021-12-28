import edu.princeton.cs.algs4.In;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class AlgorithmsIntro
{
   public static void main(String[] args)
   {
      //Init all ints, for comparison
      int noOne = 0;
      int noTwo = 0;
      int noThree = 0;
      
      //Takes three numbers as arguments in try block, to make sure user entered correct cla
      try
      {
      noOne = Integer.parseInt(args[0]);
      noTwo = Integer.parseInt(args[1]);
      noThree = Integer.parseInt(args[2]);
      }
      //If too few or incorrect input
      catch (NumberFormatException | ArrayIndexOutOfBoundsException e)
      {
         System.out.println("Please write exactly three numbers as arguments: java AlgorithmsIntro no1 no2 no3");
         System.exit(1);
      }
      //Check if all three inputs are equal
      if (noOne == noTwo && noTwo == noThree)
      {
         System.out.println("Equal");
      }
      else
      {
         System.out.println("Not equal");
      }
           
      
   }
}
