// You are using Java
import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        
        Stack<String> st = new Stack<>();
        
        String[] parts = s.split("/");
        
        for(String part : parts){
            if(part.equals("") || part.equals(".")){
                continue;
            }
            if(part.equals("..")){
                if(!st.isEmpty()){
                    st.pop();
                }
            }
            else{
                st.push(part);
            }
        }
        
        if(st.isEmpty()){
            System.out.println("/");
            return;
        }
        
        StringBuilder ans = new StringBuilder();
        
        for(String dir : st){
            ans.append("/").append(dir);
        }
        
        System.out.println(ans.toString());
        
    }
}
