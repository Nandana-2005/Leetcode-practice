// You are using Java
import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String s1 = sc.next();
        String s2 = sc.next();
        
        int m = s1.length();
        int n = s2.length();
        
        int[][] dp = new int[m+1][n+1];
        
        for(int i = 0;i<m;i++){
            dp[i][0]=i;
        }
        
        for(int j = 0;j<n;j++){
            dp[0][j]=j;
        }
        
        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=n;j++){
                
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = 1+ Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                }
            }
        }
        
        System.out.println(dp[m][n]);
        
    }
}
