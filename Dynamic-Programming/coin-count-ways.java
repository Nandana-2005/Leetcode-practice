/*
==========================================================
                COIN CHANGE II
==========================================================

Problem:
Given an array of coin denominations and a target amount,
return the number of distinct combinations that make up
the amount.

Each coin can be used UNLIMITED number of times.

Return the total number of ways.

----------------------------------------------------------
Intuition

At every index, we have two choices:

1. Take the current coin
   -> Stay at the same index since coins are unlimited.

2. Do not take the current coin
   -> Move to the previous index.

Answer =
take + notTake

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
   Time  : O(N × Amount)
   Space : O(N × Amount) + O(N)

3. Tabulation
   Time  : O(N × Amount)
   Space : O(N × Amount)

4. Space Optimization
   Time  : O(N × Amount)
   Space : O(Amount)

==========================================================
*/

import java.util.Arrays;

public class CoinChangeII {

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        int amount = 5;

        System.out.println("Recursion       : " + changeRecursive(coins, amount));
        System.out.println("Memoization     : " + changeMemo(coins, amount));
        System.out.println("Tabulation      : " + changeTab(coins, amount));
        System.out.println("Space Optimized : " + changeSpace(coins, amount));
    }

    // ==========================================================
    // 1. RECURSION
    // ==========================================================

    public static int changeRecursive(int[] coins, int amount) {

        return solve(coins.length - 1, amount, coins);
    }

    private static int solve(int index, int amount, int[] coins) {

        // Base Case
        if (index == 0) {

            if (amount % coins[0] == 0)
                return 1;

            return 0;
        }

        int notTake = solve(index - 1, amount, coins);

        int take = 0;

        if (coins[index] <= amount)
            take = solve(index, amount - coins[index], coins);

        return take + notTake;
    }

    // ==========================================================
    // 2. MEMOIZATION
    // ==========================================================

    public static int changeMemo(int[] coins, int amount) {

        int[][] dp = new int[coins.length][amount + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveMemo(coins.length - 1, amount, coins, dp);
    }

    private static int solveMemo(int index, int amount,
                                 int[] coins, int[][] dp) {

        if (index == 0) {

            if (amount % coins[0] == 0)
                return 1;

            return 0;
        }

        if (dp[index][amount] != -1)
            return dp[index][amount];

        int notTake = solveMemo(index - 1, amount, coins, dp);

        int take = 0;

        if (coins[index] <= amount)
            take = solveMemo(index, amount - coins[index], coins, dp);

        return dp[index][amount] = take + notTake;
    }

    // ==========================================================
    // 3. TABULATION
    // ==========================================================

    public static int changeTab(int[] coins, int amount) {

        int n = coins.length;

        int[][] dp = new int[n][amount + 1];

        // Base Case
        for (int target = 0; target <= amount; target++) {
            //divisible meaning increase count ryt
            if (target % coins[0] == 0)
                dp[0][target] = 1;
        }

        for (int index = 1; index < n; index++) {

            for (int target = 0; target <= amount; target++) {

                int notTake = dp[index - 1][target];

                int take = 0;

                if (coins[index] <= target)
                    take = dp[index][target - coins[index]];

                dp[index][target] = take + notTake;
            }
        }

        return dp[n - 1][amount];
    }

    // ==========================================================
    // 4. SPACE OPTIMIZATION
    // ==========================================================

    public static int changeSpace(int[] coins, int amount) {

        int[] prev = new int[amount + 1];

        // Base Case
        for (int target = 0; target <= amount; target++) {

            if (target % coins[0] == 0)
                prev[target] = 1;
        }

        for (int index = 1; index < coins.length; index++) {

            int[] curr = new int[amount + 1];

            for (int target = 0; target <= amount; target++) {

                int notTake = prev[target];

                int take = 0;

                if (coins[index] <= target)
                    take = curr[target - coins[index]];

                curr[target] = take + notTake;
            }

            prev = curr;
        }

        return prev[amount];
    }
}
