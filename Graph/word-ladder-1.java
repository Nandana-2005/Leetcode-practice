import java.util.*;
class Pair{
    int first;
    int second;
    Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}
public class practice {
    public int wordLadderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;
        Queue<Pair> q = new LinkedList<>();
        // the second parameter in the pair is the level of the word in the ladder
        q.add(new Pair(beginWord, 1));

        while(!q.isEmpty()){
            Pair p = q.poll();
            String word = p.first;
            int level = p.second;
            //when finally after all the transformations we reach the endWord, we return the level of the word in the ladder
            if(word.equals(endWord)) return level;
            char[] arr = word.toCharArray();
            for(int i=0; i<arr.length; i++){
                char originalChar = arr[i];
                for(char c='a'; c<='z'; c++){
                    arr[i] = c;
                    //the newword is created by changing one character at a time and if it is present in the set, we remove it from the set and add it to the queue with level+1
                    //newowrd is got my converting the character array to string
                    String newWord = new String(arr);
                    if(set.contains(newWord)){
                        set.remove(newWord);
                        q.add(new Pair(newWord, level+1));
                    }
                }
                arr[i] = originalChar;
            }
        }
        return 0;
    }
}
