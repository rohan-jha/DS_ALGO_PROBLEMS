package ds.arrays.sorting;

import ds.arrays.rotation.ArrayRotation;
import ds.arrays.util.ArrayUtil;

public class MergeSort {

	public static void main(String[] args){
		int[] arr = {12,14,4,19,5,1,7,8,10,12,40,1,15,18,23,50,18};
		mergeSort(arr, 0, arr.length-1);
		ArrayUtil.printArray(arr);
	}
	
	public static void mergeSort(int arr[], int start, int end){
		if(end<=start) return;
		/*if(end-start==1){
			if(arr[end]<arr[start]) ArrayUtil.swapArrVal(arr, start, end);
			return;
		}*/
		int mid = start + (end-start)/2;
		mergeSort(arr, start, mid);
		mergeSort(arr, mid+1, end);
		//mergeInPlaceUsingRotate(arr, start, end);
		mergeUsingSpace(arr, start, end);
	}
	
	/**
	 * Time : O(N*N) Space: O(1) 
	 * 
	 * For better implementation: https://stackoverflow.com/questions/2571049/how-to-sort-in-place-using-the-merge-sort-algorithm
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 */
	public static void mergeInPlaceUsingRotate(int arr[], int start, int end){
		int mid = start + (end-start)/2;
		//Merge the array start->mid-1 and mid->end
		int leftp=start;
		int rightp=mid+1;
		while(rightp<=end){
			if(arr[leftp]>=arr[rightp]){
				ArrayRotation.rotateSubArray(arr, leftp, rightp);
				leftp++;rightp++;
			}else{
				leftp++;
			}
		}
	}
	
	/**
	 * Time : O(N*log(N)) Space : O(log(N))...due to recursion
	 * @param arr
	 * @param start
	 * @param end
	 */
	public static void mergeUsingSpace(int arr[], int start, int end){
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
			if(arr[leftp]>=arr[rightp]){
				tempArr[tempIndex++]=arr[rightp++];
			}else if(arr[leftp]<arr[rightp]){
				tempArr[tempIndex++]=arr[leftp++];
			}
		}
		for(int i=0;i<=end-start;i++){
			arr[start+i]=tempArr[i];
		}
	}
	
}
