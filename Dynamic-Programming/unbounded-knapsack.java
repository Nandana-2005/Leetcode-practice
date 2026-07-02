/*
==========================================================
                UNBOUNDED KNAPSACK
==========================================================

Problem:
Given N items, each having a weight and a value, and a
knapsack with capacity W, find the maximum value that can
be obtained.

Each item can be taken UNLIMITED number of times.

----------------------------------------------------------
Intuition

At every index, we have two choices:

1. Take the current item
   -> Stay at the same index since items are unlimited.

2. Do not take the current item
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
   Time  : O(N × W)
   Space : O(N × W) + O(N)

3. Tabulation
   Time  : O(N × W)
   Space : O(N × W)

4. Space Optimization
   Time  : O(N × W)
   Space : O(W)

==========================================================
*/

import java.util.Arrays;

public class UnboundedKnapsack {

    public static void main(String[] args) {

        int[] wt = {2, 4, 6};
        int[] val = {5, 11, 13};
        int W = 10;

        System.out.println("Recursion       : " + unboundedKnapsackRecursive(wt, val, W));
        System.out.println("Memoization     : " + unboundedKnapsackMemo(wt, val, W));
        System.out.println("Tabulation      : " + unboundedKnapsackTab(wt, val, W));
        System.out.println("Space Optimized : " + unboundedKnapsackSpace(wt, val, W));
    }

    // ==========================================================
    // 1. RECURSION
    // ==========================================================

    public static int unboundedKnapsackRecursive(int[] wt, int[] val, int W) {

        return solve(wt.length - 1, W, wt, val);
    }

    private static int solve(int index, int W, int[] wt, int[] val) {

        // Base Case
        if (index == 0)
            return (W / wt[0]) * val[0];

        int notTake = solve(index - 1, W, wt, val);

        int take = Integer.MIN_VALUE;

        if (wt[index] <= W)
            take = val[index] + solve(index, W - wt[index], wt, val);

        return Math.max(take, notTake);
    }

    // ==========================================================
    // 2. MEMOIZATION
    // ==========================================================

    public static int unboundedKnapsackMemo(int[] wt, int[] val, int W) {

        int[][] dp = new int[wt.length][W + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveMemo(wt.length - 1, W, wt, val, dp);
    }

    private static int solveMemo(int index, int W,
                                 int[] wt, int[] val,
                                 int[][] dp) {

        if (index == 0)
            return (W / wt[0]) * val[0];

        if (dp[index][W] != -1)
            return dp[index][W];

        int notTake = solveMemo(index - 1, W, wt, val, dp);

        int take = Integer.MIN_VALUE;

        if (wt[index] <= W)
            take = val[index] + solveMemo(index, W - wt[index], wt, val, dp);

        return dp[index][W] = Math.max(take, notTake);
    }

    // ==========================================================
    // 3. TABULATION
    // ==========================================================

    public static int unboundedKnapsackTab(int[] wt, int[] val, int W) {

        int n = wt.length;

        int[][] dp = new int[n][W + 1];

        // Base Case
        for (int cap = 0; cap <= W; cap++)
            dp[0][cap] = (cap / wt[0]) * val[0];

        for (int index = 1; index < n; index++) {

            for (int cap = 0; cap <= W; cap++) {

                int notTake = dp[index - 1][cap];

                int take = Integer.MIN_VALUE;

                if (wt[index] <= cap)
                    take = val[index] + dp[index][cap - wt[index]];

                dp[index][cap] = Math.max(take, notTake);
            }
        }

        return dp[n - 1][W];
    }

    // ==========================================================
    // 4. SPACE OPTIMIZATION
    // ==========================================================

    public static int unboundedKnapsackSpace(int[] wt, int[] val, int W) {

        int[] prev = new int[W + 1];

        // Base Case
        for (int cap = 0; cap <= W; cap++)
            prev[cap] = (cap / wt[0]) * val[0];

        for (int index = 1; index < wt.length; index++) {

            int[] curr = new int[W + 1];

            for (int cap = 0; cap <= W; cap++) {

                int notTake = prev[cap];

                int take = Integer.MIN_VALUE;

                if (wt[index] <= cap)
                    take = val[index] + curr[cap - wt[index]];

                curr[cap] = Math.max(take, notTake);
            }

            prev = curr;
        }

        return prev[W];
    }
}
