/*
==========================================================
      MINIMUM ABSOLUTE DIFFERENCE OF PARTITION
==========================================================

Problem:
Given an integer array nums, partition it into two subsets
such that the absolute difference between their sums is
minimum.

Return the minimum possible absolute difference.

----------------------------------------------------------
Intuition

Let totalSum = sum of all elements.

If one subset has sum = s1,
then the other subset has sum = totalSum - s1.

Difference =
|s1 - (totalSum - s1)|
= |totalSum - 2*s1|

Therefore, we first find all possible subset sums using
Subset Sum DP, then check every possible sum and compute

    answer = min(|totalSum - 2*s1|)

The subset sum DP's last row, when it returns true or false, gives all the possible sums that could be got out og
the array. So the valid sum can be taken as s1 for this question.

----------------------------------------------------------
Approaches

1. Recursion
2. Memoization
3. Tabulation

(Note:
Space Optimization is possible but Striver generally teaches
Tabulation for this problem.)

----------------------------------------------------------
Time Complexities

1. Recursion
   Time  : O(2^N)
   Space : O(N)

2. Memoization
   Time  : O(N × TotalSum)
   Space : O(N × TotalSum) + O(N)

3. Tabulation
   Time  : O(N × TotalSum)
   Space : O(N × TotalSum)

==========================================================
*/

import java.util.Arrays;

public class MinimumAbsoluteDifferencePartition {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 9};

        System.out.println("Recursive   : " + minimumDifferenceRecursive(nums));
        System.out.println("Memoization : " + minimumDifferenceMemo(nums));
        System.out.println("Tabulation  : " + minimumDifferenceTab(nums));
    }

    // ==========================================================
    // Utility Function
    // ==========================================================

    private static int totalSum(int[] nums) {

        int sum = 0;

        for (int num : nums)
            sum += num;

        return sum;
    }

    // ==========================================================
    // 1. RECURSION
    // ==========================================================

    public static int minimumDifferenceRecursive(int[] nums) {

        int total = totalSum(nums);
        int ans = Integer.MAX_VALUE;

        for (int s1 = 0; s1 <= total; s1++) {

            if (solve(nums.length - 1, s1, nums)) {

                ans = Math.min(ans, Math.abs(total - 2 * s1));
            }
        }

        return ans;
    }

    private static boolean solve(int index, int target, int[] nums) {

        if (target == 0)
            return true;

        if (index == 0)
            return nums[0] == target;

        boolean notTake = solve(index - 1, target, nums);

        boolean take = false;

        if (nums[index] <= target)
            take = solve(index - 1, target - nums[index], nums);

        return take || notTake;
    }

    // ==========================================================
    // 2. MEMOIZATION
    // ==========================================================

    public static int minimumDifferenceMemo(int[] nums) {

        int total = totalSum(nums);

        int[][] dp = new int[nums.length][total + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        int ans = Integer.MAX_VALUE;

        for (int s1 = 0; s1 <= total; s1++) {

            if (solveMemo(nums.length - 1, s1, nums, dp)) {

                ans = Math.min(ans, Math.abs(total - 2 * s1));
            }
        }

        return ans;
    }

    private static boolean solveMemo(int index, int target,
                                     int[] nums, int[][] dp) {

        if (target == 0)
            return true;

        if (index == 0)
            return nums[0] == target;

        if (dp[index][target] != -1)
            return dp[index][target] == 1;

        boolean notTake = solveMemo(index - 1, target, nums, dp);

        boolean take = false;

        if (nums[index] <= target)
            take = solveMemo(index - 1, target - nums[index], nums, dp);

        dp[index][target] = (take || notTake) ? 1 : 0;

        return take || notTake;
    }

    // ==========================================================
    // 3. TABULATION
    // ==========================================================

    public static int minimumDifferenceTab(int[] nums) {

        int n = nums.length;
        int total = totalSum(nums);

        boolean[][] dp = new boolean[n][total + 1];

        // Sum 0 is always possible
        for (int i = 0; i < n; i++)
            dp[i][0] = true;

        // Base Case
        if (nums[0] <= total)
            dp[0][nums[0]] = true;

        for (int i = 1; i < n; i++) {

            for (int sum = 1; sum <= total; sum++) {

                boolean notTake = dp[i - 1][sum];

                boolean take = false;

                if (nums[i] <= sum)
                    take = dp[i - 1][sum - nums[i]];

                dp[i][sum] = take || notTake;
            }
        }

        int minDiff = Integer.MAX_VALUE;

        // Only need to check till total/2 since it is absolute difference so some values
        // maybe repeated and to give same difference 
        for (int s1 = 0; s1 <= total / 2; s1++) {

            if (dp[n - 1][s1]) {

                int diff = Math.abs(total - 2 * s1);

                minDiff = Math.min(minDiff, diff);
            }
        }

        return minDiff;
    }
}
