import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {

        int n = heights.length;

        int[] pse = new int[n];
        int[] nse = new int[n];

        Stack<Integer> st = new Stack<>();

        // Previous Smaller
        for(int i = 0; i < n; i++) {

            while(!st.isEmpty() &&
                  heights[st.peek()] >= heights[i]) {
                st.pop();
            }

            pse[i] = st.isEmpty() ? -1 : st.peek();

            st.push(i);
        }

        st.clear();

        // Next Smaller
        for(int i = n - 1; i >= 0; i--) {

            while(!st.isEmpty() &&
                  heights[st.peek()] >= heights[i]) {
                st.pop();
            }

           //n and not -1 since in the formula -1 would give a negative answer, and the boundary obviously ends at the last index
            nse[i] = st.isEmpty() ? n : st.peek();

            st.push(i);
        }

        int maxArea = 0;

        for(int i = 0; i < n; i++) {

            int width = nse[i] - pse[i] - 1;

            maxArea = Math.max(
                maxArea,
                heights[i] * width
            );
        }

        return maxArea;
    }
}
