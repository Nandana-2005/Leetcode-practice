import java.util.*;

class Pair {
    int node;
    int distance;

    Pair(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

class Solution {

    public int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {

        // Min Heap based on distance
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance);

        int[] dist = new int[V];

        Arrays.fill(dist, (int)1e9);

        // Distance from source to itself is 0
        dist[S] = 0;

        pq.offer(new Pair(0, S));

        while (!pq.isEmpty()) {

            Pair curr = pq.poll();

            int dis = curr.distance;
            int node = curr.node;

            // Traverse all neighbours
            for (ArrayList<Integer> it : adj.get(node)) {

                int adjNode = it.get(0);
                int edgeWeight = it.get(1);

                /*
                 If going through current node gives
                 a shorter path, update it.
                */

                if (dis + edgeWeight < dist[adjNode]) {

                    dist[adjNode] = dis + edgeWeight;

                    // Push updated distance
                    pq.offer(new Pair(dist[adjNode], adjNode));
                }
            }
        }

        return dist;
    }
}
