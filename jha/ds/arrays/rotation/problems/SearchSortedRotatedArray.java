package ds.arrays.rotation.problems;

import ds.arrays.rotation.ArrayRotation;
import ds.arrays.rotation.ArrayRotationConstants;
import ds.arrays.util.ArrayUtil;

public class SearchSortedRotatedArray {

	public static void main(String[] args){
		SearchSortedRotatedArray searchSortedRotatedArray = new SearchSortedRotatedArray();
		int arr[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
		ArrayRotation.rotate(arr, 5, ArrayRotationConstants.RIGHT);
		System.out.println(searchSortedRotatedArray.searchSortedRotatedArray(arr, 0, arr.length-1, 10));
	}
	
	/**
	 * Q: Find given key index in a sorted and rotated array
	 * Algo: 1. Find mid index, if mid value equals key return mid index
	 * 		 2. If start index value < mid index value ---> Pivot index is on right
	 * 			2.1. If key lies between start and mid value ---> Do a binary search on left subarray (as the left subarray is sorted)
	 * 			2.2. Else recursively call the search on right subarray
	 *       3. Else ---> Pivot is on left
	 *       	3.1. If key lies between mid and end value ---> Do a binary search on right subarray (as the right subarray is sorted)
	 *          3.2. Else recursively call the search on left subarray
	 * 
	 * {Time:O(log(n)) _ Space:O(1)}
	 * 
	 * @param arr : input array
	 * @param i : Start index for searching
	 * @param j : End index for searching
	 * @param key : key to be searched
	 * @return : index of key or -1
	 */
	public int searchSortedRotatedArray(int arr[], int i, int j, int key){
		if(i>j) return -1;
		int mid = i + (j-i)/2;
		if(arr[i] == key) return i;
		if(arr[mid] == key) return mid;
		if(arr[j] == key) return j;
		if(arr[i]<arr[mid]){
			if(arr[i]<key && arr[mid]>key){
				return ArrayUtil.binarySearchSortedArray(arr, i, mid-1, key);
			}else{
				return searchSortedRotatedArray(arr, mid, j, key);
			}
		}else{
			if(arr[mid]<key && arr[j]>key){
				return ArrayUtil.binarySearchSortedArray(arr, mid, j, key);
			}else{
				return searchSortedRotatedArray(arr, i, mid-1, key);
			}
		}
	}
}
