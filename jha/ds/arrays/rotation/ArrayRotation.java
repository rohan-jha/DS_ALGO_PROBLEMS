package ds.arrays.rotation;

import ds.arrays.util.ArrayUtil;
import ds.math.util.MathUtil;

public class ArrayRotation {
	
	public static void main(String[] args){
		int arr[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
		rotate(arr, 4, ArrayRotationConstants.LEFT);
		//blockRotate(arr, 13, ArrayRotationConstants.LEFT);
		/*backRotate(arr, 5, ArrayRotationConstants.RIGHT);
		gcdRotate(arr, 11, ArrayRotationConstants.RIGHT);
		reverseRotate(arr, 11, ArrayRotationConstants.RIGHT);
		reverseRotate(arr, 7, ArrayRotationConstants.RIGHT);*/
		ArrayUtil.printArray(arr);
		System.out.println(findPivotSortedRotatedArray(arr, 0, arr.length-1));
	}
	
	/**
	 * Algo1: Create a temp array and put the all d elements in it and change the actual array {Time:O(n) _ Space:O(d)}
	 * Algo2: Shift all the elements one place...repeat d times {Time:O(n*d) _ Space:O(1)}
	 * Algo3: Implemented Below: 
	 *        1. Assign startIndex, counter and nextIndex as 0
	 * 		  2. Start swapping every dth index with startIndex and keep changing the nextIndex+=d, until the nextIndex reaches startIndex or counter gets over size of array
	 * 		  3. If nextIndex==startIndex, then increment the startIndex and nextIndex and repeat step 2
	 * 
	 * {Time:O(n) _ Space:O(1)}
	 * 
	 * @param arr : Input array
	 * @param d : rotate by d number of elements
	 * @param dir: direction of rotation
	 * 
	 * {@link https://www.geeksforgeeks.org/array-rotation/}
	 */
	public static void rotate(int[] arr, int d, int dir){
		if(d<=0) return;
		int size = arr.length;
		while(d>size-1){
			d=d-size;
		}
		if(d==0) return;
		if(dir == ArrayRotationConstants.LEFT) d = size-d;
		/* d is a number from 1 to arr.length-1*/
		int counter = 0;
		int startIndex = 0;
		int nextIndex = 0;
		while(counter<size){
			counter++;
			nextIndex += d;
			if(nextIndex > size-1){
				nextIndex -= size;
			}
			if(nextIndex == startIndex){
				startIndex++; nextIndex++;
			}else{
				ArrayUtil.swapArrVal(arr, startIndex, nextIndex);
				//ArrayUtil.printArray(arr);
			}
		}
	}

	/**
	 * Algo4:
	 * Initialize A = arr[0..d-1] and B = arr[d..n-1]
		1) Do following until size of A is equal to size of B
		
		  a)  If A is shorter, divide B into Bl and Br such that Br is of same 
		       length as A. Swap A and Br to change ABlBr into BrBlA. Now A
		       is at its final place, so recur on pieces of B.  
		
		   b)  If A is longer, divide A into Al and Ar such that Al is of same 
		       length as B Swap Al and B to change AlArB into BArAl. Now B
		       is at its final place, so recur on pieces of A.
		
		2)  Finally when A and B are of equal size, block swap them.
	 * 
	 * {Time: O(n) _ Space: O(1)}
	 * 
	 * @param arr : input array
	 * @param d : rotate by d indexes
	 * @param dir : direction of rotation
	 */
	public static void blockRotate(int arr[], int d, int dir){
		if(d<=0) return;
		int size = arr.length;
		while(d>size-1){
			d=d-size;
		}
		if(d==0) return;
		if(dir == ArrayRotationConstants.LEFT) d = size-d;
		
		int startLeftBlockIndex = 0;
		int endLeftBlockIndex = d-1;
		int startRightBlockIndex = d;
		int endRightBlockIndex = size-1;
		int leftBlockSize = endLeftBlockIndex-startLeftBlockIndex+1;
		int rightBlockSize = endRightBlockIndex-startRightBlockIndex+1;
		do{
			if(leftBlockSize==rightBlockSize){
				ArrayUtil.blockSwap(arr, startLeftBlockIndex, startRightBlockIndex, leftBlockSize);
			}else{
				if(leftBlockSize>rightBlockSize){
					ArrayUtil.blockSwap(arr, endLeftBlockIndex-rightBlockSize+1, startRightBlockIndex, rightBlockSize);
					endLeftBlockIndex -= rightBlockSize;
				}else{
					ArrayUtil.blockSwap(arr, startLeftBlockIndex, startRightBlockIndex, leftBlockSize);
					startRightBlockIndex+=leftBlockSize;
				}
				leftBlockSize = endLeftBlockIndex-startLeftBlockIndex+1;
				rightBlockSize = endRightBlockIndex-startRightBlockIndex+1;
				if(leftBlockSize==rightBlockSize){
					ArrayUtil.blockSwap(arr, startLeftBlockIndex, startRightBlockIndex, leftBlockSize);
				}
			}
		}while(endLeftBlockIndex-startLeftBlockIndex!=endRightBlockIndex-startRightBlockIndex);
	}

	/**
	 * Algo5: This is Algo3 starting from end of the array 
	 */
	public static void backRotate(int[] arr, int d, int dir){
		if(d<=0) return;
		int size = arr.length;
		while(d>size-1){
			d=d-size;
		}
		if(d==0) return;
		if(dir == ArrayRotationConstants.LEFT) d = size-d;
		/* d is a number from 1 to arr.length-1*/
		int counter = 0;
		int startIndex = size-1;
		int nextIndex = size-1;
		while(counter<size){
			counter++;
			nextIndex += d;
			if(nextIndex > size-1){
				nextIndex -= size;
			}
			if(nextIndex == startIndex){
				startIndex--; nextIndex--;
			}else{
				ArrayUtil.swapArrVal(arr, startIndex, nextIndex);
				ArrayUtil.printArray(arr);
				System.out.println();
			}
		}
	}

	/**
	 * Algo6: Rotate using GCD(n,d)
	 * 		  1. Let arr[] be {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12} and d=3... gcd(3,12) = 3

				a)    Elements are first moved in first set ---> Every element is shifted d indexes
				          arr[] after this step --> {4 2 3 7 5 6 10 8 9 1 11 12}
				b)    Then in second set.
				          arr[] after this step --> {4 5 3 7 8 6 10 11 9 1 2 12}
				c)    Finally in third set ... (This is repeated GCD(n,d) number of times)
				          arr[] after this step --> {4 5 6 7 8 9 10 11 12 1 2 3}
	 * 
	 * {Time:O(n) _ Space:O(1)}
	 * 
	 * @param arr : input array
	 * @param d : rotate by d indexes
	 * @param dir : direction of rotation
	 */
	public static void gcdRotate(int arr[], int d, int dir){
		if(d<=0) return;
		int size = arr.length;
		while(d>size-1){
			d=d-size;
		}
		if(d==0) return;
		if(dir == ArrayRotationConstants.LEFT) d = size-d;
		/* d is a number from 1 to arr.length-1*/
		int i, j, k, temp;
        for (i = 0; i < MathUtil.gcd(d, size); i++) 
        {
            /* move i-th values of blocks */
            temp = arr[i];
            j = i;
            while (1 != 0) 
            {
                k = j + d;
                if (k >= size) 
                    k = k - size;
                if (k == i) 
                    break;
                arr[j] = arr[k];
                j = k;
            }
            arr[j] = temp;
        }
	}
	
	/**
	 * Algo7: Rotate by using reversal of elements
	 * rotate(arr[], d, n)
     * reverse(arr[], 1, d) ;
     * reverse(arr[], d + 1, n);
  	 * reverse(arr[], l, n);
	 * 
	 * {Time: O(n) _ Space: O(1)}
	 * 
	 * @param arr : input array
	 * @param d : rotate by d elements
	 * @param dir : direction of rotation
	 */
	public static void reverseRotate(int arr[], int d, int dir){
		if(d<=0) return;
		int size = arr.length;
		while(d>size-1){
			d=d-size;
		}
		if(d==0) return;
		if(dir == ArrayRotationConstants.RIGHT) d = size-d;
		/* d is a number from 1 to arr.length-1*/
		
		ArrayUtil.reverseSubArray(arr, 0, d-1);
		ArrayUtil.reverseSubArray(arr, d, size-1);
		ArrayUtil.reverseSubArray(arr, 0, size-1);
	}
	
	/**
	 * Q: Find the pivot of a sorted and rotated array
	 * Algo: 1. Find mid
	 *       2. If arr[mid]<arr[mid-1] return mid
	 *       3. If arr[mid]>arr[j] ---> call recursion on right side of array
	 *       4. Else call recursion of left side of array
	 *       
	 * {Time: O(log(n)) _ Space: O(1)}
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 * @return
	 */
	public static int findPivotSortedRotatedArray(int arr[], int i, int j){
		if (i>j) return 0;
		if(i==j) return i;
		int mid = i+(j-i)/2;
		if (mid < j && arr[mid+1] < arr[mid])
            return (mid + 1);
		if (mid > i && arr[mid] < arr[mid - 1])
            return mid;
		if (arr[j] > arr[mid])
            return findPivotSortedRotatedArray(arr, i, mid - 1);
		
        return findPivotSortedRotatedArray(arr, mid + 1, j);
	}
	
}
