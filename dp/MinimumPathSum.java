/*
 * Problem: Minimum Path Sum
 *
 * In an m x n grid of non-negative numbers, find a path from top-left to
 * bottom-right that minimizes the sum of numbers along it. You can only move
 * right or down.
 *
 * Example:
 * Input: [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7   (1->3->1->1->1)
 *
 * Approach: grid DP. dp[r][c] = cheapest cost to reach cell (r,c). since you can
 *           only come from above or from the left, dp[r][c] = grid[r][c] +
 *           min(dp[r-1][c], dp[r][c-1]). fill the first row/column first (only one
 *           way to reach those), then the rest. I just modify the grid in place to
 *           save space.
 *
 * Time: O(m*n)
 * Space: O(1) extra (in place)
 *
 * Topics: dp, grid dp
 */
public class MinimumPathSum {

    static int solve(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) continue;                 // start, nothing to add
                else if (r == 0) grid[r][c] += grid[r][c - 1];  // top row: only from left
                else if (c == 0) grid[r][c] += grid[r - 1][c];  // left col: only from above
                else grid[r][c] += Math.min(grid[r - 1][c], grid[r][c - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[][]{{1,3,1},{1,5,1},{4,2,1}})); // expected: 7
        System.out.println("Test 2: " + solve(new int[][]{{1,2,3},{4,5,6}}));          // expected: 12
    }
}
