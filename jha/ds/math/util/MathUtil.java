package ds.math.util;

public class MathUtil {

	/**
	 * @param i
	 * @param j
	 * @return GCD of (i,j)
	 */
	public static int gcd(int i, int j){
	   if(j==0)
	     return i;
	   else
	     return gcd(j, i%j);
	}

	/**
	 * @param i
	 * @param j
	 * @return GCD of (i,j)
	 */
	public static int gcdGeneric(int i, int j){
		int gcd = 1;
		if (i>1 && j>1){
			int min = Math.min(i, j);
			for(int k=2;k<=min;k++){
				if(i % k == 0 && j % k == 0) gcd = k;
			}
		}
		return gcd;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return Absolute value of difference between (i,j) without sign
	 */
	public static int absDiff(int i, int j){
		return (i>j)?(i-j):(j-i);
	}
}
