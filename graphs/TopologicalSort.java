/*
 * Problem: Topological Sort (of a DAG)
 *
 * Order the vertices of a directed acyclic graph so that for every edge u -> v,
 * u comes before v. Useful for things like course prerequisites or build order.
 *
 * Example:
 *   5 -> 0, 5 -> 2, 4 -> 0, 4 -> 1, 2 -> 3, 3 -> 1
 * One valid order: [4, 5, 2, 3, 1, 0]
 *
 * Approach: this uses Kahn's algorithm (BFS based). compute each node's in-degree
 *           (number of incoming edges). start with all in-degree-0 nodes in a
 *           queue (nothing depends on them). pop one, add it to the order, and
 *           "remove" it by decrementing its neighbours' in-degrees; any that hit 0
 *           join the queue. if we output all V nodes, great; if not, there was a
 *           cycle (topo sort only works on DAGs).
 *
 * Time: O(V + E)
 * Space: O(V)
 *
 * Topics: graph, topological sort, BFS, in-degree
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {

    static List<Integer> topoSort(int v, List<List<Integer>> adj) {
        int[] indegree = new int[v];
        for (int u = 0; u < v; u++)
            for (int nb : adj.get(u)) indegree[nb]++;

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < v; i++) if (indegree[i] == 0) q.add(i);

        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            order.add(node);
            for (int nb : adj.get(node)) {
                if (--indegree[nb] == 0) q.add(nb);  // all its prereqs are done
            }
        }
        return order; // size < v would mean a cycle exists
    }

    static List<List<Integer>> build(int v, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);
        return adj;
    }

    public static void main(String[] args) {
        int[][] edges = {{5,0},{5,2},{4,0},{4,1},{2,3},{3,1}};
        List<Integer> order = topoSort(6, build(6, edges));
        System.out.println("Topo order: " + order);
        // every edge u->v must have u before v in this list (one valid: [4, 5, 2, 3, 1, 0])
    }
}
