package ds.arrays.rotation.problems;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HackerRankPerfTestTOBE_DEL_LATER {

	public static void main(String[] args) throws Exception{
		File f = new File("C:\\Users\\jharohan\\Desktop\\input.txt");

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(f);
        int n = s.nextInt();
        List<Integer>[] a = new ArrayList[n];
        int numOfQueries = s.nextInt();
        int lastAnswer = 0;
        for(int i=0;i<numOfQueries;i++){
            if(1==s.nextInt()){
                int seq = (s.nextInt()^lastAnswer)%n;
                if(null == a[seq]) a[seq] = new ArrayList<Integer>();
                a[seq].add(s.nextInt());
            }else if(2==s.nextInt()){
                int seq = (s.nextInt()^lastAnswer)%n;
                if(null != a[seq]){ lastAnswer=a[seq].get(s.nextInt()%a[seq].size());}
                System.out.println(lastAnswer);
            }
        }
        s.close();
    }
    
    static void reverseSubArray(int arr[], int i, int j){
        if(i>=j) return;
		for(;i<j;i++,j--){
			int temp = arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
		}
    }
}
