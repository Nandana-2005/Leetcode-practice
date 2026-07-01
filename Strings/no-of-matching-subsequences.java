/*
==========================================================
        NUMBER OF MATCHING SUBSEQUENCES (LeetCode 792)
==========================================================

Problem:
Given a string s and an array of strings words, return
the number of words that are subsequences of s.

A subsequence is formed by deleting some (or no) characters
from the original string without changing the order of the
remaining characters.

----------------------------------------------------------
Approaches

1. Brute Force (Two Pointers)
2. Binary Search + Preprocessing
3. Bucket Method (Optimal)

----------------------------------------------------------
Time Complexities

1. Brute Force
   Time  : O(N × M)
   Space : O(1)

   N = length of s
   M = total length of all words

2. Binary Search + Preprocessing
   Time  : O(N + M log N)
   Space : O(N)

3. Bucket Method
   Time  : O(N + M)
   Space : O(M)

==========================================================
*/

import java.util.*;

public class NumberOfMatchingSubsequences {

    public static void main(String[] args) {

        String s = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};

        System.out.println("Brute Force : " + numMatchingSubseqBrute(s, words));
        System.out.println("Binary Search : " + numMatchingSubseqBinary(s, words));
        System.out.println("Bucket Method : " + numMatchingSubseqBucket(s, words));
    }

    // ==========================================================
    // 1. BRUTE FORCE (Two Pointers)
    // ==========================================================

    public static int numMatchingSubseqBrute(String s, String[] words) {

        int count = 0;

        for (String word : words) {

            if (isSubsequence(s, word))
                count++;
        }

        return count;
    }

    private static boolean isSubsequence(String s, String word) {

        int i = 0;
        int j = 0;

        while (i < s.length() && j < word.length()) {

            if (s.charAt(i) == word.charAt(j))
                j++;

            i++;
        }

        return j == word.length();
    }

    // ==========================================================
    // 2. BINARY SEARCH + PREPROCESSING
    // ==========================================================

    public static int numMatchingSubseqBinary(String s, String[] words) {

        List<Integer>[] positions = new ArrayList[26];

        for (int i = 0; i < 26; i++)
            positions[i] = new ArrayList<>();

        // Store all indices of every character
        for (int i = 0; i < s.length(); i++)
            positions[s.charAt(i) - 'a'].add(i);

        int count = 0;

        for (String word : words) {

            int prevIndex = -1;
            boolean found = true;

            for (char ch : word.toCharArray()) {

                List<Integer> list = positions[ch - 'a'];

                int next = upperBound(list, prevIndex);

                if (next == -1) {
                    found = false;
                    break;
                }

                prevIndex = next;
            }

            if (found)
                count++;
        }

        return count;
    }

    // First index greater than prevIndex
    private static int upperBound(List<Integer> list, int prevIndex) {

        int left = 0;
        int right = list.size() - 1;
        int ans = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (list.get(mid) > prevIndex) {

                ans = list.get(mid);
                right = mid - 1;

            } else {

                left = mid + 1;
            }
        }

        return ans;
    }

    // ==========================================================
    // 3. BUCKET METHOD (OPTIMAL)
    // ==========================================================

    static class Node {

        String word;
        int index;

        Node(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }

    public static int numMatchingSubseqBucket(String s, String[] words) {

        Queue<Node>[] buckets = new LinkedList[26];

        for (int i = 0; i < 26; i++)
            buckets[i] = new LinkedList<>();

        // Put every word into the bucket of its first character
        for (String word : words)
            buckets[word.charAt(0) - 'a'].offer(new Node(word, 0));

        int answer = 0;

        for (char ch : s.toCharArray()) {

            Queue<Node> queue = buckets[ch - 'a'];

            int size = queue.size();

            while (size-- > 0) {

                Node current = queue.poll();

                current.index++;

                // Word completely matched
                if (current.index == current.word.length()) {

                    answer++;

                } else {

                    char nextChar = current.word.charAt(current.index);

                    buckets[nextChar - 'a'].offer(current);
                }
            }
        }

        return answer;
    }
}
