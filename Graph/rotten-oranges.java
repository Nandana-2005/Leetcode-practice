import java.util.*;

public class RottenOranges {

    class Pair {
        int row;
        int col;
        int time;

        Pair(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }

    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> q = new LinkedList<>();

        int fresh = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 2)
                    q.offer(new Pair(i, j, 0));

                else if (grid[i][j] == 1)
                    fresh++;
            }
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int time = 0;
        int rotten = 0;

        while (!q.isEmpty()) {

            Pair cur = q.poll();

            time = Math.max(time, cur.time);

            for (int k = 0; k < 4; k++) {

                int nr = cur.row + dr[k];
                int nc = cur.col + dc[k];

                if (nr >= 0 && nc >= 0 &&
                    nr < n && nc < m &&
                    grid[nr][nc] == 1) {

                    grid[nr][nc] = 2;
                    rotten++;

                    q.offer(new Pair(nr, nc,
                                     cur.time + 1));
                }
            }
        }

        if (rotten != fresh)
            return -1;

        return time;
    }
}
