import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        int m = sc.nextInt();

        long[] dp = new long[m + 1];
        dp[0] = 1;

        for (int coin: coins) {
            for (int j = coin; j <= m; j++) {
                dp[j] += dp[j - coin];
            }
        }

        System.out.println(dp[m]);
    }
}
