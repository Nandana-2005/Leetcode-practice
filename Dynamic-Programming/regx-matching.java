
import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String p = sc.nextLine();
        
        int m = s.length();
        int n = p.length();
        
        boolean[][] dp = new boolean[m+1][n+1];
        
        dp[0][0] = true;
        
        //Handle patterns like a*,a*b*,a*b*c*
        //j starts with 2 since the first possible pattern is a* 
        //since dp is 1-based indexing and arrays are 0-based indexing, we do j-1 for accessing the array properly
        //the following loop is to fill the first row in dp with 
        //empty string and a given pattern to check if that pattern can create an empty string
        for(int j = 2;j<=n;j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        
        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=n;j++){
                
                char scur = s.charAt(i-1);
                char pcur = p.charAt(j-1);
                
                //Normal match or '.'
                
                if(pcur == '.' || pcur == scur){
                    dp[i][j] = dp[i-1][j-1];
                }
                
                // "*"
                else if(pcur == '*'){
                    //zero occurrences
                    dp[i][j] = dp[i][j-2];
                    
                    char prev = p.charAt(j-2);
                    
                    //one or more occurrences
                    
                    if(prev == '.' || prev == scur){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        
        System.out.println(dp[m][n]);
    }
}
