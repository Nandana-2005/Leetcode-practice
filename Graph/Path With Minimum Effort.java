import java.util.*;

class Tuple {
    int effort;
    int row;
    int col;

    Tuple(int effort, int row, int col) {
        this.effort = effort;
        this.row = row;
        this.col = col;
    }
}

class Solution {

    public int minimumEffortPath(int[][] heights) {

        int n = heights.length;
        int m = heights[0].length;

        PriorityQueue<Tuple> pq =
                new PriorityQueue<>((a, b) -> a.effort - b.effort);

        int[][] dist = new int[n][m];

        for (int[] row : dist)
            Arrays.fill(row, (int)1e9);

        dist[0][0] = 0;
        pq.offer(new Tuple(0, 0, 0));

        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        while (!pq.isEmpty()) {

            Tuple it = pq.poll();

            int effort = it.effort;
            int row = it.row;
            int col = it.col;

            // Destination reached
            if (row == n - 1 && col == m - 1)
                return effort;

            for (int i = 0; i < 4; i++) {

                int newRow = row + delRow[i];
                int newCol = col + delCol[i];

                if (newRow >= 0 && newRow < n &&
                    newCol >= 0 && newCol < m) {

                    // Effort required to move to neighbour
                    int newEffort = Math.max(
                            effort,
                            Math.abs(heights[row][col] - heights[newRow][newCol])
                    );

                    if (newEffort < dist[newRow][newCol]) {

                        dist[newRow][newCol] = newEffort;
                        pq.offer(new Tuple(newEffort, newRow, newCol));
                    }
                }
            }
        }

        return 0;
    }
}
