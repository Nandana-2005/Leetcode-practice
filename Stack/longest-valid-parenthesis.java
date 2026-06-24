// You are using Java
import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String s = sc.nextLine();
        
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        
        int maxLen = 0;
        for(int i = 0;i<s.length();i++){
          //if open just push index of ( in stack
            if(s.charAt(i) == '('){
                st.push(i);
            }
          //if ) the pop and check is stack is empty 
            else{
                st.pop();
                
                if(st.isEmpty()){
                    st.push(i);
                }else{
                    // i - st.peek() will give the difference of the current ) and the last invalid index to give the length of the encountered valid parenthesis
                    maxLen = Math.max(maxLen,i-st.peek());
                }
            }
        }
        
        System.out.println(maxLen);
    }
}
