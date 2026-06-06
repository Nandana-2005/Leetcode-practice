import java.util.*;

class Job {
    int start, finish, profit;

    Job(int start, int finish, int profit) {
        this.start = start;
        this.finish = finish;
        this.profit = profit;
    }
}

public class Main {
    //using binary search on the sorted jobs array by finish time 
    static int latestNonConflict(Job[] jobs, int index) {
        int low = 0;
        int high = index - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (jobs[mid].finish <= jobs[index].start) {
                if (mid + 1 < index &&
                    jobs[mid + 1].finish <= jobs[index].start)
                    low = mid + 1;
                else
                    return mid;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Job[] jobs = new Job[n];

        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int finish = sc.nextInt();
            int profit = sc.nextInt();

            jobs[i] = new Job(start, finish, profit);
        }

        /**The Lambda Expression: (a, b) -> a.finish - b.finish
This is a concise way of writing a custom Comparator. You can think of a and b as two arbitrary Job objects that Java is comparing while it runs its sorting algorithm.

The expression returns an integer based on the subtraction:

If a.finish is less than b.finish: The result is negative. This tells Java that a should come before b.

If a.finish is equal to b.finish: The result is zero. Their relative order stays the same.

If a.finish is greater than b.finish: The result is positive. This tells Java that a should come after b.**/
        Arrays.sort(jobs, (a, b) -> a.finish - b.finish);

        int[] dp = new int[n];
        dp[0] = jobs[0].profit;

        for (int i = 1; i < n; i++) {
            int includeProfit = jobs[i].profit;

            int l = latestNonConflict(jobs, i);

            if (l != -1)
                includeProfit += dp[l];

            dp[i] = Math.max(includeProfit, dp[i - 1]);
        }

        System.out.println("The optimal profit is " + dp[n - 1]);

        sc.close();
    }
}
