public class NumberOfIslands {

    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};

    void dfs(char[][] grid, int r, int c) {

        int n = grid.length;
        int m = grid[0].length;

        if (r < 0 || c < 0 || r >= n || c >= m ||
            grid[r][c] == '0')
            return;

        grid[r][c] = '0';

        for (int k = 0; k < 4; k++) {
            dfs(grid, r + dr[k], c + dc[k]);
        }
    }

    public int numIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int count = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {

                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }
}
