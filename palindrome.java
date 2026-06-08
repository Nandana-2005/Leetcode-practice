import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int x = sc.nextInt();
        
        if(x>0){
            int temp = x,rev = 0;
            
            while(temp!=0){
                int digit = temp%10;
                temp = temp/10;
                rev = rev*10+digit;
            }
            if(x == rev){
                System.out.println("true");
                return;
            }
        }
        else{
            System.out.println("false");
            return;
        }
    } 
}
