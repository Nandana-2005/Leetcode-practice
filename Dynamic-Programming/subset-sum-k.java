/*
==========================================================
            SUBSET SUM EQUAL TO K
==========================================================

Problem:
Given an array of positive integers and a target K,
determine whether there exists a subset whose sum equals K.

----------------------------------------------------------
Approaches:
1. Recursion
2. Memoization (Top Down DP)
3. Tabulation (Bottom Up DP)

----------------------------------------------------------
Time Complexities

1. Recursion
   Time  : O(2^N)
   Space : O(N) (Recursion Stack)

2. Memoization
   Time  : O(N * K)
   Space : O(N * K) + O(N)

3. Tabulation
   Time  : O(N * K)
   Space : O(N * K)

==========================================================
*/

import java.util.Arrays;

public class SubsetSumEqualToK {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4};
        int k = 5;

        System.out.println("Recursive  : " + subsetSumRecursive(arr, k));
        System.out.println("Memoization: " + subsetSumMemo(arr, k));
        System.out.println("Tabulation : " + subsetSumTab(arr, k));
    }

    // ==========================================================
    // 1. RECURSION
    // ==========================================================

    public static boolean subsetSumRecursive(int[] arr, int target) {
        return solve(arr.length - 1, target, arr);
    }

    private static boolean solve(int index, int target, int[] arr) {

        // Target achieved
        if (target == 0)
            return true;

        // Only first element left
        if (index == 0)
            return arr[0] == target;

        // Don't take current element
        boolean notTake = solve(index - 1, target, arr);

        // Take current element if possible
        boolean take = false;
        if (arr[index] <= target)
            take = solve(index - 1, target - arr[index], arr);

        return take || notTake;
    }

    // ==========================================================
    // 2. MEMOIZATION
    // ==========================================================

    public static boolean subsetSumMemo(int[] arr, int target) {

        int[][] dp = new int[arr.length][target + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveMemo(arr.length - 1, target, arr, dp);
    }

    private static boolean solveMemo(int index, int target,
                                     int[] arr, int[][] dp) {

        if (target == 0)
            return true;

        if (index == 0)
            return arr[0] == target;

        if (dp[index][target] != -1)
            return dp[index][target] == 1;

        boolean notTake = solveMemo(index - 1, target, arr, dp);

        boolean take = false;

        if (arr[index] <= target)
            take = solveMemo(index - 1, target - arr[index], arr, dp);

        dp[index][target] = (take || notTake) ? 1 : 0;

        return take || notTake;
    }

    // ==========================================================
    // 3. TABULATION
    // ==========================================================

    public static boolean subsetSumTab(int[] arr, int target) {

        int n = arr.length;

        boolean[][] dp = new boolean[n][target + 1];

        // Sum = 0 is always possible
        for (int i = 0; i < n; i++)
            dp[i][0] = true;

        // Base case
        if (arr[0] <= target)
            dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {

            for (int sum = 1; sum <= target; sum++) {

                boolean notTake = dp[i - 1][sum];

                boolean take = false;

                if (arr[i] <= sum)
                    take = dp[i - 1][sum - arr[i]];

                dp[i][sum] = take || notTake;
            }
        }

        return dp[n - 1][target];
    }
}
