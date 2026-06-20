import java.util.*;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        //map is used to (num -> next greater number)
        
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        //Iterate from last so that we have right elements to find greatest from while maintaining in the stack
        for (int i = nums2.length - 1; i >= 0; i--) {

            //remove from top of stack till top of stack has greater element than the array
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }

            int nextGreater = stack.isEmpty() ? -1 : stack.peek();
            map.put(nums2[i], nextGreater);

            //always push current element
            stack.push(nums2[i]);
        }

        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
