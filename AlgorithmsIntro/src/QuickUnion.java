import edu.princeton.cs.algs4.StdRandom;

/**
 * Quick union implementation, it utilizes the "lazy-approach"
 * ie it does the work when it is needed, primarily
 * 
 * @author erikkarlsson
 *
 */
public class QuickUnion
{
   
   private int[] id;
   private int[] sz;
   
   public static void main(String[] args)
   {
      QuickUnion QU = new QuickUnion(100000);
      System.out.println(QU.connected(0,1));
      QU.union(0,3);
      for (int i = 5; i < QU.size(); i++)
      {
         boolean connect = StdRandom.bernoulli(0.9);
         if (connect) 
         {QU.connected(i, i-1);}
      }
      //QU.union(1,2);
      //QU.union(2,3);
      //QU.union(4,6);
      
      System.out.println(QU.connected(0,600));
      System.out.println(QU.connected(4,6));
      System.out.println(QU.connected(5,6));
      System.out.println(QU.connected(3,6));
      
   }
  
   public int size()
   {
      return id.length;
   }
   public QuickUnion(int N)
   {
      id = new int[N];
      sz = new int[N];
      for (int i = 0; i < id.length; i++)
      {
         id[i] = i;
         sz[i] = 1;
      }
   }
   public int root(int i)
   {
      //If parent == current index, it is the root, otherwise, keep chasing root
      while (id[i] != i)
         {
            id[i] = id[id[i]];
            i = id[i];
         }
      return i;
   }
   public boolean connected(int p, int q)
   {
      return root(p) == root(q);
   }
   public void union(int p, int q)
   {
      int i = root(p);
      int j = root(q);
      if (i == j) return;
      if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i];}
      else              {id[j] = i; sz[i] += sz[j];}
      id[p] = id[q];
   }
   
}
