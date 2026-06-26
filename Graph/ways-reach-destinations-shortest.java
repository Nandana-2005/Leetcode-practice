import java.util.*;

class Pair {
    long distance;
    int node;

    Pair(long distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

class Solution {

    public int countPaths(int n, int[][] roads) {

        int mod = (int)1e9 + 7;

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        // Build graph
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int wt = road[2];
            //undirected
            adj.get(u).add(new Pair(wt, v));
            adj.get(v).add(new Pair(wt, u));
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        int[] ways = new int[n];

        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a.distance, b.distance));

        dist[0] = 0;
        ways[0] = 1;

        pq.offer(new Pair(0, 0));

        while (!pq.isEmpty()) {

            Pair it = pq.poll();

            long dis = it.distance;
            int node = it.node;

            if (dis > dist[node])
                continue;

            for (Pair edge : adj.get(node)) {

                int adjNode = edge.node;
                long edgeWeight = edge.distance;

                // Found a shorter path
                if (dis + edgeWeight < dist[adjNode]) {
                    //if shorter distance found then ways to previous node only would contribute since only one edge between adjNode and curr node not contribtuting to the ways
                    dist[adjNode] = dis + edgeWeight;
                    ways[adjNode] = ways[node];

                    pq.offer(new Pair(dist[adjNode], adjNode));
                }

                // Found another shortest path
                else if (dis + edgeWeight == dist[adjNode]) {
                    // when same min distance found then we add no of ways of previous nodes to inlcude all the combinations
                    ways[adjNode] =
                            (ways[adjNode] + ways[node]) % mod;
                }
            }
        }

        return ways[n - 1];
    }
}
