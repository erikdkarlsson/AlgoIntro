
/**
 * Recursive implementation of binarysearch
 * Main includes limited testing of the function
 * Also includes a random testing to see how many "steps" are used in searching
 * @author erikkarlsson
 *
 */

import java.security.SecureRandom;
import java.util.Arrays;

public class BinarySearch
{
   public static void main(String[] args)
   {
      int[] a = new int[128];
      SecureRandom rnd = new SecureRandom();

      // Initialize array to idx=value, for each position
      for (int i = 0; i < a.length; i++)
      {
         a[i] = rnd.nextInt(2500);
      }
      // Sort array
      Arrays.sort(a);
      int cnt = 0;
      for (int i = 0; i < a.length; i++)
      {
         int temp = search(a, a[i]);
         if (temp != -1)
            ;
         {
            System.out.println(temp);
            cnt++;
         }
      }
      // Print the random array
      for (int i = 0; i < a.length; i++)
      {
         System.out.println(a[i]);
      }
      System.out.println(search(a, a[a.length-1]));
      //System.out.printf("Cnt: %d%n", cnt);

   }

   // Function that is the api
   public static int search(int[] a, int key)
   {
      // if (a.length ==1) return a[0]
      int lo = 0;
      int hi = a.length - 1;

      return searchHelp(a, key, lo, hi);
   }

   public static int searchHelp(int[] a, int key, int lo, int hi)
   {

      int mid = (lo + hi) / 2 +1;
      if (lo >= hi)
         return -1;
      // This line below can be uncommented to print the current Lo, Hi, and Mid,
      // makes it easy to "visualize" the search progression
      //System.out.printf("Lo: %d, Hi: %d, Mid: %d%n",lo, hi, mid);

      // If curr position is bigger than key, lower the hi to mid-1
      if (a[mid] > key)
         return searchHelp(a, key, lo, mid-1);
      // If curr position is smaller than key, increase lo to mid+1
      else if (a[mid] < key)
         return searchHelp(a, key, mid + 1, hi);
      // If
      else if (a[mid] == key)
         return mid;

      return -1;

   }
}
