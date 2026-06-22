import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //form the directed graph from the given array
        // apply topo sort so that we can see if there is cyclic dependency between tasks because of which they can't be linearly arranged
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        for (int[] pre : prerequisites) {

            int course = pre[0];
            int prerequisite = pre[1];

            adj.get(prerequisite).add(course);

            indegree[course]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int count = 0;

        while (!q.isEmpty()) {

            int node = q.poll();

            count++;

            for (int neighbor : adj.get(node)) {

                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        return count == numCourses;
        //return topo sort if ordering is asked to be printed
    }
}
