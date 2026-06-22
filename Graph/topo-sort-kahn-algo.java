import java.util.*;
//run this code for detection of cycle in directed graph  - since topo sort works only with acyclic graph 
// just count the no of elements in the sort and if it not match the no of vertices then it is not valid and cycle exists
class Solution {

    public ArrayList<Integer> topoSort(int V,
                                       ArrayList<ArrayList<Integer>> adj) {

        int[] indegree = new int[V];

        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        ArrayList<Integer> topo = new ArrayList<>();

        while (!q.isEmpty()) {

            int node = q.poll();

            topo.add(node);

            for (int neighbor : adj.get(node)) {

                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        return topo;
    }
}
