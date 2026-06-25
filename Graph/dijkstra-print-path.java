import java.util.*;

class Pair {
    int distance;
    int node;

    Pair(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

class Solution {

    public List<Integer> shortestPath(int n, int m, int edges[][]) {

        // Adjacency List
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++)
            adj.add(new ArrayList<>());

        // Undirected Graph
        for (int i = 0; i < m; i++) {

            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            adj.get(u).add(new Pair(wt, v));
            adj.get(v).add(new Pair(wt, u));
        }

        PriorityQueue<Pair> pq =
                new PriorityQueue<>((x, y) -> x.distance - y.distance);

        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];

        Arrays.fill(dist, (int)1e9);

        /*
            Initially every node
            is its own parent.
        */

        for (int i = 1; i <= n; i++)
            parent[i] = i;

        dist[1] = 0;

        pq.offer(new Pair(0, 1));

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();

            int dis = curr.distance;
            int node = curr.node;

            for (Pair it : adj.get(node)) {

                int adjNode = it.node;
                int edgeWeight = it.distance;

                /*
                 Found a shorter path
                */

                if (dis + edgeWeight < dist[adjNode]) {

                    dist[adjNode] = dis + edgeWeight;

                    /*
                     Store who helped us
                     reach this node.
                    */

                    parent[adjNode] = node;

                    pq.offer(new Pair(dist[adjNode], adjNode));
                }
            }
        }

        /*
         If destination cannot
         be reached.
        */

        if (dist[n] == (int)1e9)
            return Arrays.asList(-1);

        List<Integer> path = new ArrayList<>();

        int node = n;

        /*
         Keep moving to parent
         until source.
        */

        while (parent[node] != node) {

            path.add(node);

            node = parent[node];
        }

        path.add(1);

        Collections.reverse(path);

        return path;
    }
}
