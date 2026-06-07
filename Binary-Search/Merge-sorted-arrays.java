// You are using Java
import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int m = sc.nextInt();
        int[] num1 = new int[m];
        for(int i= 0;i<m;i++){
            num1[i] = sc.nextInt();
        }
        
        
        int n = sc.nextInt();
        int[] num2 = new int[n];
        for(int i= 0;i<n;i++){
            num2[i] = sc.nextInt();
        }
        
        int[] smaller = m>n ? num2 : num1;
        int[] larger = m>n ? num1 : num2;
        int totalLength = m+n;
        
        
        int low = 0,high = smaller.length;
        
        while(low<=high){
            int p1 = (low+high)/2;
            int p2 = ((totalLength + 1)/2) - p1;
            
            int l1 = p1 == 0 ? Integer.MIN_VALUE : smaller[p1-1];
            int r1 = p1 == smaller.length ? Integer.MAX_VALUE : smaller[p1];
            
            int l2 = p2 == 0 ? Integer.MIN_VALUE : larger[p2-1];
            int r2 = p2 == larger.length ? Integer.MAX_VALUE : larger[p2];
            
            if(l1 <= r2 && l2 <=r1){
                if(totalLength % 2 == 0){
                    System.out.printf("%.2f",((Math.max(l1,l2)+Math.min(r1,r2))/2.00));
                }
                else{
                    System.out.printf("%.2f",((double)Math.max(l1,l2)));
                }
                return;
            }
            
            if(l1 > r2){
                high = p1 - 1;
            }
            else{
                low = p1 + 1;
            }
        }
        
    }
}
