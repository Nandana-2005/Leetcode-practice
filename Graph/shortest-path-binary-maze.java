import java.util.*;

class Pair {
    int distance;
    int row;
    int col;

    Pair(int distance, int row, int col) {
        this.distance = distance;
        this.row = row;
        this.col = col;
    }
}

public class Solution {

    int shortestPath(int[][] grid, int[] source, int[] destination) {

        int n = grid.length;
        int m = grid[0].length;

        //if src and dest are the same points then distance is 0
        if (source[0] == destination[0] && source[1] == destination[1])
            return 0;

        Queue<Pair> q = new LinkedList<>();

        int[][] dist = new int[n][m];

        for (int[] row : dist)
            Arrays.fill(row, (int)1e9);

        dist[source[0]][source[1]] = 0;

        q.offer(new Pair(0, source[0], source[1]));

        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        while (!q.isEmpty()) {

            Pair it = q.poll();

            int dis = it.distance;
            int r = it.row;
            int c = it.col;

            for (int i = 0; i < 4; i++) {

                int newRow = r + delRow[i];
                int newCol = c + delCol[i];

                if (newRow >= 0 && newRow < n &&
                    newCol >= 0 && newCol < m &&
                    grid[newRow][newCol] == 1 &&
                    dis + 1 < dist[newRow][newCol]) {

                    dist[newRow][newCol] = dis + 1;

                    //checking if destination is reached, if yes, then we return the updated distance
                    if (newRow == destination[0] &&
                        newCol == destination[1])
                        return dis + 1;

                    q.offer(new Pair(dis + 1, newRow, newCol));
                }
            }
        }

        return -1;
    }
}
