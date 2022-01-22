import java.security.SecureRandom;
import java.util.Iterator;
import java.security.SecureRandom;
/**
 * Implements a few sorts, notes their space and time
 * complexity, and also whether the sorts are stable
 * for instance mergesort and insertion sort are stable
 * shell sort and selection sort are not stable,
 * stable sorts are sorts that preserve order between
 * keys that are equal
 * @author erikkarlsson
 *
 */
public class Selection
{
   final static int CUTOFF = 7;
   

   public static void main(String[] args)
   {
      final int ALENGTH = 20000000;

      //static Integer[] a = new Integer[ALENGTH];
      //static Integer[] aux = new Integer[ALENGTH];
      Integer[] a = new Integer[ALENGTH];
      //Integer[] aux = new Integer[ALENGTH];
      SecureRandom randomGen = new SecureRandom();
      // Integer[] a = { 4, 3, 2, 1, 7, 5 ,};

      for (int i = 0; i < a.length; i++)
      {
         a[i] = randomGen.nextInt(10000);
      }
      System.out.println(isSorted(a));
      // show(a);
      // selection(a);
      //insertion(a);
      // show(a);
       //mergeSort(a, aux, 0, a.length);
      mergeSort(a);
      // System.out.println(isSorted(a));
      //show(a);
      System.out.println(isSorted(a));
   }
   
   //Polymorphic version for API consumption(does not require aux array and hi and lo vals
   public static void mergeSort(Comparable[] a)
   {
      Comparable[] aux = new Comparable[a.length];
      mergeSort(a, aux, 0, a.length-1);
   }
   public static void mergeSort(Comparable[] a, Comparable[] aux, int lo, int hi)
   {
      
      if (hi <= lo) return;
      /*if (hi <= lo + CUTOFF -1)
      {
         insertion(a,lo, hi);
         return;
      }*/
      int mid = lo + (hi - lo ) / 2;
      mergeSort(a, aux, lo, mid);
      mergeSort(a, aux,mid+1, hi);
      merge(a, aux, lo, mid, hi);
   }

   public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
   {
      //assert isSorted(a, lo, mid); // precondition: a[lo..mid] sorted
      //assert isSorted(a, mid + 1, hi); // precondition a[mid+1..hi] sorted

      //copy to aux[]
      for (int k = lo; k <= hi; k++)
      {
         aux[k] = a[k];
      }

      int i = lo, j = mid + 1; // init the variables for "subarrays"
      for (int k = lo; k <= hi; k++)
      {
         if (i > mid)
            a[k] = aux[j++]; // if i is on right half, copy rest of right into array
         else if (j > hi)
            a[k] = aux[i++]; // if all on right half has been copied, copy rest from left half
         else if (less(aux[j], aux[i]))
            a[k] = aux[j++]; // copy from right half
         else
            a[k] = aux[i++]; // copy from left half
      }
      //assert isSorted(a, lo, hi);

   }

   /**
    * Shell sort, sorts in the pattern
    * 
    * @param a
    */
   public static void shellsort(Comparable[] a)
   {
      int h = 1;
      int n = a.length;
      while (h < n / 3)
         h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093,...
      System.out.println(h);
      while (h >= 1)
      {
         // show(a);
         System.out.println(h);
         for (int i = h; i < n; i++)
         {
            for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
            {
               exch(a, j, j - h);
            }
         }
         h /= 3;
      }
   }

   /**
    * Insertion sort, uses N^2/4 compares and N^2/4 exch. for a randomly ordered
    * array. Worst case is N^2/2 Compares and exchanges. Uses N+1 memory For an
    * ordered array it only uses N-1 compares and zero exch.
    * 
    * @param a
    */
   public static void insertion(Comparable[] a)
   {
      for (int i = 1; i < a.length; i++)
      {
         for (int j = i; j > 0; j--)
         {
            if (less(a[j], a[j - 1]))
            {
               exch(a, j, j - 1);
               // show(a);
            }
         }
      }
   }
   public static void insertion(Comparable[] a, int lo, int hi)
   {
      for (int i = lo; i < hi; i++)
      {
         for (int j = i; j > 0; j--)
         {
            if (less(a[j], a[j - 1]))
            {
               exch(a, j, j - 1);
               // show(a);
            }
         }
      }
   }
   /**
    * Selection sort, uses N^2/2 compares and at most N exchanges. Uses N+1 Memory
    * Insensitive to input
    * 
    * @param a
    */
   public static void selection(Comparable[] a)
   {
      Comparable min;
      int minPos = 0;
      for (int i = 0; i < a.length; i++)
      {
         min = a[i];
         minPos = i;
         for (int j = i; j < a.length; j++)
         {
            if (less(a[j], min))
            {
               minPos = j;
               min = a[j];
            }
         }
         exch(a, i, minPos);
      }
   }

   private static boolean less(Comparable v, Comparable w)
   {
      return v.compareTo(w) < 0;
   }

   private static void exch(Comparable[] a, int i, int j)
   {
      Comparable temp;
      temp = a[i];
      a[i] = a[j];
      a[j] = temp;
   }

   private static void show(Comparable[] a)
   {
      for (Comparable comparable : a)
      {
         System.out.print(comparable + " ");
      }
      System.out.println();
   }

   private static boolean isSorted(Comparable[] a)
   {
      for (int i = 1; i < a.length; i++)
      {
         if (less(a[i], a[i - 1]))
            return false;

      }
      return true;
   }

   private static boolean isSorted(Comparable[] a, int lo, int hi)
   {
      assert lo >= 0; // make sure that we are within the array
      assert hi <= a.length; // make sure that we are within the array
      for (int i = lo; i < hi; i++)
      {
         if (less(a[i], a[i - 1]))
            return false;

      }
      return true;
   }

}
