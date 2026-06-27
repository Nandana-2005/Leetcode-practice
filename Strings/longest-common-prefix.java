//string manipulation approach - vertical scanning
// tc: O(m x n) and O(1) SC
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String[] strs = new String[n];

        for (int i = 0; i < n; i++) {
            strs[i] = sc.next();
        }

        String first = strs[0];

        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);

            for (int j = 1; j < n; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    System.out.println("\"" + first.substring(0, i) + "\"");
                    return;
                }
            }
        }

        System.out.println("\"" + first + "\"");
    }
}


//sorting and comparing first and last 2 strings approach
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        String[] strs = new String[n];

        for(int i=0;i<n;i++)
            strs[i]=sc.next();

        Arrays.sort(strs);

        String first = strs[0];
        String last = strs[n-1];

        int i = 0;

        while(i < first.length() &&
              i < last.length() &&
              first.charAt(i)==last.charAt(i))
            i++;

        System.out.println("\""+first.substring(0,i)+"\"");
    }
}
//horizontal scanning

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        String[] strs = new String[n];

        for(int i=0;i<n;i++)
            strs[i]=sc.next();

      //assuming full string to be the prefix 
        String prefix = strs[0];

        for(int i=1;i<n;i++){
            //trying to find the prefix by removing one character at a time from the end and find prefixing between 2 strings at a time.
            while(!strs[i].startsWith(prefix)){

                prefix = prefix.substring(0,prefix.length()-1);

                //while removing if string becomes empty then nothing is common returning empty string
                if(prefix.length()==0){
                    System.out.println("\"\"");  // /" is a escape sequence to print " "
                    return;
                }
            }
        }

        System.out.println("\""+prefix+"\"");
    }
}
