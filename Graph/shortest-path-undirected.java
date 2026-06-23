import java.util.*;

public class practice {
    public int[] shortestPath(int[][] edges,int n,int m,int src){
        //creating adj list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0;i<m;i++){
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);    
        }

        //insert infinity in all the positions of the distance array
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);        

        dist[src] = 0; // source distance always 0

        //BFS traversal using queue to check every neighbour and modify their distances whenever better
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        while(!q.isEmpty()){
            int node = q.peek();
            q.remove();

            for(int it:adj.get(node)){
                //relaxation algorithm 
                if(dist[node]+1<dist[it]){
                    dist[it] = dist[node]+1;
                    q.add(it);
                }
            }
        }

        for(int i = 0;i<n;i++){
            if(dist[i]==Integer.MAX_VALUE){
                dist[i] = -1;
            }
        }
        return dist;
    }
}
