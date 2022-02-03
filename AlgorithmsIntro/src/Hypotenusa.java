import java.util.Scanner;

public class Hypotenusa
{
    /**
     * Returns the hypotenuse of the rightsided triangle 
     * with catethus a and b
     * @param a
     * @param b
     * @return c, length of hypotenuse
     */
    public static double hypotenuse(double a, double b)
    {
        return Math.sqrt(Math.pow(a,2)+ Math.pow(b, 2));
    }
    /**
     * Returns volume of a sphere with radius r
     * @param r
     * @return
     */
    public static double sphereVolume(double r)
    {
        return (4*Math.PI*Math.pow(r,3)) /3.0f;
    }
    /**
     * Returns two real roots of the eq. ax^2+bx +c = 0,
     * or null if no real answers, uses an array to return answer 
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static double[] eqSolution(double a, double b, double c)
    {
        double x0;
        double x1;
        double sqrtExpr;
        double[] ans= new double[2]; 
        sqrtExpr = Math.pow(b,2) - (4.0 * a * c);
        if (sqrtExpr < 0) return ans;
        x0 = (-b + Math.sqrt(sqrtExpr))/ 2*a;
        x1 = (-b - Math.sqrt(sqrtExpr))/ 2*a;
        //System.out.println(sqrtExpr);
        ans[0] = x0;
        ans[1] = x1;
        return ans;
    }
    public static void main(String[] args)
    {
        
        //Unit test for hypotenuse
        //System.out.println(hypotenuse(3,4));
        
        //Answer should be  
        double ans[] = new double[2];
        ans = eqSolution(1.0, 4.0, 4.0);
        System.out.println(ans[0]);
        System.out.println(ans[1]);
        
    }

}
