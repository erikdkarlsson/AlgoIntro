/**
 * Contains implementations and different exercises from 
 * Algorithms, between 1.1.16 through 1.1.19
 * @author erikkarlsson
 *
 */

public class AlgoritmsIntro1116 {
	
	public static void main(String[] args)
	{
		String temp = exR1(6);
		System.out.println(temp);
		System.out.println(mystery(2,25));
		System.out.println(fib(40));//0 1 1 2 3 5 8 ... 45 seems like a tipping point where delay is noticeable
		System.out.println(F(100));
	}
	
	public static String exR1(int n)
	{
		if (n <=0) return "";
		return exR1(n-3) + n + exR1(n-2) + n;
	}
	
	public static int mystery(int a, int b)
	{
		if (b == 0) return 0;
		if (b % 2 == 0) return mystery(a+a, b/2);
		//System.out.printf("a: %d, b: %d %n",a,b);
		return mystery(a+a, b/2) +a;
	}
	public static long fib(int N)
	{
		if (N == 0) return 0;
		if (N == 1) return 1;
		return fib(N-1)+fib(N-2);
	}
	public static long F(int N, long[] a)
	{
		if (N==0) return 0;
		if (N==1) return 1;
		if (a[N] != 0) return a[N];
		else a[N] = F(N-1, a) + F(N-2, a);
		return a[N];
		
	}
	public static long F(int N)
	{
		long[] a = new long[10000];
		if (N==0) return 0;
		if (N==1) return 1;
		return F(N-1, a) + F(N-2, a);
	}
}
