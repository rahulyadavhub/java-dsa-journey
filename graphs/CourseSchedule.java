/*
 * Problem: Course Schedule
 *
 * numCourses courses (0..n-1) and prerequisites[i] = [a, b] meaning you must take
 * b before a. Return true if you can finish all courses.
 *
 * Example:
 * Input: numCourses = 2, prereqs = [[1,0]]
 * Output: true   (take 0 then 1)
 *
 * Input: numCourses = 2, prereqs = [[1,0],[0,1]]
 * Output: false  (circular dependency)
 *
 * Approach: this is literally "does this directed graph have a cycle?". if there's
 *           a cycle in the prereq graph you can never start it, so it's impossible.
 *           I use Kahn's topological sort: keep removing courses with 0 remaining
 *           prereqs. if we manage to remove all of them, no cycle -> true. if some
 *           are stuck (still have prereqs), there's a cycle -> false.
 *
 * Time: O(V + E)
 * Space: O(V + E)
 *
 * Topics: graph, topological sort, cycle detection, BFS
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {

    static boolean canFinish(int numCourses, int[][] prereqs) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        int[] indegree = new int[numCourses];

        for (int[] p : prereqs) {       // edge: b -> a  (b before a)
            adj.get(p[1]).add(p[0]);
            indegree[p[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) if (indegree[i] == 0) q.add(i);

        int taken = 0;
        while (!q.isEmpty()) {
            int course = q.poll();
            taken++;
            for (int next : adj.get(course)) {
                if (--indegree[next] == 0) q.add(next);
            }
        }
        return taken == numCourses;   // took everything -> no cycle
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + canFinish(2, new int[][]{{1,0}}));        // expected: true
        System.out.println("Test 2: " + canFinish(2, new int[][]{{1,0},{0,1}})); // expected: false
        System.out.println("Test 3: " + canFinish(4, new int[][]{{1,0},{2,1},{3,2}})); // expected: true
    }
}
