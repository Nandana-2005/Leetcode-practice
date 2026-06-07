// You are using Java
import java.util.*;

public class Main{
    static void generateCombinations(HashMap<Character,String> map,String d,int ind,String current,List<String> result
    ){
        if(ind == d.length()){
            result.add("'"+current+"'");
            return;
        }
        
        String letters = map.get(d.charAt(ind));
        
        for(char ch : letters.toCharArray()){
            generateCombinations(map,d,ind+1,current+ch,result);
        }
        
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String d = sc.nextLine();
        List<String> result = new ArrayList<>();
        
        HashMap<Character,String> map = new HashMap<>();
        
        if(d.length() == 0){
            System.out.println("[]");
            return;
        }
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        
        generateCombinations(map,d,0,"",result);
        
        System.out.println(result);
    }
}
