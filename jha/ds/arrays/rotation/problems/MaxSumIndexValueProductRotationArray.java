package ds.arrays.rotation.problems;

public class MaxSumIndexValueProductRotationArray {

	public static void main(String[] args){
		int arr1[] = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		MaxSumIndexValueProductRotationArray main = new MaxSumIndexValueProductRotationArray();
		System.out.println(main.maxSumIndexValueProductRotationArray(arr1));
	}
	
	/**
	 * Algo: 
	 *      1) Compute sum of all array elements. Let this sum be 'arrSum'.
			2) Compute R0 by doing i*arr[i] for given array. 
   			   Let this value be indexValueProductSum.
			3) Initialize result: maxIndexValueProductSum = indexValueProductSum // maxVal is result.
			// This loop computes Rj from  Rj-1 
			4) Do following for j = 1 to n-1
			   a) indexValueProductSum = indexValueProductSum + arrSum-n*arr[n-j];
			   b) If (indexValueProductSum > maxIndexValueProductSum)
			            maxIndexValueProductSum = indexValueProductSum   
			5) Return maxIndexValueProductSum
			
	   {Time:O(n) _ Space:O(1)}
	 * @param arr : input array
	 * @return : max(sum(i*arr[i])) after all possible rotations
	 */
	public int maxSumIndexValueProductRotationArray(int arr[]){
		int indexValueProductSum = 0;
		int arrSum = 0;
		int maxRotationIndex = 0;
		int maxIndexValueProductSum = 0;
		int size = arr.length;
		for(int i=0;i<size;i++){
			indexValueProductSum += i*arr[i];
			arrSum += arr[i];
		}
		maxIndexValueProductSum = indexValueProductSum;
		
		for(int i=1;i<size;i++){
			indexValueProductSum += arrSum - size*arr[size-i];
			if(indexValueProductSum>maxIndexValueProductSum){
				maxIndexValueProductSum=indexValueProductSum;
				maxRotationIndex=i;
			}
		}
		return maxIndexValueProductSum;//return maxRotationIndex if the state of array is need for maxProductSum
	}
}
