import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{
   //Array that hold all percolations, maybe shouldn´t keep all?
   private Percolation[] percArr;
   
   
   //An array that holds p for each percolation simulation
   private double[] pArr;
   //private int cols;
   //private int rows;
   private int trialNo;
   private int gridSz;
   
   public static void main(String[] args)
   {
      int n = Integer.parseInt(args[0]);
      int T = Integer.parseInt(args[1]);
      //int n = 10;
      //int T = 10;
      PercolationStats percStat = new PercolationStats(n, T);
      StdOut.printf("mean\t=%f%n", percStat.mean());
      StdOut.printf("stddev\t=%f%n", percStat.stddev());
      String text = "95% confidence interval";
      StdOut.printf("%s =[%f,%f]%n", text, percStat.confidenceLo(),percStat.confidenceHi());
      //System.out.println(percStat.mean());
   }
   public PercolationStats(int n, int trials)
   {
      if (n <= 0 || trials <= 0)
      {
         throw new IllegalArgumentException();
      }
      trialNo = trials;
      gridSz = n*n;
      percArr = new Percolation[trials];
      pArr = new double[trials];
      
    
      for (int i = 0;i < trials;i++)
      {
         percArr[i] = new Percolation(n);
         int tempCol = 0;
         int tempRow = 0;
         int noOpenings = 0;
         while(!percArr[i].percolates())
         {
            tempCol = StdRandom.uniform(n)+1;
            tempRow = StdRandom.uniform(n)+1;
            //System.out.println(tempCol);
            //System.out.println(tempRow);
            if (!percArr[i].isOpen(tempRow, tempCol))
            {
               percArr[i].open(tempRow, tempCol);
               noOpenings += 1;
            }
         }
         pArr[i] = (double)noOpenings/(n*n);
      }
      
   }
   
   public double mean()
   {
      return StdStats.mean(pArr);
   }
   
   public double stddev()
   {
      return StdStats.stddev(pArr);
   }
   
   public double confidenceLo()
   {
      return StdStats.mean(pArr)-(1.96*StdStats.stddev(pArr))/Math.sqrt(trialNo);
   }
   
   public double confidenceHi()
   {
      return StdStats.mean(pArr)+(1.96*StdStats.stddev(pArr))/Math.sqrt(trialNo);
   }
}

