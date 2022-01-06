

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Percolation
{
   // Open or not?
   private int[] state;
   // Connection graph
   private int[] id;
   // Size of the tree at each node
   private int[] sz;
   // no of rows
   private int rows;
   // no of cols
   private int cols;

   private int openSites;

   /*public static void main(String[] args)
   {
      Percolation percolation = new Percolation(1);
      /*percolation.open(2, 2);
      percolation.open(2, 1);
      //percolation.isFull(row, col) for each row and col

      percolation.open(1, 3);
      //percolation.isFull(row, col) for each row and col
      percolation.open(2, 3);
      percolation.open(2, 3);
      
      StdOut.println(percolation.connected(percolation.rowColToXY(1, 3), percolation.rowColToXY(2, 3)));
      StdOut.println(percolation.percolates());*/
     // percolation.open(1, 1);
      
      /*percolation.open(2, 5);
      percolation.open(5, 3);
      
      percolation.open(3, 3);
      percolation.open(4, 5);
      percolation.open(3, 5);
      percolation.open(5, 4);
      percolation.open(2, 2);
      percolation.open(1, 5);*/
      //percolation.isFull(row, col) for each row and col
   /*   percolation.printPerc();
      StdOut.print(percolation.isFull(2, 5));
   }*/
   public Percolation(int n)
   {
      if (n < 1 )
      {
         throw new IllegalArgumentException();
      }
      
      openSites = 0;
      rows = n;
      cols = n;
      state = new int[(n * n) + 2];// Includes virtual top and bottom site, top n, bottom n+1
      sz = new int[(n * n) + 2];// Includes virtual top and bottom site, top n, bottom n+1
      id = new int[(n * n) + 2];// Includes virtual top and bottom site, top n, bottom n+1
      for (int i = 0; i < id.length -2; i++)
      {
         id[i] = i;
         sz[i] = 1;
         // Initialize to closed at all points
         state[i] = 0;
      }
      state[(n * n)] = 1;
      state[(n * n) + 1] = 1;
      id[(n * n)] = (n * n);
      id[(n * n) + 1] = (n * n) + 1;
      //StdOut.printf("NOW n*n = %d", n*n);
      for (int i = 0; i <n ; i++)
      {
         // Union all the top to the virtual top
         id[i] = (n * n);
         sz[i] = 1;
         if (((n*n) -1 -i) >=0)
         {
         id[(n * n) - 1 - i] = (n * n) + 1;
         sz[(n * n) - 1 - i] = 1;
         }
         // this.union(n-1, i);
         // Union all the bottom ones to the virtual bottom
         // this.union(n-2, n-2-i);
      }
     
   }
  /*public void printPerc()
   {
      final int scale = 6;
      StdDraw.setXscale(0, rows * scale);
      StdDraw.setYscale(0, cols * scale);
      for (int i = 0; i < id.length - 2; i++)
      {
         int r = i / rows;
         int c = (i) % cols;

         double[] x = { (c * scale), (c * scale), scale + (c * scale), scale + (c * scale) };
         double[] y = { (rows * scale) - (r * scale), (rows * scale) - scale - (r * scale),
               (rows * scale) - scale - (r * scale), (rows * scale) - (r * scale) };
         if (state[i] == 1)
         {
            if (isFull(r+1,c+1)) 
            {StdDraw.setPenColor(StdDraw.BOOK_BLUE);
            StdDraw.filledPolygon(x, y);
            }
            else
            {StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.polygon(x, y);
            }
            // StdDraw.text(c*scale,r*scale, Integer.toString(id[i]));
            // StdDraw.text(r*scale-scale/2,c*scale-scale/2, Integer.toString(id[i]));
            StdDraw.setPenColor(StdDraw.BLACK);
         } else
         {
            StdDraw.filledPolygon(x, y);
            StdDraw.setPenColor(StdDraw.BOOK_RED);
            // StdDraw.text(c*scale,r*scale, Integer.toString(id[i]));
            // StdDraw.text(r*scale-scale/2,c*scale-scale/2, Integer.toString(id[i]));
            StdDraw.setPenColor(StdDraw.BLACK);
         }
      }
    
      System.out.printf("No:");
       for (int i = 0; i < state.length; i++)
       {
       System.out.printf("%d,", i);
       }
       System.out.println();
       System.out.printf("State:");
       for (int i = 0; i < state.length; i++)
      {
         System.out.printf("State: %d,", state[i]);
      }
      System.out.println();
      System.out.printf("ID:");
      for (int i = 0; i < id.length; i++)
      {
         System.out.printf("%d,", id[i]);
      }
   }*/
   
   // Creates a percolation instance of an n by n grid, all are closed upon
   // creation
  

   private int rowColToXY(int row, int col)
   {
      return ((row - 1) * cols + col - 1);
   }

   // Opens a specific point, and connects to all adjacent nodes that are open
   public void open(int row, int col)
   {
      if (row < 1 || col < 1 ||(row > rows) ||(col>cols) )
      {
         throw new IllegalArgumentException();
      }
      if (isOpen(row, col)) return;
      openSites += 1;
      state[rowColToXY(row, col)] = 1;
      
      for (int i = -1; i <= 1; i++)
      {
         
         if (((row + i) > 0) && (rowColToXY(row+i, col) < (rows*cols) && (row +i) <= rows && (col) <= cols ))
         {
            if (isOpen(row + i, col))
            {
               union(rowColToXY(row, col), rowColToXY(row + i, col));
         
            }
         }
         
         if (((col + i) > 0) && (rowColToXY(row, col+i) <= (rows*cols)) && row <= rows && (col+i) <= cols )
         {
            //StdOut.printf("Row: %d, col: %d", row, col+i);
            if (isOpen(row, col + i))
            {
               union(rowColToXY(row, col), rowColToXY(row, col + i));
         
            }
         }
      }

   }

   // Tells if a specific node is open
   public boolean isOpen(int row, int col)
   {
      if (row < 1 || col < 1 ||(row > rows) ||(col>cols) )
      {
         throw new IllegalArgumentException();
      }
      /*if (row < 1 || col < 1)
      {
         throw new IllegalArgumentException("Too small");
      }
     if ((row > rows) ||(col>cols) )
      {
         throw new IllegalArgumentException("Too big");
      }*/
      return (state[rowColToXY(row, col)] == 1);
   }

   public int numberOfOpenSites()
   {

      return openSites;
   }

   public boolean percolates()
   {
      return connected((rows * cols), (rows * cols) + 1);
   }

   private int find(int i)
   {
      // If parent == current index, it is the find, otherwise, keep chasing find
      while (id[i] != i)
      {
         id[i] = id[id[i]];
         i = id[i];
      }
      return i;
   }
   //!!! Shall be a private when all testing is done
   private boolean connected(int p, int q)
   {
      return find(p) == find(q);
   }

   private void union(int p, int q)
   {
      int i = find(p);
      int j = find(q);
      if (i == j)
         return;
      if (sz[i] < sz[j])
      {
         id[i] = j;
         sz[j] += sz[i];
      } else
      {
         id[j] = i;
         sz[i] += sz[j];
      }
      id[p] = id[q];
   }
   public boolean isFull(int row, int col)
   {
      if (row < 1 || col < 1 ||(row > rows) ||(col>cols) )
      {
         throw new IllegalArgumentException();
      }
      //StdOut.println(rowColToXY(row, col));
      //StdOut.println(cols*rows);
      return (connected(cols*rows,rowColToXY(row, col)) && isOpen(row, col));
      //return (state[rowColToXY(row, col)] == 0);
   }
  

}
