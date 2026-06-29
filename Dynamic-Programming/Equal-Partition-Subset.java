/*
==========================================================
            PARTITION EQUAL SUBSET SUM
==========================================================

Problem:
Given an integer array nums, determine whether it can be
partitioned into two subsets such that the sum of elements
in both subsets is equal.

Observation:
If total sum is odd → Partition is impossible.

Otherwise,
Target = Total Sum / 2

The problem reduces to checking whether there exists
a subset with sum = target.

----------------------------------------------------------
Approaches:
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
   Time  : O(N × Target)
   Space : O(N × Target) + O(N)

3. Tabulation
   Time  : O(N × Target)
   Space : O(N × Target)

4. Space Optimization
   Time  : O(N × Target)
   Space : O(Target)

==========================================================
*/

import java.util.Arrays;

public class PartitionEqualSubsetSum {

    public static void main(String[] args) {

        int[] nums = {1, 5, 11, 5};

        System.out.println("Recursive         : " + canPartitionRecursive(nums));
        System.out.println("Memoization       : " + canPartitionMemo(nums));
        System.out.println("Tabulation        : " + canPartitionTab(nums));
        System.out.println("Space Optimized   : " + canPartitionSpace(nums));
    }

    // ==========================================================
    // Utility Function
    // ==========================================================

    private static int findTarget(int[] nums) {

        int sum = 0;

        for (int num : nums)
            sum += num;

        if (sum % 2 != 0)
            return -1;

        return sum / 2;
    }

    // ==========================================================
    // 1. RECURSION
    // ==========================================================

    public static boolean canPartitionRecursive(int[] nums) {

        int target = findTarget(nums);

        if (target == -1)
            return false;

        return solve(nums.length - 1, target, nums);
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

    public static boolean canPartitionMemo(int[] nums) {

        int target = findTarget(nums);

        if (target == -1)
            return false;

        int[][] dp = new int[nums.length][target + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveMemo(nums.length - 1, target, nums, dp);
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

    public static boolean canPartitionTab(int[] nums) {

        int target = findTarget(nums);

        if (target == -1)
            return false;

        int n = nums.length;

        boolean[][] dp = new boolean[n][target + 1];

        // Sum = 0 is always possible
        for (int i = 0; i < n; i++)
            dp[i][0] = true;

        // Base Case
        if (nums[0] <= target)
            dp[0][nums[0]] = true;

        for (int i = 1; i < n; i++) {

            for (int sum = 1; sum <= target; sum++) {

                boolean notTake = dp[i - 1][sum];

                boolean take = false;

                if (nums[i] <= sum)
                    take = dp[i - 1][sum - nums[i]];

                dp[i][sum] = take || notTake;
            }
        }

        return dp[n - 1][target];
    }

    // ==========================================================
    // 4. SPACE OPTIMIZATION
    // ==========================================================

    public static boolean canPartitionSpace(int[] nums) {

        int target = findTarget(nums);

        if (target == -1)
            return false;

        boolean[] dp = new boolean[target + 1];

        dp[0] = true;

        for (int num : nums) {

            // Traverse backwards to avoid using updated values
            // from the current iteration.
            for (int sum = target; sum >= num; sum--) {

                dp[sum] = dp[sum] || dp[sum - num];
            }
        }

        return dp[target];
    }
}
