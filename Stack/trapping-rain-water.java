import java.util.*;

class Solution {
    public int trap(int[] height) {

        int n = height.length;
        int water = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty()
                    && height[i] > height[stack.peek()]) {

                int bottom = stack.pop();

                if (stack.isEmpty()) {
                    break; // no left boundary
                }

                int leftBoundary = stack.peek();

                int width = i - leftBoundary - 1;

                int waterHeight =
                        Math.min(height[leftBoundary], height[i])
                        - height[bottom];

                water += width * waterHeight;
            }

            stack.push(i);
        }

        return water;
    }
}
