package ds.arrays.util;

public class ArrayUtil {

	/**
	 * Print array values in order
	 * @param arr
	 */
	public static void printArray(int[] arr){
		for(int i:arr){
			System.out.print(i+",");
		}
		System.out.println();
	}
	
	/**
	 * Binary Search for a sorted array within given indexes
	 * 
	 * @param arr : Array to be searched
	 * @param i : start index for searching
	 * @param j : end index for searching
	 * @param key : to be searched key
	 * @return index where key is present or -1
	 */
	public static int binarySearchSortedArray(int arr[], int i, int j, int key){
		if(i>j) return -1;
		//int mid = (i+j)%2==0?(i+j)/2:(i+j)/2+1;
		int mid = i+(j-i)/2;
		if(key<arr[i] || key>arr[j]) return -1;
		if(key==arr[i]) return i;
		if(key==arr[mid]) return mid;
		if(key==arr[j]) return j;
		if(key>arr[mid]){
			return binarySearchSortedArray(arr, mid, j , key);
		}else{
			return binarySearchSortedArray(arr, i, mid-1 , key);
		}
	}
	
	/**
	 * Reverses the array elements within given indexes
	 * 
	 * @param arr : input array
	 * @param i : start index for reversing
	 * @param j : end index for reversing
	 */
	public static void reverseSubArray(int arr[], int i, int j){
		if(i>=j) return;
		for(;i<j;i++,j--){
			int temp = arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
		}
	}
	
	/**
	 * 
	 * Swaps the whole blocks of array with given start indexes and block size
	 * 
	 * @param arr : input array
	 * @param i : start index of left block
	 * @param j : start index of right block
	 * @param blockSize : size of the block
	 */
	public static void blockSwap(int arr[], int i, int j, int blockSize){
		int endIndex = i+blockSize;
		for (;i<endIndex;i++,j++){
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		//printArray(arr);
		//System.out.println();
	}
	
	/**
	 * Swaps two given array values
	 * 
	 * @param arr : input array
	 * @param i : index 1 for swapping
	 * @param j : index 2 for swapping
	 */
	public static void swapArrVal(int[] arr, int i, int j){
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}
	
}
