/*
 * Problem: Spiral Matrix
 *
 * Return all elements of a matrix in spiral order (clockwise from top-left).
 *
 * Example:
 * Input: [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 * Approach: keep 4 boundaries - top, bottom, left, right. peel one layer at a
 *           time: go right across top row, down the right col, left across
 *           bottom row, up the left col. shrink the boundaries after each side.
 *           the two extra if-checks stop us from re-reading a row/col when the
 *           matrix isn't square (that bug bit me first time).
 *
 * Time: O(m*n)
 * Space: O(1) extra
 *
 * Topics: matrix, simulation
 */

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    static List<Integer> solve(int[][] m) {
        List<Integer> res = new ArrayList<>();
        if (m.length == 0) return res;

        int top = 0, bottom = m.length - 1;
        int left = 0, right = m[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int c = left; c <= right; c++) res.add(m[top][c]);   // top row ->
            top++;
            for (int r = top; r <= bottom; r++) res.add(m[r][right]); // right col v
            right--;

            if (top <= bottom) {
                for (int c = right; c >= left; c--) res.add(m[bottom][c]); // bottom row <-
                bottom--;
            }
            if (left <= right) {
                for (int r = bottom; r >= top; r--) res.add(m[r][left]);   // left col ^
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        // expected: [1, 2, 3, 6, 9, 8, 7, 4, 5]
        System.out.println("Test 2: " + solve(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
        // expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
    }
}
