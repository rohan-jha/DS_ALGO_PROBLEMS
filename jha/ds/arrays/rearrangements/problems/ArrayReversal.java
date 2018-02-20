package ds.arrays.rearrangements.problems;

import ds.arrays.util.ArrayUtil;

public class ArrayReversal<E> {

	public static void main(String[] args){
		int[] intArr = {1,2,3,4,5,6,7,8,9,10};
		reverseArrayXOR(intArr, 0);
		ArrayUtil.printArray(intArr);
		ArrayReversal<Integer> arrayReversal = new ArrayReversal<>();
		Integer[] arr = {1,2,3,4,5,6,7,8,9,10};
		ArrayUtil<Integer> au = new ArrayUtil<>();
		arrayReversal.reverseArray(arr);
		au.printGenericArray(arr);
		arrayReversal.reverseArrayRecur(arr, 0);
		au.printGenericArray(arr);
	}
	
	/**
	 * Reverses an array using XOR FUNCTION (^)
	 * 
	 * i^i=0; 0^j=j; i^i^j=j;
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 */
	static void reverseArrayXOR(int arr[], int start){
		int size=arr.length;
		while (start<=(size-1)/2){
			arr[start] ^= arr[size-1-start];//i'=i^j
			arr[size-1-start] ^= arr[start];//j'=j^i'=j^i^j=i
			arr[start] ^= arr[size-1-start];//i''=i'^j'=i^j^i=j
			start++;
		} 
	}
	
	/**
	 * Reverses the array iteratively 
	 * 
	 * Time : O(N)
	 * 
	 * @param arr
	 */
	public void reverseArray(E arr[]){
		int size = arr.length;
		for (int i=0;i<=(size-1)/2;i++){
			E temp = arr[i];
			arr[i]=arr[size-1-i];
			arr[size-1-i]=temp;
		}
	}
	
	/**
	 * Reverses the array recuring
	 * 
	 * Time : O(N)
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	public void reverseArrayRecur(E arr[], int i){
		int size=arr.length;
		if (i>(size-1)/2) return;
		E temp = arr[i];
		arr[i]=arr[size-1-i];
		arr[size-1-i]=temp;
		reverseArrayRecur(arr, i+1);
	}
}
