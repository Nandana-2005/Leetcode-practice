//this solution is about expanding from the center as it requires more like a length of palindrome to be optimised so this approach
//manacher also give more optimised TC but it is complex
// O(n^2) - TC and O(1) - SC


class Solution {

    int start = 0;
    int maxLen = 0;

    public String longestPalindrome(String s) {

        if (s == null || s.length() == 0)
            return "";

        for (int i = 0; i < s.length(); i++) {

            // Odd length palindrome
            expand(s, i, i);

            // Even length palindrome
            expand(s, i, i + 1);
        }

        return s.substring(start, start + maxLen);
    }

    private void expand(String s, int left, int right) {

        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        int length = right - left - 1;

        if (length > maxLen) {
            maxLen = length;
            start = left + 1;
        }
    }
}
