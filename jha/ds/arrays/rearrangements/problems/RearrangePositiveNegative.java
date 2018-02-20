package ds.arrays.rearrangements.problems;

import ds.arrays.rotation.ArrayRotation;
import ds.arrays.util.ArrayUtil;

public class RearrangePositiveNegative {

	public static void main(String[] args){
		int arr[] = {1, 2, 3, 4, 5, -6, -7, 8, 9, 10, 12, -14};
		//rearrangePositiveNegativeAlternatively(arr);
		//rearrangePositiveNegativeOneSide(arr);
		//ArrayUtil.printArray(arr);
		//ArrayUtil.printArray(rearrangePositiveNegativeOneSideExtraSpace(arr));
		//rearrangePositiveNegativeOneSideNoOrder(arr);
		//rearrangePositiveNegativeOneSide(arr);
		rearrangePositiveNegativeOneSideRecur(arr, 0 , arr.length-1);
		ArrayUtil.printArray(arr);
	}
	
	/**
	 * Q1: An array contains both positive and negative numbers in random order. Rearrange the array elements so that positive and negative numbers are placed alternatively. 
	 * Number of positive and negative numbers need not be equal. 
	 * If there are more positive numbers they appear at the end of the array. If there are more negative numbers, they too appear in the end of the array.
	 * 
	 * Algo: Time O(N) _ Space O(1)
	 * 		 1. Assign p as pivot which has to be swapped to maintain the alternate order
	 * 		 2. Iterate over the array
	 * 		 3. If arr[i] and arr[p] are of different signs then swap the elements (As index p is expecting the different signed integer)
	 * 		 4. After swapping increase the pivot index by 2, if the new pivot index element and the element prior to that are separate signs then keep increasing pivot
	 * 			till we get the same signed integers side by side...then we have got our new pivot...keep iterating over 3 and 4
	 * 
	 * @param arr : input array
	 */
	public static void rearrangePositiveNegativeAlternatively(int arr[]){
		int p=0;
		for(int i=0;i<arr.length;i++){
			if(i<=p) continue;
			if(!isSameSign(arr[i],arr[p])){
				ArrayUtil.swapArrVal(arr, i, p);
				p+=2;
				while(p<arr.length && !isSameSign(arr[p], arr[p-1])){
					p+=1;
				}
			}
		}
	}
	
	/**
	 * Q2. Given an array of positive and negative numbers, arrange them such that all negative integers appear before all the positive integers in the array 
	 *    without using any additional data structure like hash table, arrays, etc. The order of appearance should be maintained.
	 * 
	 * Algo 1: Time O(N*N) _ Space O(1) {Worst Case: {1,-2,-3,-4,-5,-6}}
	 * 		   1. Assign pivot to -1 and Start Iteration
	 * 		   2. As soon as first positive integer is found assign the pivot to index
	 * 		   3. Once the pivot has found a positive index and then we find a negative index rotate the array right to get the negative integer at the start of the subarray
	 * 
	 * @param arr : input array
	 */
	public static void rearrangePositiveNegativeOneSideExtraTime(int arr[]){
		int p=-1;
		for(int i=0;i<arr.length;i++){
			if(arr[i]>=0 && p==-1){
				p = i;
			}else if(arr[i]<0 && p>-1){
				ArrayRotation.rotateSubArray(arr, p, i);
				p+=1;
			}
		}
	}

	/**
	 * Q2. Question Above with extra space allowed
	 * Algo 2: Time O(N) _ SpaceO(N) 
	 * 		   1. Iterate through original array and put all the negative values
	 * 		   2. Iterate through original array again and put all the positive values
	 * 
	 * @param arr
	 */
	public static int[] rearrangePositiveNegativeOneSideExtraSpace(int arr[]){
		int[] rearrangedArr = new int[arr.length];
		int index=-1;//Use index=0 and index++ later on
		for (int i=0;i<arr.length;i++){
			if(arr[i]<0){
				rearrangedArr[++index]=arr[i];
			}
		}
		for (int i=0;i<arr.length;i++){
			if(arr[i]>=0){
				rearrangedArr[++index]=arr[i];
			}
		}
		return rearrangedArr;
	}
	
	/**
	 * 
	 * Q2. Question above with in-place implementation without extra space...
	 *     Worst case is O(N*N)...If array has alternate positive and negative integers...to get better solution refer {@link #rearrangePositiveNegativeOneSideRecur(int[], int, int)}
	 * Algo 3: 1. Start designating {nl, nr} negative block, {pl, pr} positve block 
	 * 		   2. If Current element is negative
	 * 			  2.1. If no positive block is found and no negative block is found (i.e. first element) assign nl=nr=i;
	 * 			  2.2. If no positive block is found but negative block is present...nr=i; (Negative block size is increased)
	 * 		   	  2.3. If positive block is found but no negative block then assign nl=nr=i;
	 * 			  2.4. If previous block is negative block increment nr
	 * 			  2.5. If previous block is positive block reset nl=nr=i;
	 *         3. If Current element is positive
	 *         	  3.1. If no negative block found yet and no positive element is found yet (i.e. first element) assign nl=nr=i;
	 *         	  3.2. If no negative block found yet and only positive element is found assign pr=i;
	 *            3.3. If negative block is found and no positive block is found then pl=pr=i;
	 *            3.4. If previous block is positive block then increment pr;
	 *            3.5. If previous block is negative block then rotate the subarrays to swap positive and negative blocks and assign pl=i-(positive block size) and pr=i;
	 * 
	 * Time: Worst Case: O(N*N) Space: O(1)
	 * 
	 * @param arr : input array
	 * @return
	 */
	public static int[] rearrangePositiveNegativeOneSide(int arr[]){
		int nl=-1,nr=-1,pl=-1,pr=-1;
		
		for(int i=0;i<arr.length;i++){
			if(arr[i]<0){//Current Element is negative
				if(pl<0){//No positive element found yet
					if(nl<0) nl=nr=i; //First Element
					else nr=i; //Only negative block yet
				}else{//Positive block is found
					if(nl<0) nl=nr=i; //No prior negative block
					else{
						if(i-nr==1) nr++;//Previous block is negative block
						else nl=nr=i;//Previous block is positive block
					}
					if(i==arr.length-1){//If last element and positive block is present then rotate
						ArrayUtil.reverseSubArray(arr, nl, nr);
						ArrayUtil.reverseSubArray(arr, pl, pr);
						ArrayUtil.reverseSubArray(arr, pl, nr);
					}
				}
			}else{//Current Element is positive
				if(nl<0){//No negative element found yet
					if(pl<0) pl=pr=i;//First Element
					else pr=i; // Only positive block found yet
				}else{//negative block is found
					if(pl<0) pl=pr=i; //No prior positive block
					else{
						if(i-pr==1) pr++; //Previous block is positive block
						else{//Previous block is negative block...so rotate the positive and negative blocks
							ArrayUtil.reverseSubArray(arr, nl, nr);
							ArrayUtil.reverseSubArray(arr, pl, pr);
							ArrayUtil.reverseSubArray(arr, pl, nr);
							pl=i-(pr-pl+1);
							pr=i;
						}
					}
				}
			}
		}
		return arr;
	}
	
	/**
	 * Q2. Question above with in-place implementation without extra DS solved recursively...
	 * Algo 4: Logic is similar to merge sort...
	 * 		   1. Keep breaking the array in two parts and after breaking till smallest block
	 * 		   2. Then keep on merging the two blocks by rotating the two blocks indexed [{ln, lp}-{rn, rp}]--->[{ln, rn}-{lp, rp}]
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 * @return
	 */
	public static void rearrangePositiveNegativeOneSideRecur(int arr[], int i, int j){
		if(i>=j) return;
		if(j-i==1){
			if(arr[i]>0 && arr[j]<0) ArrayUtil.swapArrVal(arr, i, j);
		}
		int mid = i+(j-i)/2;
		rearrangePositiveNegativeOneSideRecur(arr, i, mid);
		rearrangePositiveNegativeOneSideRecur(arr, mid+1, j);
		mergePositiveNegativeBlocks(arr, i, j);
	}

	/**
	 * This function is used to merge the two halves of the array [{ln, lp}-{rn, rp}]--->[{ln, rn}-{lp, rp}]
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 */
	private static void mergePositiveNegativeBlocks(int arr[], int start, int end){
		int mid = start + (end-start)/2;
	    int i = start; // Initial index of 1st subarray
	    int j = mid + 1; // Initial index of IInd
	 
	    while (i <= mid && arr[i] < 0) i++;
	 
	    // arr[i..m] is positive
	    while (j <= end && arr[j] < 0) j++;
	 
	    // arr[j..r] is positive
	    // reverse positive part of left sub-array (arr[i..m])
	    ArrayUtil.reverseRecursively(arr, i, mid);
	    // reverse negative part of right sub-array (arr[m+1..j-1])
	    ArrayUtil.reverseRecursively(arr, mid + 1, j - 1);
	    // reverse arr[i..j-1]
	    ArrayUtil.reverseRecursively(arr, i, j - 1);
	}
	
	/**
	 * Q3: Given an array of positive and negative numbers, arrange them such that all negative integers appear before all the positive integers in the array 
	 *    without using any additional data structure like hash table, arrays, etc. The order of appearance need not be maintained.
	 * Algo: Time O(N) _ Space O(1)
	 * 		 1. Initialize pivot index p=0
	 * 		 2. Iterate through array, if current element is less than zero put it at pivot and increment pivot by 1 index
	 * @param arr
	 */
	public static void rearrangePositiveNegativeOneSideNoOrder(int arr[]){
		int size = arr.length;
		int p=0;
		for (int i=0;i<size;i++){
			if(arr[i]<0){
				ArrayUtil.swapArrVal(arr, p, i);
				p+=1;
			}
		}
		ArrayUtil.printArray(arr);
	}
	
	public static boolean isSameSign(int i, int j){
		return (i>=0&&j>=0) || (i<0&&j<0);
	}
}
