import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints
{
    private int N;
    private ArrayList<LineSegment> lineSegments = new ArrayList<LineSegment>();
    //Double[] slopes;

    public BruteCollinearPoints(Point[] points)
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

        for (int first = 0; first < pCopy.length - 3; first++)
        {
            for (int second = first + 1; second < pCopy.length - 2; second++)
            {
                double slopeOneTwo = pCopy[first].slopeTo(pCopy[second]);
                for (int third = second + 1; third < pCopy.length - 1; third++)
                {
                    double slopeOneThree = pCopy[first].slopeTo(pCopy[third]);
                    if (slopeOneTwo == slopeOneThree)
                    {
                        for (int fourth = third + 1; fourth < pCopy.length; fourth++)
                        {
                            double slopeOneFour = pCopy[first].slopeTo(pCopy[fourth]);
                            if (slopeOneTwo == slopeOneFour)
                            {
                                lineSegments.add(new LineSegment(pCopy[first], pCopy[fourth]));
                                N++;
                            }
                        }
                    }
                }

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

    /**
     * 
     * @return number of linesegments
     */

    public int numberOfSegments()
    {
        return N;
    }

    /**
     * Returns each segment containing 4 points exactly once
     * 
     * @return
     */
    public LineSegment[] segments()
    {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

}
