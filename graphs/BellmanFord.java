/*
 * Problem: Bellman-Ford Shortest Path
 *
 * Shortest distances from a source in a weighted directed graph that MAY have
 * negative edge weights. Also detects negative-weight cycles.
 *
 * Example:
 *   0 ->(4) 1, 0 ->(5) 2, 1 ->(-3) 2
 * Shortest from 0: [0, 4, 1]
 *
 * Approach: simpler than Dijkstra but slower. relax EVERY edge, V-1 times. why
 *           V-1? because a shortest path has at most V-1 edges, and each full pass
 *           guarantees one more edge of every shortest path is locked in. after
 *           that, if any edge can STILL be relaxed, there's a negative cycle. it
 *           handles negative edges, which Dijkstra can't.
 *
 * Time: O(V * E)
 * Space: O(V)
 *
 * Topics: graph, shortest path, Bellman-Ford, negative weights
 */

import java.util.Arrays;

public class BellmanFord {

    // edges as [from, to, weight]
    static int[] bellmanFord(int v, int[][] edges, int src) {
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // relax all edges V-1 times
        for (int i = 0; i < v - 1; i++) {
            for (int[] e : edges) {
                int u = e[0], to = e[1], w = e[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[to]) {
                    dist[to] = dist[u] + w;
                }
            }
        }

        // one more pass: if anything still improves -> negative cycle
        for (int[] e : edges) {
            int u = e[0], to = e[1], w = e[2];
            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[to]) {
                System.out.println("negative weight cycle detected!");
                return null;
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,4},{0,2,5},{1,2,-3}};
        System.out.println("Shortest from 0: " + Arrays.toString(bellmanFord(3, edges, 0)));
        // expected: [0, 4, 1]
    }
}
