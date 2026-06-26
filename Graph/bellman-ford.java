import java.util.*;

class Solution {

    static int[] bellmanFord(int V, ArrayList<ArrayList<Integer>> edges, int S) {

        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e8);

        dist[S] = 0;

        // Relax all edges V-1 times
        for (int i = 0; i < V - 1; i++) {

            for (ArrayList<Integer> edge : edges) {

                int u = edge.get(0);
                int v = edge.get(1);
                int wt = edge.get(2);

                if (dist[u] != (int)1e8 &&
                    dist[u] + wt < dist[v]) {

                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Why does the Vth iteration detect a negative cycle?

        // If after V - 1 iterations you can still reduce a distance, the only explanation is that you're repeatedly traversing a negative-weight cycle, 
        // making the path cheaper each time.
        // Check for negative weight cycle by trying relaxation beyond the v - 1 relaxation 
        for (ArrayList<Integer> edge : edges) {

            int u = edge.get(0);
            int v = edge.get(1);
            int wt = edge.get(2);

            if (dist[u] != (int)1e8 &&
                dist[u] + wt < dist[v]) {

                return new int[]{-1};
            }
        }

        return dist;
    }
}



