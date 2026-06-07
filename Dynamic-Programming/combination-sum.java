import java.util.*;

public class Main{
    
    static void backtrack(int i,int[] arr,int target,int sum,List<Integer> ds){
        if(sum == target){
            System.out.println(ds);
            return;
        }
        if(sum>target || i == arr.length){
            return;
        }
        //taking element
        ds.add(arr[i]);
        backtrack(i,arr,target,sum+arr[i],ds);
        
        //not taking element
        ds.remove(ds.size()-1);
        backtrack(i+1,arr,target,sum,ds);
        
    }
    
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String[] nums = sc.nextLine().trim().split("\\s+");
        
        int[] candidates = new int[nums.length];
        
        for(int i = 0;i<nums.length;i++){
            candidates[i] = Integer.parseInt(nums[i]);
        }
        
        int target = sc.nextInt();
        
        backtrack(0,candidates,target,0,new ArrayList<>());
    }
}
