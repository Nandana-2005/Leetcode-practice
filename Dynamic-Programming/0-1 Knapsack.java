/*
==========================================================
                    0/1 KNAPSACK
==========================================================

Problem:
Given N items, each having a weight and a value, and a
knapsack with capacity W, find the maximum value that can
be obtained.

Each item can either be:
1. Taken once
2. Not taken

An item cannot be taken multiple times.

----------------------------------------------------------
Intuition

At every index we have two choices:

1. Take the current item (if weight allows)
2. Do not take the current item

Answer =
max(take, notTake)

----------------------------------------------------------
Approaches

1. Recursion
2. Memoization
3. Tabulation
4. Space Optimization (2 Arrays)
5. Space Optimization (1 Array)

----------------------------------------------------------
Time Complexities

1. Recursion
   Time  : O(2^N)
   Space : O(N)

2. Memoization
   Time  : O(N × W)
   Space : O(N × W) + O(N)

3. Tabulation
   Time  : O(N × W)
   Space : O(N × W)

4. Space Optimization (2 Arrays)
   Time  : O(N × W)
   Space : O(W)

5. Space Optimization (1 Array)
   Time  : O(N × W)
   Space : O(W)

==========================================================
*/

import java.util.Arrays;

public class ZeroOneKnapsack {

    public static void main(String[] args) {

        int[] wt = {1, 2, 4, 5};
        int[] val = {5, 4, 8, 6};
        int W = 5;

        System.out.println("Recursion       : " + knapsackRecursive(wt, val, W));
        System.out.println("Memoization     : " + knapsackMemo(wt, val, W));
        System.out.println("Tabulation      : " + knapsackTab(wt, val, W));
        System.out.println("Space Opt (2)   : " + knapsackSpaceTwo(wt, val, W));
        System.out.println("Space Opt (1)   : " + knapsackSpaceOne(wt, val, W));
    }

    // ==========================================================
    // 1. RECURSION
    // ==========================================================

    public static int knapsackRecursive(int[] wt, int[] val, int W) {

        return solve(wt.length - 1, W, wt, val);
    }

    private static int solve(int index, int W, int[] wt, int[] val) {

        if (index == 0) {

            if (wt[0] <= W)
                return val[0];

            return 0;
        }

        int notTake = solve(index - 1, W, wt, val);

        int take = Integer.MIN_VALUE;

        if (wt[index] <= W)
            take = val[index] + solve(index - 1, W - wt[index], wt, val);

        return Math.max(take, notTake);
    }

    // ==========================================================
    // 2. MEMOIZATION
    // ==========================================================

    public static int knapsackMemo(int[] wt, int[] val, int W) {

        int[][] dp = new int[wt.length][W + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveMemo(wt.length - 1, W, wt, val, dp);
    }

    private static int solveMemo(int index, int W,
                                 int[] wt, int[] val,
                                 int[][] dp) {

        if (index == 0) {

            if (wt[0] <= W)
                return val[0];

            return 0;
        }

        if (dp[index][W] != -1)
            return dp[index][W];

        int notTake = solveMemo(index - 1, W, wt, val, dp);

        int take = Integer.MIN_VALUE;

        if (wt[index] <= W)
            take = val[index] + solveMemo(index - 1,
                    W - wt[index], wt, val, dp);

        return dp[index][W] = Math.max(take, notTake);
    }

    // ==========================================================
    // 3. TABULATION
    // ==========================================================

    public static int knapsackTab(int[] wt, int[] val, int W) {

        int n = wt.length;

        int[][] dp = new int[n][W + 1];

        // Base Case
      // It starts from the first capacity where the item fits (wt[0]) and fills all remaining capacities with val[0].
        for (int cap = wt[0]; cap <= W; cap++)
            dp[0][cap] = val[0];

        for (int index = 1; index < n; index++) {

            for (int cap = 0; cap <= W; cap++) {

                int notTake = dp[index - 1][cap];

                int take = Integer.MIN_VALUE;

                if (wt[index] <= cap)
                    take = val[index] +
                            dp[index - 1][cap - wt[index]];

                dp[index][cap] = Math.max(take, notTake);
            }
        }

        return dp[n - 1][W];
    }

    // ==========================================================
    // 4. SPACE OPTIMIZATION (TWO ARRAYS)
    // ==========================================================

    public static int knapsackSpaceTwo(int[] wt, int[] val, int W) {

        int n = wt.length;

        int[] prev = new int[W + 1];

        for (int cap = wt[0]; cap <= W; cap++)
            prev[cap] = val[0];

        for (int index = 1; index < n; index++) {

            int[] curr = new int[W + 1];

            for (int cap = 0; cap <= W; cap++) {

                int notTake = prev[cap];

                int take = Integer.MIN_VALUE;

                if (wt[index] <= cap)
                    take = val[index] +
                            prev[cap - wt[index]];

                curr[cap] = Math.max(take, notTake);
            }

            prev = curr;
        }

        return prev[W];
    }

    // ==========================================================
    // 5. SPACE OPTIMIZATION (ONE ARRAY)
    // ==========================================================

    public static int knapsackSpaceOne(int[] wt, int[] val, int W) {

        int[] dp = new int[W + 1];

        for (int cap = wt[0]; cap <= W; cap++)
            dp[cap] = val[0];

        for (int index = 1; index < wt.length; index++) {

            // Traverse backwards to avoid reusing the same item
            // multiple times.
            for (int cap = W; cap >= 0; cap--) {

                int notTake = dp[cap];

                int take = Integer.MIN_VALUE;

                if (wt[index] <= cap)
                    take = val[index] +
                            dp[cap - wt[index]];

                dp[cap] = Math.max(take, notTake);
            }
        }

        return dp[W];
    }
}
