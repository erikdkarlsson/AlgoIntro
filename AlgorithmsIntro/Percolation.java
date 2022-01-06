

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
      Percolation percTest = new Percolation(3);
      //percTest.open(1,4);
      //percTest.open(2,1);
      //StdOut.println(percTest.percolates());
      //percTest.printPerc();
      //StdOut.println(percTest.isFull(2,1));
      /*percTest.open(1, 1);
      // percTest.open(1,2);
      // percTest.open(1,3);
      percTest.open(2, 1);
      percTest.open(4,1);
      percTest.open(4,2);
      percTest.open(5, 1);
      percTest.open(3, 1);
      //ercTest.open(8,1);
      // percTest.open(2,3);
      // percTest.open(2,2);
      percTest.printPerc();
      //StdOut.print(percTest.isOpen(-1,5));
      StdOut.print(percTest.connected(25, 26));
      StdOut.print(percTest.connected(23, 26));
      StdOut.print(percTest.connected(11, 26));
      StdOut.print(percTest.percolates());
      StdOut.print(percTest.numberOfOpenSites());
      StdOut.printf("%n%d", percTest.rowColToXY(2, 2));*/
   //}

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
      /*private int idxToRowCol(i)
      {
         int row = i/(rows) +1;
         int col = i%cols;
         return 
      }*/
      /*System.out.printf("No:");
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
      for (int i = 0; i < id.length ; i++)
      {
         id[i] = i;
         sz[i] = 1;
         // Initialize to closed at all points
         state[i] = 0;
      }
      state[(n * n)] = 1;
      state[(n * n) + 1] = 1;
      id[(n * n)] = (n * n);
      //id[(n * n) + 1] = (n * n) + 1;
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
      //id[(n * n)] = (n * n);
      //id[11] = 10;
      
   }

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
      openSites += 1;
      state[rowColToXY(row, col)] = 1;
      for (int i = -1; i <= 1; i++)
      {
         //if (((row + i) > 0) && rowColToXY(row+i, col) < (rows*cols))
         if (((row + i) > 0) && rowColToXY(row+i, col) <= (rows*cols) && row < rows && col < cols)
         {
            if (isOpen(row + i, col))
            {
               union(rowColToXY(row, col), rowColToXY(row + i, col));
               //StdOut.printf("Row: %d, Col: %d, Row+i: % d, Col+j: %d ", row, col, row + 1, col + 1);
            }
         }
         //if (((col + i) > 0) && rowColToXY(row+i, col) <= (rows*cols))
         if (((col + i) > 0) && rowColToXY(row+i, col) <= (rows*cols) && row < rows && col < cols)
         {
            if (isOpen(row, col + i))
            {
               union(rowColToXY(row, col), rowColToXY(row, col + i));
               //StdOut.printf("Row: %d, Col: %d, Row+i: % d, Col+j: %d ", row, col, row + 1, col + 1);
            }
         }
      }
   
   // Här ska det in att checka om omkringliggande är öppna, isf union
   /*
    * for (int i = -1; i <= 1; i++) { for (int j = -1; j <= 1; j++) { if (((row +
    * i) >0) && ((col + j)>0)) { //System.out.println(row+i);
    * //System.out.println(col+j);
    * 
    * if (isOpen(row+i, col + j)) { union(rowColToXY(row, col), rowColToXY(row+i,
    * col+j)); StdOut.printf("Row: %d, Col: %d, Row+i: % d, Col+j: %d ", row, col,
    * row+1, col+1); }
    * 
    * 
    * 
    * } }
    * 
    * }
    */

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
      }*/
      /*if ((row > rows) ||(col>cols) )
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

   private int root(int i)
   {
      // If parent == current index, it is the root, otherwise, keep chasing root
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
      return root(p) == root(q);
   }

   private void union(int p, int q)
   {
      int i = root(p);
      int j = root(q);
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
