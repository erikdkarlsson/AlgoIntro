/**
 * Implementation and test of QuickUnion find
 * Problem with this algorithm is that union method
 * is quadratic 
 * @author erikkarlsson
 *
 */
public class QuickFindUF 
{
   public static void main(String args[])
   {
      QuickFindUF QF = new QuickFindUF(5);
      
      System.out.println(QF.connected(0,1));
      QF.union(0, 1);
      System.out.println(QF.connected(0,1));
      System.out.println(QF.connected(0,2));
      QF.union(3,4 );
      System.out.println(QF.connected(0,4));
      QF.union(1,3 );
      System.out.println(QF.connected(0,3));
      
      
   }
   private int[] id;
   
   public QuickFindUF(int N)
   {
      id = new int[N];
      for (int i = 0; i < id.length; i++)
      {
         id[i] = i;
      }
      
   }
   public boolean connected(int p, int q)
   {
      return id[p] == id[q];
   }
   
   public void union(int p, int q)
   {
      if (id[p] != id[q])
      {
         int idQ = id[q];
         int idP = id[p];
         id[q] = id[p];
         for (int i = 0; i < id.length; i++)
         {
            if (id[i] == idQ || id[i] == idP)
               id[i] = id[p];
            
         }
      }
         
   }
}
