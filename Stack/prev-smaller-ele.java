import java.util.*;

class Solution {
    public int[] previousSmaller(int[] arr) {

      Stack<Integer> st = new Stack<>();
      int[] ans = new int[arr.length];

      for(int i = 0;i<arr.length;i++){
        while(!st.isEmpty() && st.peek() >= arr[i]){
          st.pop();
        }

        ans[i] = st.isEmpty() ? -1: st.peek();
        st.push(arr[i]);
      }

      return ans;
    }
}
