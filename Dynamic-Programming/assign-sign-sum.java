/*
==========================================================
           TARGET SUM (STRIVER APPROACH)
==========================================================

Problem:
Given an array nums and an integer target,
assign '+' or '-' before every element such that
the resulting expression equals target.

Return the total number of possible expressions.

----------------------------------------------------------
Intuition

Let:

S1 = Sum of first subset
S2 = Sum of second subset

Then,

S1 - S2 = target
S1 + S2 = totalSum

Subtracting,

2 * S2 = totalSum - target

S2 = (totalSum - target) / 2

Thus the problem reduces to:

Count the number of subsets having sum =
(totalSum - target) / 2

----------------------------------------------------------
Approaches

1. Recursion
2. Memoization
3. Tabulation
4. Space Optimization

----------------------------------------------------------
Time Complexities

1. Recursion
   Time  : O(2^N)
   Space : O(N)

2. Memoization
   Time  : O(N × K)
   Space : O(N × K) + O(N)

3. Tabulation
   Time  : O(N × K)
   Space : O(N × K)

4. Space Optimization
   Time  : O(N × K)
   Space : O(K)

where
K = (totalSum - target) / 2

==========================================================
*/

import java.util.Arrays;

public class TargetSum {

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;

        System.out.println("Recursion       : " + targetSumRecursive(nums, target));
        System.out.println("Memoization     : " + targetSumMemo(nums, target));
        System.out.println("Tabulation      : " + targetSumTab(nums, target));
        System.out.println("Space Optimized : " + targetSumSpace(nums, target));
    }

    // ==========================================================
    // Utility Function
    // ==========================================================

    private static int requiredSum(int[] nums, int target) {

        int totalSum = 0;

        for (int num : nums)
            totalSum += num;

        if (totalSum - target < 0)
            return -1;

        if ((totalSum - target) % 2 != 0)
            return -1;

        return (totalSum - target) / 2;
    }

    // ==========================================================
    // 1. RECURSION
    // ==========================================================

    public static int targetSumRecursive(int[] nums, int target) {

        int sum = requiredSum(nums, target);

        if (sum == -1)
            return 0;

        return solve(nums.length - 1, sum, nums);
    }

    private static int solve(int index, int target, int[] nums) {

        if (index == 0) {

            if (target == 0 && nums[0] == 0)
                return 2;

            if (target == 0 || target == nums[0])
                return 1;

            return 0;
        }

        int notTake = solve(index - 1, target, nums);

        int take = 0;

        if (nums[index] <= target)
            take = solve(index - 1, target - nums[index], nums);

        return take + notTake;
    }

    // ==========================================================
    // 2. MEMOIZATION
    // ==========================================================

    public static int targetSumMemo(int[] nums, int target) {

        int sum = requiredSum(nums, target);

        if (sum == -1)
            return 0;

        int[][] dp = new int[nums.length][sum + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveMemo(nums.length - 1, sum, nums, dp);
    }

    private static int solveMemo(int index, int target,
                                 int[] nums, int[][] dp) {

        if (index == 0) {

            if (target == 0 && nums[0] == 0)
                return 2;

            if (target == 0 || target == nums[0])
                return 1;

            return 0;
        }

        if (dp[index][target] != -1)
            return dp[index][target];

        int notTake = solveMemo(index - 1, target, nums, dp);

        int take = 0;

        if (nums[index] <= target)
            take = solveMemo(index - 1, target - nums[index], nums, dp);

        return dp[index][target] = take + notTake;
    }

    // ==========================================================
    // 3. TABULATION
    // ==========================================================

    public static int targetSumTab(int[] nums, int target) {

        int sum = requiredSum(nums, target);

        if (sum == -1)
            return 0;

        int n = nums.length;

        int[][] dp = new int[n][sum + 1];

        // Base Case
        if (nums[0] == 0)
            dp[0][0] = 2;
        else
            dp[0][0] = 1;

        if (nums[0] != 0 && nums[0] <= sum)
            dp[0][nums[0]] = 1;

        for (int index = 1; index < n; index++) {

            for (int targetSum = 0; targetSum <= sum; targetSum++) {

                int notTake = dp[index - 1][targetSum];

                int take = 0;

                if (nums[index] <= targetSum)
                    take = dp[index - 1][targetSum - nums[index]];

                dp[index][targetSum] = take + notTake;
            }
        }

        return dp[n - 1][sum];
    }

    // ==========================================================
    // 4. SPACE OPTIMIZATION
    // ==========================================================

    public static int targetSumSpace(int[] nums, int target) {

        int sum = requiredSum(nums, target);

        if (sum == -1)
            return 0;

        int[] prev = new int[sum + 1];

        if (nums[0] == 0)
            prev[0] = 2;
        else
            prev[0] = 1;

        if (nums[0] != 0 && nums[0] <= sum)
            prev[nums[0]] = 1;

        for (int index = 1; index < nums.length; index++) {

            int[] curr = new int[sum + 1];

            for (int targetSum = 0; targetSum <= sum; targetSum++) {

                int notTake = prev[targetSum];

                int take = 0;

                if (nums[index] <= targetSum)
                    take = prev[targetSum - nums[index]];

                curr[targetSum] = take + notTake;
            }

            prev = curr;
        }

        return prev[sum];
    }
}
