/*
==========================================================
                COIN CHANGE (MINIMUM COINS)
==========================================================

Problem:
Given an array of coin denominations and a target amount,
find the minimum number of coins required to make the amount.

Each coin can be used UNLIMITED number of times.

Return -1 if it is not possible to make the amount.

----------------------------------------------------------
Intuition

At every index, we have two choices:

1. Take the current coin
   -> Stay at the same index because coins are unlimited.

2. Do not take the current coin
   -> Move to the previous index.

Answer =
min(take, notTake)

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

public class CoinChangeMinimumCoins {

    static final int INF = (int)1e9;

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println("Recursion       : " + coinChangeRecursive(coins, amount));
        System.out.println("Memoization     : " + coinChangeMemo(coins, amount));
        System.out.println("Tabulation      : " + coinChangeTab(coins, amount));
        System.out.println("Space Optimized : " + coinChangeSpace(coins, amount));
    }

    // ==========================================================
    // 1. RECURSION
    // ==========================================================

    public static int coinChangeRecursive(int[] coins, int amount) {

        int ans = solve(coins.length - 1, amount, coins);

        return (ans >= INF) ? -1 : ans;
    }

    private static int solve(int index, int amount, int[] coins) {

        if (index == 0) {

            if (amount % coins[0] == 0)
                return amount / coins[0];

            return INF;
        }

        int notTake = solve(index - 1, amount, coins);

        int take = INF;

        if (coins[index] <= amount)
            take = 1 + solve(index, amount - coins[index], coins);

        return Math.min(take, notTake);
    }

    // ==========================================================
    // 2. MEMOIZATION
    // ==========================================================

    public static int coinChangeMemo(int[] coins, int amount) {

        int[][] dp = new int[coins.length][amount + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        int ans = solveMemo(coins.length - 1, amount, coins, dp);

        return (ans >= INF) ? -1 : ans;
    }

    private static int solveMemo(int index, int amount,
                                 int[] coins, int[][] dp) {

        if (index == 0) {

            if (amount % coins[0] == 0)
                return amount / coins[0];

            return INF;
        }

        if (dp[index][amount] != -1)
            return dp[index][amount];

        int notTake = solveMemo(index - 1, amount, coins, dp);

        int take = INF;

        if (coins[index] <= amount)
            take = 1 + solveMemo(index, amount - coins[index], coins, dp);

        return dp[index][amount] = Math.min(take, notTake);
    }

    // ==========================================================
    // 3. TABULATION
    // ==========================================================

    public static int coinChangeTab(int[] coins, int amount) {

        int n = coins.length;

        int[][] dp = new int[n][amount + 1];

        // Base Case
        for (int target = 0; target <= amount; target++) {

            if (target % coins[0] == 0)
                dp[0][target] = target / coins[0];
            else
                dp[0][target] = INF;
        }

        for (int index = 1; index < n; index++) {

            for (int target = 0; target <= amount; target++) {

                int notTake = dp[index - 1][target];

                int take = INF;

                if (coins[index] <= target)
                    take = 1 + dp[index][target - coins[index]];

                dp[index][target] = Math.min(take, notTake);
            }
        }

        return (dp[n - 1][amount] >= INF) ? -1 : dp[n - 1][amount];
    }

    // ==========================================================
    // 4. SPACE OPTIMIZATION
    // ==========================================================

    public static int coinChangeSpace(int[] coins, int amount) {

        int[] prev = new int[amount + 1];
        //usual base case
        for (int target = 0; target <= amount; target++) {

            if (target % coins[0] == 0)
                prev[target] = target / coins[0];
            else
                prev[target] = INF;
        }
        //all the dp[i-1] will become prev and dp[i] will be curr
        for (int index = 1; index < coins.length; index++) {

            int[] curr = new int[amount + 1];

            for (int target = 0; target <= amount; target++) {

                int notTake = 0 + prev[target];

                int take = INF;

                if (coins[index] <= target)
                    take = 1 + curr[target - coins[index]];

                curr[target] = Math.min(take, notTake);
            }

            prev = curr;
        }

        return (prev[amount] >= INF) ? -1 : prev[amount];
    }
}
