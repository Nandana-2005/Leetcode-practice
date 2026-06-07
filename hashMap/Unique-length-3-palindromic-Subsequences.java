import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        String s = sc.next();
        
        int ans = 0;
        
        for(char ch = 'a';ch<='z';ch++){
            int first = s.indexOf(ch);
            int last = s.lastIndexOf(ch);
            
            if(first != -1 && last - first > 1){
                HashSet<Character> set = new HashSet<>();
                
                for(int i = first+1;i<last;i++){
                    set.add(s.charAt(i));
                }
                
                ans += set.size();
            }
        }
        
        System.out.println(ans);
        
    }
}
