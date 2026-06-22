import java.util.*;

class Solution {

    private void dfs(int node,
                     ArrayList<ArrayList<Integer>> adj,
                     boolean[] visited,
                     Stack<Integer> stack) {

        visited[node] = true;

        for (int neighbor: adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, stack);
            }
        }

        stack.push(node);
    }

    public ArrayList<Integer> topoSort(int V,
                                       ArrayList<ArrayList<Integer>> adj) {

        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, adj, visited, stack);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        while (!stack.isEmpty()) {
            ans.add(stack.pop());
        }

        return ans;
    }
}
