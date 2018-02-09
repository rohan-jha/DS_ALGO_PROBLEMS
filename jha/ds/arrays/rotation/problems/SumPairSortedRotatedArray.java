package ds.arrays.rotation.problems;

import ds.arrays.rotation.ArrayRotation;

public class SumPairSortedRotatedArray {

	public static void main(String[] args){
		SumPairSortedRotatedArray main = new SumPairSortedRotatedArray();
		int arr[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
		System.out.println(main.doesPairSumExistSortedRotatedArray(arr, 26));
	}
	
	/**
	 * Algo: 1. Find the pivot
	 * 		 2. Take two pointers (i,j), i from pivot going right (towards larger numbers) and j from pivot-1 going left(towards smaller numbers)
	 *       3. Keep incrementing i if the sum is smaller and decrementing j if the sum is larger
	 *       4. Keep checking if the arr[i]+arr[j]==sum, else return -1;
	 * 
	 * {Time: O(n) _ Space: O(1)}
	 * 
	 * @param arr : input array
	 * @param sum : sum to be searched
	 * @return : boolean whether a pair exists with given sum
	 */
	public boolean doesPairSumExistSortedRotatedArray(int arr[], int sum){
		int d = ArrayRotation.findPivotSortedRotatedArray(arr, 0, arr.length-1);
		int size = arr.length;
		for(int i=d,j=(d>0)?d-1:size-1;;){
			if(arr[i]+arr[j]==sum) return true;
			else if(arr[i]+arr[j]>sum){
				j=(j==0)?size-1:j-1;
			}else{
				i=(i==size-1)?0:i+1;
			}
			if(i==j) return false;
		}
	}
}
