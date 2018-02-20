package ds.arrays.rotation.problems;

import ds.arrays.rotation.ArrayRotation;
import ds.arrays.rotation.ArrayRotationConstants;

public class DivisionBy8 {

	public static void main(String[] args){
		String s = "888";
		char[] c = s.toCharArray();
		System.out.println(combinationsDivisionBy8(c));
	}
	
	private static int combinationsDivisionBy8(char[] c){
		int count=0;
		ArrayRotation<Character> arrayRotation = new ArrayRotation<>();
		int size=c.length;
		if(size==1) return ((int)c[0]%8==0)?1:0;
		for(int i=0;i<size;i++){
			int lastThreeNumbers = 0;
			if(size==2) lastThreeNumbers = (int)c[1]+(int)c[0]*10;
			else lastThreeNumbers = (int)c[size-1]+(int)c[size-2]*10+(int)c[size-3]*100;
			if(lastThreeNumbers%8==0) count++;
			arrayRotation.charRotate(c, 1, ArrayRotationConstants.RIGHT);
		}
		
		return count;
	}
	
}
