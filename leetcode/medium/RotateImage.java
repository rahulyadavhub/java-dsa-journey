/*
 * Problem: Rotate Image  (LeetCode #48 - Medium)
 *
 * Rotate an n x n matrix 90 degrees clockwise, in place.
 *
 * Example:
 * Input:  [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 * Approach: cute two-step trick instead of fiddly index math.
 *           1) TRANSPOSE the matrix (swap m[i][j] with m[j][i]) - flips it over the
 *              main diagonal.
 *           2) REVERSE each row.
 *           transpose + reverse-rows = 90 degree clockwise rotation. (for counter-
 *           clockwise you'd reverse columns instead.) much easier to remember than
 *           rotating ring by ring.
 *
 * Time: O(n^2)
 * Space: O(1)
 *
 * Topics: matrix, in-place
 */

import java.util.Arrays;

public class RotateImage {

    static int[][] solve(int[][] m) {
        int n = m.length;

        // step 1: transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int t = m[i][j]; m[i][j] = m[j][i]; m[j][i] = t;
            }
        }
        // step 2: reverse each row
        for (int[] row : m) {
            for (int l = 0, r = n - 1; l < r; l++, r--) {
                int t = row[l]; row[l] = row[r]; row[r] = t;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + Arrays.deepToString(solve(new int[][]{{1,2,3},{4,5,6},{7,8,9}})));
        // expected: [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
        System.out.println("Test 2: " + Arrays.deepToString(solve(new int[][]{{1,2},{3,4}})));
        // expected: [[3, 1], [4, 2]]
    }
}
