import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int x : arr){
            set.add(x);
        }

      //check for every number from 1, and if anything is not found, print that
        int ans = 1;
        while(set.contains(ans)){
            ans++;
        }
        
        System.out.println(ans);
    }
}
