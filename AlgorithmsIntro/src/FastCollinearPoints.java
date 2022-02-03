import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints
{
    private int N;
    private ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();
    //Double[] slopes;

    public FastCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new NullPointerException();

        Point[] pCopy = new Point[points.length];
        for (int i = 0; i < pCopy.length; i++)
        {
            pCopy[i] = points[i];
        }
        Arrays.sort(pCopy);
        if (duplicate(pCopy))
            throw new IllegalArgumentException("Duplicate points");

        for (int i = 0; i < pCopy.length - 3; i++)
        {
            Arrays.sort(pCopy);

            Arrays.sort(pCopy, pCopy[i].slopeOrder());

            for (int p = 0, first = 1, sista = 2; sista < pCopy.length; sista++)
            {
                Double p1 = pCopy[p].slopeTo(pCopy[first]);
                Double p2 = pCopy[p].slopeTo(pCopy[sista]);
                // System.out.printf("first : %d", first);
                while (sista < pCopy.length
                        && p1.equals(p2))
                {
                    sista++;
                    if (sista < pCopy.length) p2 = pCopy[p].slopeTo(pCopy[sista]);
                }

                if (sista - first >= 3 && pCopy[p].compareTo(pCopy[first]) < 0)
                {
                    lineSegments.add(new LineSegment(pCopy[p], pCopy[sista - 1]));
                    N++;
                    // System.out.printf("N: %d", N);
                }

                first = sista;
            }
        }

    }

    private boolean duplicate(Point[] points)
    {
        for (int i = 1; i < points.length; i++)
        {
            if (points[i].compareTo(points[i - 1]) == 0)
                return true;
        }
        return false;
    }

    public int numberOfSegments()
    {
        return N;
    }

    public LineSegment[] segments()
    {
        LineSegment[] lines = new LineSegment[N];
        // System.out.print(N);
        for (int i = 0; i < N; i++)
        {
            // System.out.print("One");
            lines[i] = lineSegments.get(i);
        }
        // show(lines);
        return lines;
    }

    /**
     * Selection sort which sorts one array, and sorts the second array in
     * accordance with the key in the first, example input: a= {'c','b','a'} b=
     * {1,2,3} leads to output: a ={'a','b','c'} b = {3,2,1}
     * 
     * @param a
     */
    

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

    // Unit test for selection sort, which sorts two arrays
    /*
     * public static void selectionTest() { Character[] a= {'d','c','b','a'};
     * Integer[] b= {4,1,2,3}; show(a); show(b);
     * 
     * selection(a, b); show(a); show(b); }
     */
    public static void main(String[] args)
    {
        // read the n points from a file

        int n = 8;
        int[] xs = { 10000, 0, 3000, 7000, 20000, 3000, 14000, 6000 };
        int[] ys = { 0, 10000, 7000, 3000, 21000, 4000, 15000, 7000 };
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++)
        {
            int x = xs[i];
            int y = ys[i];
            points[i] = new Point(x, y);
        }
        /*
         * In in = new In(args[0]); int n = in.readInt(); Point[] points = new Point[n];
         * for (int i = 0; i < n; i++) { int x = in.readInt(); int y = in.readInt();
         * points[i] = new Point(x, y); }
         */

        // draw the points
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points)
        {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments())
        {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        // selectionTest();

    }

}
