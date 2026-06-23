import java.util.*;

class Solution {

    private List<Integer> topoSort(int V, List<List<Integer>> adj) {

        int[] indegree = new int[V];

        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        List<Integer> topo = new ArrayList<>();

        while (!q.isEmpty()) {

            int node = q.poll();
            topo.add(node);

            for (int it : adj.get(node)) {

                indegree[it]--;

                if (indegree[it] == 0)
                    q.offer(it);
            }
        }

        return topo;
    }

    public String findOrder(String[] dict, int N, int K) {

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }

        // Build Graph
        for (int i = 0; i < N - 1; i++) {

            String s1 = dict[i];
            String s2 = dict[i + 1];

            // LEETCODE EXTRA CHECK
            if (s1.length() > s2.length() && s1.startsWith(s2))
                return "";

            int len = Math.min(s1.length(), s2.length());

            for (int ptr = 0; ptr < len; ptr++) {

                if (s1.charAt(ptr) != s2.charAt(ptr)) {

                    int u = s1.charAt(ptr) - 'a';
                    int v = s2.charAt(ptr) - 'a';

                    // avoid duplicate edges
                    if (!adj.get(u).contains(v))
                        adj.get(u).add(v);

                    break;
                }
            }
        }

        List<Integer> topo = topoSort(K, adj);

        // LEETCODE EXTRA CHECK
        // if cycle exists topo sort won't contain all nodes
        if (topo.size() != K)
            return "";

        StringBuilder ans = new StringBuilder();

        for (int it : topo) {
            ans.append((char)(it + 'a'));
        }

        return ans.toString();
    }
}
