import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] value = new int[n];
        int[] weight = new int[n];

        for (int i = 0; i < n; i++) {
            value[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }

        int W = sc.nextInt();

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {

                if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            value[i - 1] + dp[i - 1][w - weight[i - 1]]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println(dp[n][W]);

        sc.close();
    }
}
