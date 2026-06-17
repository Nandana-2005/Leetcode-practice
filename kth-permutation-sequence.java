// You are using Java
import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        List<Integer> nums = new ArrayList<>();
        int fact = 1;
        
        //creating a list of numbers from 1 - n
        //finding factorial of (n-1) since 1 place will be fixed and (n-1)! will be the value we need
        
        for(int i = 1;i<n;i++){
            fact*=i;
            nums.add(i);
        }
        //only n-1 elements added in loop so add last nth element
        nums.add(n);
        
        k--; //if question gives k = 17 then we find k = 16 because of 0 based indexing
        
        StringBuilder ans = new StringBuilder();
        
        while(true){
            
            //index of 1st place in the permutation we need
            int index = k /fact;
            ans.append(nums.get(index));
            nums.remove(index);
            
            if(nums.size() == 0){
                break;
            }
            
            k = k%fact;
            fact = fact / nums.size();
        }
        
        System.out.print(ans.toString());
    }
}
