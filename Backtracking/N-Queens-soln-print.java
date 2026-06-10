import java.util.*;

class NQueens {
    List<List<String>> solutions = new ArrayList<>();

    public void solve(int col, int n, char[][] board, Set<Integer> rows, Set<Integer> diag1, Set<Integer> diag2) {
        if (col == n) {
            List<String> current = new ArrayList<>();
            for (char[] row : board) {
                current.add(new String(row));
            }
            solutions.add(current);
            return;
        }

        for (int row = 0; row < n; row++) {
            if (rows.contains(row) || diag1.contains(col - row) || diag2.contains(col + row)) continue;

            board[row][col] = 'Q';
            rows.add(row);
            diag1.add(col - row);
            diag2.add(col + row);

            solve(col + 1, n, board, rows, diag1, diag2);

            board[row][col] = '.';
            rows.remove(row);
            diag1.remove(col - row);
            diag2.remove(col + row);
        }
    }

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');

        solve(0, n, board, new HashSet<>(), new HashSet<>(), new HashSet<>());
        return solutions;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        NQueens obj = new NQueens();

        List<List<String>> result = obj.solveNQueens(n);

        // Print the total number of solutions
        System.out.println(result.size());

        // Print each solution
        for (List<String> solution : result) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println(); // Blank line between solutions
        }
    }
}
