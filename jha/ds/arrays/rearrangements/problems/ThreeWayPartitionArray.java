package ds.arrays.rearrangements.problems;

import ds.arrays.util.ArrayUtil;

public class ThreeWayPartitionArray {

	public static void main(String[] args){
		int arr[] = {10, 2, 13, 6, 5, -6, 7, 8, 19, 8, 12, -14};
		//threeWayPartitioning(arr,1,9);
		threeWayPartitioningOtherAlgo(arr, 1, 9);
		ArrayUtil.printArray(arr);
	}
	
	/**
	 * Q:Given an array and a range [lowVal, highVal], partition the array around the range such that array is divided in three parts.
		1) All elements smaller than lowVal come first.
		2) All elements in range lowVal to highVVal come next.
		3) All elements greater than highVVal appear in the end.
		The individual elements of three sets can appear in any order. 
	 * 
	 * Time: O(N) Space: O(1)
	 * 
	 * Algo 1: 1. Initialize three variables, partitionLeftIndex, currentLeftIndex and currentRightIndex
	 * 	     2. Iterate over the array
	 * 			2.1 If currentLeftIndex element is greater than high, then swap currentLeft and currentRight and decrease currentRight (as we have set the first element @currentRight)
	 * 			2.2 Else if currentLeftIndex element is less than low, then if partitionLeft is not initialized then increment currentLeft otherwise swap currentLeft and partitionLeft and increment partitionLeft
	 * 			2.3 Else if currentLeftIndex element lies in (low, high), then if partitionLeft is not initialized then increment currentLeft and assign partitionleft to currentleft otherwise increment currentLeft
	 * 
 	 * @param arr : Input Array
	 */
	public static void threeWayPartitioning(int[] arr, int low, int high){
		if(high<low) return;
		int partitionLeftIndex=-1;
		int currentLeftIndex=0;
		int currentRightIndex=arr.length-1;
		for(int i=0;i<arr.length;i++){
			if(arr[currentLeftIndex]>high){
				ArrayUtil.swapArrVal(arr, currentLeftIndex, currentRightIndex--);
			}else if(arr[currentLeftIndex]<low){
				if(partitionLeftIndex==-1){
					currentLeftIndex++;
				}else{
					ArrayUtil.swapArrVal(arr, currentLeftIndex, partitionLeftIndex);
					partitionLeftIndex++;
				}
			}else{
				if(partitionLeftIndex==-1){
					partitionLeftIndex=currentLeftIndex++;
				}else{
					currentLeftIndex++;	
				}
			}
		}
	}
	
	/**
	 * Q:Given an array and a range [lowVal, highVal], partition the array around the range such that array is divided in three parts.
		1) All elements smaller than lowVal come first.
		2) All elements in range lowVal to highVVal come next.
		3) All elements greater than highVVal appear in the end.
		The individual elements of three sets can appear in any order. 
	 * 
	 * Time: O(N) Space: O(1)
	 * 
	 * Algo 2: Almost similar uses one less variable
	 * 
 	 * @param arr : Input Array
	 */
	public static void threeWayPartitioningOtherAlgo(int[] arr, int lowVal, int highVal){
		int  n = arr.length;
        // Initialize ext available positions for smaller (than range) and greater elments
        int start = 0, end = n-1;
        for(int i = 0; i < end;){
            // If current element is smaller than range, put it on next available smaller position.
            if(arr[i] < lowVal){
            	ArrayUtil.swapArrVal(arr, start++, i++);
            }
            // If current element is greater than range, put it on next available greater position.
            else if(arr[i] > highVal){
                ArrayUtil.swapArrVal(arr, i, end--);
            }
            else i++;
        }
	}
}
