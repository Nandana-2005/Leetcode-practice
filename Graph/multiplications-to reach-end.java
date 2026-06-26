import java.util.*;

class Pair {
    int node;
    int steps;

    Pair(int node, int steps) {
        this.node = node;
        this.steps = steps;
    }
}

class Solution {

    int minimumMultiplications(int[] arr, int start, int end) {

        int mod = 100000;

        Queue<Pair> q = new LinkedList<>();

        int[] dist = new int[mod];
        Arrays.fill(dist, (int)1e9);

        dist[start] = 0;
        q.offer(new Pair(start, 0));

        while (!q.isEmpty()) {

            Pair it = q.poll();

            int node = it.node;
            int steps = it.steps;

            // Try multiplying with every number
            for (int num : arr) {

                int newNode = (node * num) % mod;

                if (steps + 1 < dist[newNode]) {

                    dist[newNode] = steps + 1;

                    if (newNode == end)
                        return steps + 1;

                    q.offer(new Pair(newNode, steps + 1));
                }
            }
        }

        return -1;
    }
}
