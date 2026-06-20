import java.util.*;

class Solution {
    public int[] nextGreaterElements(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];

        Stack<Integer> stack = new Stack<>();

        //Traverse 2*n - 1 down to 0 - assuming hypothetical array that is double the size of original
        for (int i = 2 * n - 1; i >= 0; i--) {

            //Use i % n to access elements.
            while (!stack.isEmpty() &&
                   stack.peek() <= nums[i % n]) {
                stack.pop();
            }

            //Only fill answers during the first pass (i < n) because the rest of them are hypothetical
            if (i < n) {
                ans[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            //always add to the array
            stack.push(nums[i % n]);
        }

        return ans;
    }
}
