public class SurroundedRegions {

    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};

    void dfs(char[][] board, int r, int c) {

        int n = board.length;
        int m = board[0].length;

        if (r < 0 || c < 0 || r >= n || c >= m ||
            board[r][c] != 'O')
            return;

        board[r][c] = '#';

        for (int k = 0; k < 4; k++) {
            dfs(board, r + dr[k], c + dc[k]);
        }
    }

    public void solve(char[][] board) {

        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][m - 1] == 'O') dfs(board, i, m - 1);
        }

        for (int j = 0; j < m; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);
            if (board[n - 1][j] == 'O') dfs(board, n - 1, j);
        }

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {

                if (board[i][j] == 'O')
                    board[i][j] = 'X';

                else if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }
}
