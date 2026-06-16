public class FloodFill {

    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};

    void dfs(int[][] image,
             int r,
             int c,
             int oldColor,
             int newColor) {

        int n = image.length;
        int m = image[0].length;

        if (r < 0 || c < 0 || r >= n || c >= m)
            return;

        if (image[r][c] != oldColor)
            return;

        image[r][c] = newColor;

        for (int k = 0; k < 4; k++) {
            dfs(image,
                r + dr[k],
                c + dc[k],
                oldColor,
                newColor);
        }
    }

    public int[][] floodFill(int[][] image,
                             int sr,
                             int sc,
                             int color) {

        int oldColor = image[sr][sc];

        if (oldColor == color)
            return image;

        dfs(image, sr, sc, oldColor, color);

        return image;
    }
}
