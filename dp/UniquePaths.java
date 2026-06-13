/*
 * Problem: Unique Paths
 *
 * A robot at the top-left of an m x n grid wants to reach the bottom-right. It can
 * only move right or down. How many distinct paths are there?
 *
 * Example:
 * Input: m = 3, n = 7
 * Output: 28
 *
 * Approach: dp[r][c] = number of ways to reach cell (r,c). there's exactly 1 way
 *           to reach any cell in the first row or first column (just keep going
 *           straight). every other cell is reached from above or from the left, so
 *           dp[r][c] = dp[r-1][c] + dp[r][c-1]. the answer is the bottom-right cell.
 *           (it's also a combinatorics formula, but the DP is clearer.)
 *
 * Time: O(m*n)
 * Space: O(m*n)
 *
 * Topics: dp, grid dp, counting
 */
public class UniquePaths {

    static int solve(int m, int n) {
        int[][] dp = new int[m][n];
        for (int r = 0; r < m; r++) dp[r][0] = 1;  // first column: 1 way each
        for (int c = 0; c < n; c++) dp[0][c] = 1;  // first row: 1 way each

        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(3, 7)); // expected: 28
        System.out.println("Test 2: " + solve(3, 2)); // expected: 3
        System.out.println("Test 3: " + solve(1, 1)); // expected: 1
    }
}
