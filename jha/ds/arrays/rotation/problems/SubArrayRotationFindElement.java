package ds.arrays.rotation.problems;

public class SubArrayRotationFindElement {
	
	/**
	 * An array consisting of N integers is given. There are several Right Circular Rotations of range[L..R] that we perform. After performing these rotations, we need to find element at a given index.
		Examples:
		Input : arr[] : {1, 2, 3, 4, 5}
		        ranges[] = { {0, 2}, {0, 3} }
		        index : 1
		Output : 3
		Explanation : After first given rotation {0, 2}
		                arr[] = {3, 1, 2, 4, 5}
		              After second rotation {0, 3} 
		                arr[] = {4, 3, 1, 2, 5}
		After all rotations we have element 3 at given index 1.
		
		Algo: Dont actually rotate the array but just calculate the new index position after every rotation
	 * @param arr
	 * @param ranges
	 * @param index
	 * @return
	 */
	static int findElement(int[] arr, int[][] ranges, int index)
	{
		for (int i = ranges.length - 1; i >= 0; i--) {

			int left = ranges[i][0];
			int right = ranges[i][1];

			// Else Rotation will not have any effect
			if (left <= index && right >= index) {
				if (index == left)
					index = right;
				else
					index--;
			}
		}
		// Returning new element
		return arr[index];
	}

	public static void main (String[] args) {
		int[] arr = { 1, 2, 3, 4, 5 };
		
		// Ranges according to 0-based indexing
		int[][] ranges = { { 0, 2 }, { 0, 3 } };

		int index = 1;
		System.out.println(findElement(arr, ranges, index));
	}
}
