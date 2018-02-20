package ds.arrays.rearrangements.problems;

import ds.arrays.util.ArrayUtil;
import ds.math.util.MathUtil;

public class WeirdArraySorts {

	public static void main(String []args){
		int arr[] = {1, 2, 3, 4, 5, -6, -7, 8, 9, 10, 12, -14};
		//waveSorting(arr);
		absoluteSorting(arr, 5, 0, arr.length-1);
		ArrayUtil.printArray(arr);
	}
	
	/**
	 * Q: Given an unsorted array of integers, sort the array into a wave like array. An array ‘arr[0..n-1]’ is sorted in wave form if arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4] >= …..
	 * Algo: If we are expecting the current element to be smaller and it is not or vice versa then swap elements and flip the expectation
	 * 
	 * @param arr : Input array
	 */
	public static void waveSorting(int arr[]){
		if(arr.length<3) return;
		boolean expectingSmaller=(arr[1]>arr[0])?true:false;
		for(int i=2;i<arr.length;i++){
			if((arr[i]>arr[i-1] && expectingSmaller) || (arr[i]<arr[i-1] && !expectingSmaller)){
				ArrayUtil.swapArrVal(arr, i-1, i);
			}
			expectingSmaller = !expectingSmaller;
		}
	}
	
	/**
	 * Q: Given an array of n distinct elements and a number x, arrange array elements according to the absolute difference with x, i. e., element having minimum difference comes first and so on.
		  Note : If two or more elements are at equal distance arrange them in same sequence as in the given array.
     * Algo: 
     * 
	 * @param arr : Input array
	 * @param x : Given value
	 */
	public static void absoluteSorting(int arr[], int x, int start, int end){
		if(end<=start) return;
		int mid = start + (end-start)/2;
		absoluteSorting(arr, x, start, mid);
		absoluteSorting(arr, x, mid+1, end);
		mergeUsingSpace(arr, x, start, end);
	}
	
	/**
	 * Time : O(N*log(N)) Space : O(log(N))...due to recursion
	 * @param arr
	 * @param start
	 * @param end
	 */
	public static void mergeUsingSpace(int arr[], int x, int start, int end){
		int mid = start + (end-start)/2;
		//Merge the array start->mid-1 and mid->end
		int leftp=start;
		int rightp=mid+1;
		int[] tempArr=new int[end-start+1];
		int tempIndex=0;
		while(tempIndex<end-start+1){
			if(leftp>mid){
				tempArr[tempIndex++]=arr[rightp++];
				continue;
			}else if(rightp>end){
				tempArr[tempIndex++]=arr[leftp++];
				continue;
			}
			if(MathUtil.absDiff(x, arr[leftp])>MathUtil.absDiff(x, arr[rightp])){
				tempArr[tempIndex++]=arr[rightp++];
			}else if(MathUtil.absDiff(x, arr[leftp])<=MathUtil.absDiff(x, arr[rightp])){//Here equals(=) is used to preserve the order by inserting the leftp instead of rightp 
				tempArr[tempIndex++]=arr[leftp++];
			}
		}
		for(int i=0;i<=end-start;i++){
			arr[start+i]=tempArr[i];
		}
	}
	
}
