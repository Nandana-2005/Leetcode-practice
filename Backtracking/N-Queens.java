import java.util.*;

class NQueens {
    int count = 0;

    public void solve(int col, int n, Set<Integer> rows, Set<Integer> diag1, Set<Integer> diag2) {
        if (col == n) {
            count++;
            return;
        }

        for (int row = 0; row < n; row++) {
            if (rows.contains(row) || diag1.contains(col - row) || diag2.contains(col + row)) continue;

            rows.add(row);
            diag1.add(col - row);
            diag2.add(col + row);

            solve(col + 1, n, rows, diag1, diag2);

            rows.remove(row);
            diag1.remove(col - row);
            diag2.remove(col + row);
        }
    }

    public int totalNQueens(int n) {
        solve(0, n, new HashSet<>(), new HashSet<>(), new HashSet<>());
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        NQueens obj = new NQueens();
        System.out.print(obj.totalNQueens(n));
    }
}
