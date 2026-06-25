//memoisation


import java.util.*;

class Solution {

    static int solve(int i, int j, int[][] matrix, int[][] dp) {

        int n = matrix.length;
        int m = matrix[0].length;

        // Out of bounds
        if (j < 0 || j >= m)
            return (int)-1e9;

        // First row
        if (i == 0)
            return matrix[0][j];

        if (dp[i][j] != -1)
            return dp[i][j];

        int up = matrix[i][j] + solve(i - 1, j, matrix, dp);
        int leftDiagonal = matrix[i][j] + solve(i - 1, j - 1, matrix, dp);
        int rightDiagonal = matrix[i][j] + solve(i - 1, j + 1, matrix, dp);

        return dp[i][j] = Math.max(up,
                Math.max(leftDiagonal, rightDiagonal));
    }

    public int minFallingPathSum(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        int maxi = Integer.MIN_VALUE;

        for (int j = 0; j < m; j++) {
            maxi = Math.max(maxi, solve(n - 1, j, matrix, dp));
        }

        return maxi;
    }
}



//Tabulation


class Solution {

    public int minFallingPathSum(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];

        // First row
        for (int j = 0; j < m; j++)
            dp[0][j] = matrix[0][j];

        for (int i = 1; i < n; i++) {

            for (int j = 0; j < m; j++) {

                int up = matrix[i][j] + dp[i - 1][j];

                int leftDiagonal = matrix[i][j];
                if (j > 0)
                    leftDiagonal += dp[i - 1][j - 1];
                else
                    leftDiagonal += (int)-1e9;

                int rightDiagonal = matrix[i][j];
                if (j < m - 1)
                    rightDiagonal += dp[i - 1][j + 1];
                else
                    rightDiagonal += (int)-1e9;

                dp[i][j] = Math.max(up,
                        Math.max(leftDiagonal, rightDiagonal));
            }
        }

        int maxi = Integer.MIN_VALUE;

        for (int j = 0; j < m; j++)
            maxi = Math.max(maxi, dp[n - 1][j]);

        return maxi;
    }
}

// Time Complexity
// Memoization: O(n × m)
// Tabulation: O(n × m)
// Space Optimized: O(n × m)
// Space Complexity
// Memoization: O(n × m) + O(n) (DP table + recursion stack)
// Tabulation: O(n × m)
// Space Optimized: O(m)
