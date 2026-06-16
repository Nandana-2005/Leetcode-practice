import java.util.*;

public class BFS {
    
    public static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[V];

        Queue<Integer> q = new LinkedList<>();

        q.offer(0);
        vis[0] = true;

        while (!q.isEmpty()) {

            int node = q.poll();
            ans.add(node);

            for (int neigh : adj.get(node)) {

                if (!vis[neigh]) {
                    vis[neigh] = true;
                    q.offer(neigh);
                }
            }
        }

        return ans;
    }
}
