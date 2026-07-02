/*
==========================================================
                    ROD CUTTING
==========================================================

Problem:
Given a rod of length N and an array price[],
where price[i] represents the price of a rod of
length (i + 1), determine the maximum obtainable
value by cutting up the rod and selling the pieces.

A piece of the same length can be used multiple times.

----------------------------------------------------------
Intuition

Treat every rod length as an item.

Weight  = Rod Length (index + 1)
Value   = Price[index]

Since a rod of the same length can be cut multiple
times, this is an Unbounded Knapsack problem.

At every index, we have two choices:

1. Cut the current rod length
   -> Stay at the same index.

2. Do not cut the current rod length
   -> Move to the previous index.

Answer =
max(take, notTake)

----------------------------------------------------------
Approaches

1. Recursion
2. Memoization
3. Tabulation
4. Space Optimization

----------------------------------------------------------
Time Complexities

1. Recursion
   Time  : Exponential
   Space : O(N)

2. Memoization
   Time  : O(N × N)
   Space : O(N × N) + O(N)

3. Tabulation
   Time  : O(N × N)
   Space : O(N × N)

4. Space Optimization
   Time  : O(N × N)
   Space : O(N)

==========================================================
*/

import java.util.Arrays;

public class RodCutting {

    public static void main(String[] args) {

        int[] price = {2, 5, 7, 8, 10};
        int n = 5;

        System.out.println("Recursion       : " + cutRodRecursive(price, n));
        System.out.println("Memoization     : " + cutRodMemo(price, n));
        System.out.println("Tabulation      : " + cutRodTab(price, n));
        System.out.println("Space Optimized : " + cutRodSpace(price, n));
    }

    // ==========================================================
    // 1. RECURSION
    // ==========================================================

    public static int cutRodRecursive(int[] price, int n) {

        return solve(price.length - 1, n, price);
    }

    private static int solve(int index, int length, int[] price) {

        // Base Case
        if (index == 0)
            return length * price[0];

        int notTake = solve(index - 1, length, price);

        int take = Integer.MIN_VALUE;

        int rodLength = index + 1;

        if (rodLength <= length)
            take = price[index] +
                    solve(index, length - rodLength, price);

        return Math.max(take, notTake);
    }

    // ==========================================================
    // 2. MEMOIZATION
    // ==========================================================

    public static int cutRodMemo(int[] price, int n) {

        int[][] dp = new int[price.length][n + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveMemo(price.length - 1, n, price, dp);
    }

    private static int solveMemo(int index, int length,
                                 int[] price, int[][] dp) {

        if (index == 0)
            return length * price[0];

        if (dp[index][length] != -1)
            return dp[index][length];

        int notTake = solveMemo(index - 1, length, price, dp);

        int take = Integer.MIN_VALUE;

        int rodLength = index + 1;

        if (rodLength <= length)
            take = price[index] +
                    solveMemo(index, length - rodLength, price, dp);

        return dp[index][length] = Math.max(take, notTake);
    }

    // ==========================================================
    // 3. TABULATION
    // ==========================================================

    public static int cutRodTab(int[] price, int n) {

        int[][] dp = new int[n][n + 1];

        // Base Case
        for (int length = 0; length <= n; length++)
            dp[0][length] = length * price[0];

        for (int index = 1; index < n; index++) {

            int rodLength = index + 1;

            for (int length = 0; length <= n; length++) {

                int notTake = dp[index - 1][length];

                int take = Integer.MIN_VALUE;

                if (rodLength <= length)
                    take = price[index] +
                            dp[index][length - rodLength];

                dp[index][length] = Math.max(take, notTake);
            }
        }

        return dp[n - 1][n];
    }

    // ==========================================================
    // 4. SPACE OPTIMIZATION
    // ==========================================================

    public static int cutRodSpace(int[] price, int n) {

        int[] prev = new int[n + 1];

        // Base Case
        for (int length = 0; length <= n; length++)
            prev[length] = length * price[0];

        for (int index = 1; index < n; index++) {

            int[] curr = new int[n + 1];

            int rodLength = index + 1;

            for (int length = 0; length <= n; length++) {

                int notTake = prev[length];

                int take = Integer.MIN_VALUE;

                if (rodLength <= length)
                    take = price[index] +
                            curr[length - rodLength];

                curr[length] = Math.max(take, notTake);
            }

            prev = curr;
        }

        return prev[n];
    }
}
