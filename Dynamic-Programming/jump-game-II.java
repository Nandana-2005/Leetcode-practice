import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        
        int jumps = 0;
        int l = 0;
        int r = 0;
        
        while(r<n-1){
            int farthest = 0;
            
            for(int i = l;i<=r;i++){
                farthest = Math.max(farthest,(i+arr[i]));
            }
            
            l = r + 1;
            r = farthest;
            jumps++;
        }
        
        System.out.println(jumps);
        
    }
}
