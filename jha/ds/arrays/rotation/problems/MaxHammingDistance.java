package ds.arrays.rotation.problems;

public class MaxHammingDistance {

	public static void main(String[] args){
		int arr[] = {2, 4, 8, 0};
		System.out.println(maxHammingDistance(arr));
	}
	
	/**
	 * Q. Given an array of n elements, create a new array which is a rotation of given array and hamming distance between both the arrays is maximum.
	 *    Hamming distance between two arrays or strings of equal length is the number of positions at which the corresponding character(elements) are different.
	 * Algo: 1. Outer Iteration based on starting index for inner iteration
	 *       2. Inner Iteration goes from outer iteration index to end and then from 0 to outer iteration index-1
	 *       3. For a given outer iteration hamming distance is calculated and if greater than max hamming distance then at the end of both iteration we have maxHammingDistance
	 * 
	 * 
	 * {Time:O(n^2) _ Space:O(1)}
	 * 
	 * @param arr : input array
	 * @return : hamming distance
	 */
	public static int maxHammingDistance(int arr[]){
		int maxHammingDistance = 0;
		int size = arr.length;
		for(int i=1;i<size;i++){
			int currentHammingDistance = 0;
			for(int j=i;;j++){
				j=j%size;
				if((j-i>=0 && arr[j]!=arr[j-i]) || (j-i<0 && arr[j]!=arr[size + j-i])){
					currentHammingDistance++;
				}
				if(j==i-1) break;
			}
			if(currentHammingDistance>maxHammingDistance) maxHammingDistance=currentHammingDistance;
		}
		return maxHammingDistance;
	}
}
