/*
==========================================================
                PARTITION LABELS
==========================================================

Problem:
Given a string s, partition it into as many parts as
possible so that each letter appears in at most one part.

Return a list containing the size of each partition.

----------------------------------------------------------
Intuition

For every character, determine its LAST occurrence in
the string.

Traverse the string while maintaining the farthest last
occurrence (end) of all characters seen so far.

Whenever the current index reaches this farthest point,
we have found a valid partition.

----------------------------------------------------------
Approaches

1. Brute Force
2. Greedy (Optimal)

----------------------------------------------------------
Time Complexities

1. Brute Force
   Time  : O(N²)
   Space : O(1)

2. Greedy
   Time  : O(N)
   Space : O(1)

==========================================================
*/

import java.util.*;

public class PartitionLabels {

    public static void main(String[] args) {

        String s = "ababcbacadefegdehijhklij";

        System.out.println("Brute Force : " + partitionLabelsBrute(s));
        System.out.println("Greedy      : " + partitionLabelsGreedy(s));
    }

    // ==========================================================
    // 1. BRUTE FORCE
    // ==========================================================

    public static List<Integer> partitionLabelsBrute(String s) {

        List<Integer> answer = new ArrayList<>();

        int start = 0;

        while (start < s.length()) {

            int end = lastOccurrence(s, s.charAt(start));

            int i = start;

            while (i < end) {

                end = Math.max(end,
                        lastOccurrence(s, s.charAt(i)));

                i++;
            }

            answer.add(end - start + 1);

            start = end + 1;
        }

        return answer;
    }

    private static int lastOccurrence(String s, char ch) {

        for (int i = s.length() - 1; i >= 0; i--) {

            if (s.charAt(i) == ch)
                return i;
        }

        return -1;
    }

    // ==========================================================
    // 2. GREEDY (OPTIMAL)
    // ==========================================================

    public static List<Integer> partitionLabelsGreedy(String s) {

        List<Integer> answer = new ArrayList<>();

        int[] last = new int[26];

        // Store last occurrence of every character
        for (int i = 0; i < s.length(); i++)
            last[s.charAt(i) - 'a'] = i;

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {

            end = Math.max(end, last[s.charAt(i) - 'a']);

            // Current partition ends here when the last index 'end' becomes equal to i after iteration through partition 
            if (i == end) {
                 //adding the length of the partition to answer list
                answer.add(end - start + 1);

                start = i + 1;
            }
        }

        return answer;
    }
}
