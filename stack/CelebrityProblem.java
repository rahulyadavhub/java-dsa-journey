/*
 * Problem: The Celebrity Problem
 *
 * In a party of n people, a celebrity is someone who EVERYONE knows but who
 * knows NO ONE. Given a knows(a, b) relation (as a matrix), find the celebrity's
 * index, or -1.
 *
 * Example:
 * knows matrix (1 = row knows col):
 *   0 1 0
 *   0 0 0
 *   0 1 0
 * Output: 1   (person 1 is known by 0 and 2, and knows nobody)
 *
 * Approach: stack trick. push everyone. pop two people a and b: if a knows b, a
 *           can't be the celeb so drop a; else b can't be, drop b. one candidate
 *           survives. then verify it really knows no one and is known by all,
 *           because the elimination only proves "at most one possible celeb".
 *
 * Time: O(n)
 * Space: O(n)
 *
 * Topics: stack, elimination
 */

import java.util.Stack;

public class CelebrityProblem {

    static boolean knows(int[][] m, int a, int b) { return m[a][b] == 1; }

    static int solve(int[][] m) {
        int n = m.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) stack.push(i);

        // narrow down to one candidate
        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();
            if (knows(m, a, b)) stack.push(b);  // a knows b -> a isn't celeb
            else stack.push(a);                  // a doesn't know b -> b isn't celeb
        }

        int cand = stack.pop();
        // verify the candidate properly
        for (int i = 0; i < n; i++) {
            if (i == cand) continue;
            if (knows(m, cand, i) || !knows(m, i, cand)) return -1;
        }
        return cand;
    }

    public static void main(String[] args) {
        int[][] m1 = {{0,1,0},{0,0,0},{0,1,0}};
        System.out.println("Test 1: " + solve(m1)); // expected: 1

        int[][] m2 = {{0,1},{1,0}};
        System.out.println("Test 2: " + solve(m2)); // expected: -1 (no celebrity)
    }
}
