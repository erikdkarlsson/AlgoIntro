
public class PercolationTest
{

   public static void main(String[] args)
   {
      Percolation percolation = new Percolation(3);
      percolation.open(2, 2);
      percolation.open(2, 1);
      //percolation.isFull(row, col) for each row and col

      percolation.open(1, 3);
      //percolation.isFull(row, col) for each row and col
      percolation.open(2, 3);
      
      //percolation.isFull(row, col) for each row and col
      for (int i = 1; i <= 3; i++)
      {
         for (int j = 1; j <= 3; j++)
         {
            System.out.printf("%d,%d :%b And open?: %b%n",i,j,percolation.isFull(i, j),percolation.isOpen(i, j));
         }
      }
      percolation.open(3, 3);
      for (int i = 1; i <= 3; i++)
      {
         for (int j = 1; j <= 3; j++)
         {
            System.out.printf("%d,%d :%b And open?: %b%n",i,j,percolation.isFull(i, j),percolation.isOpen(i, j));
         }
      }
      
   }

}
