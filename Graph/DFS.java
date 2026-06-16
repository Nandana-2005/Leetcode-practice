import java.util.*;

public class DFS {

    static void dfs(int node,
                    boolean[] vis,
                    ArrayList<ArrayList<Integer>> adj,
                    ArrayList<Integer> ans) {

        vis[node] = true;
        ans.add(node);

        for (int neigh : adj.get(node)) {

            if (!vis[neigh]) {
                dfs(neigh, vis, adj, ans);
            }
        }
    }

    public static ArrayList<Integer> dfsOfGraph(int V,
                                                ArrayList<ArrayList<Integer>> adj) {

        boolean[] vis = new boolean[V];
        ArrayList<Integer> ans = new ArrayList<>();

        dfs(0, vis, adj, ans);

        return ans;
    }
}
