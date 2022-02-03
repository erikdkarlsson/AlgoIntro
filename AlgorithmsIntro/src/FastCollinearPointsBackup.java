import edu.princeton.cs.algs4.*;
import java.util.ArrayList;

public class FastCollinearPoints
{
    private int N;
    private ArrayList<LineSegment> linesegments= new ArrayList<LineSegment>();
    Double[] slopes;
    public FastCollinearPoints(Point[] points)
    {
        Point p = points[0];
        int numOfSegments = 0;
        int currPoints; //counter that remembers how many points with same slope
        int currPos = 2;//current position of 
        //Array for the slopes
        //linesegments = new LineSegment[1];
        slopes = new Double[points.length];
        
        slopes[0] = 0.0;//slope betweend p and p is zero...
        
        //Determine the slopes
        for (int i = 1; i < points.length; i++)
        {
            slopes[i] = p.slopeTo(points[i]);
        }
        //Sort the points according to the slopes
        show(points);
        show(slopes);
        
        selection(slopes, points, 1, points.length);
        show(points);
        show(slopes);
        //Search through the slopes, return all maximal line segments containing at least 4 points
        for (int i = 1; i < points.length; i++)
        {
            currPoints = 0;
            for (int j = i; j < points.length; j++)
            {
                //System.out.printf("CurrP:, %di: %d, j: %d, Slopes[i]: %f, slopes[j]: %f%n",currPoints,i, j, slopes[i], slopes[j]);
                if (slopes[j].equals(slopes[i])) currPoints++;
                else break;
            }
            //System.out.println(currPoints);
            //currPoints is more than 4, we have collinear between slopes[i] and [i+currpoints]
            if (currPoints >= 3)
            {
                //Copy points to array
                N++;
                Point[] tempPoints = new Point[currPoints+1];
                //Initialize the first point
                tempPoints[0] = points[0];
                int tempCount=1;
                for (int k = i; k <= i+currPoints-1; k++)
                {
                    tempPoints[tempCount++] = points[i];
                }
                //Sort tempPoints
                selection(tempPoints,1,tempPoints.length);
                //Add a linesegment between first and last point
                LineSegment lineseg;
                lineseg = new LineSegment(tempPoints[0], tempPoints[currPoints]);
                linesegments.add(lineseg);
            }
        }
        
        // for instance if p-q-r-s-t is a line, return either p-t and t-p, not q-t
        
        
    }
    
    public int numberOfSegments()
    {
        return N;
    }
    public LineSegment[] segments()
    {
        LineSegment[] lines = new LineSegment[N];
        //System.out.print(N);
        for (int i = 0; i < N; i++)
        {
            //System.out.print("One");
            lines[i] = linesegments.get(i);
        }
        //show(lines);
        return lines;
    }
    
    /**
     * Selection sort which sorts one array, and sorts the second array
     * in accordance with the key in the first,
     * example input: 
     * a= {'c','b','a'}
     * b= {1,2,3}
     * leads to output:
     * a ={'a','b','c'}
     * b = {3,2,1}
     * @param a
     */
    public static void selection(Comparable[] a, Comparable[] b, int lo, int hi)
    {
       Comparable min;
       int minPos = lo;
       for (int i = lo; i < hi; i++)
       {
          min = a[i];
          minPos = i;
          for (int j = i; j < hi; j++)
          {
             if (less(a[j], min))
             {
                minPos = j;
                min = a[j];
             }
          }
          exch(a, i, minPos);
          exch(b,i,minPos);
       }
    }
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
    public static void selection(Comparable[] a, int lo, int hi)
    {
       Comparable min;
       int minPos = lo;
       for (int i = lo; i < hi; i++)
       {
          min = a[i];
          minPos = i;
          for (int j = i; j < hi; j++)
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
    //Unit test for selection sort, which sorts two arrays
    /*public static void selectionTest()
    {
        Character[] a= {'d','c','b','a'};
        Integer[] b= {4,1,2,3};
        show(a);
        show(b);
        
        selection(a, b);
        show(a);
        show(b);
    }*/
    public static void main(String[] args)
    {
        // read the n points from a file
        
        int n = 8;
        int[] xs = {10000,0,3000,7000,20000,3000,14000,6000};
        int[] ys = {0,10000,7000,3000,21000,4000,15000,7000};
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = xs[i];
            int y = ys[i];
            points[i] = new Point(x, y);
        }
        /*In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }*/

        // draw the points
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        //selectionTest();
        

    }

}
