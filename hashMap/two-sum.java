
import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String[] arr = sc.nextLine().trim().split("\\s+");
        
        int target = sc.nextInt();
        int[] nums = new int[arr.length];
        
        for(int i = 0;i<nums.length;i++){
            nums[i] = Integer.parseInt(arr[i]);
        }
        
        HashMap<Integer,Integer> map = new HashMap<>();
        
        for(int i = 0;i<nums.length;i++){
            //complement is the value needed by an array element to sum to the target
            // it is like a complement for 90 degree
            int complement = target - nums[i];
            
            if(map.containsKey(complement)){
                System.out.println("["+map.get(complement)+","+i+"]");
                return;
            }

            //put value in map is that value is not there since later any element could need the current value as a complement
            map.put(nums[i],i);
        }
        
        System.out.println("No valid pair found.");
        
    }
}
