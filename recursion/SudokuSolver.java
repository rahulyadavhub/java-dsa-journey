/*
 * Problem: Sudoku Solver
 *
 * Fill a 9x9 sudoku board (0 means empty) so every row, column, and 3x3 box has
 * digits 1-9 with no repeats.
 *
 * Approach: backtracking again. find the next empty cell, try digits 1-9 in it,
 *           keep one that doesn't break the rules, recurse. if the recursion gets
 *           stuck later, undo the digit and try the next. the isValid check looks
 *           at the cell's row, column, and 3x3 box. brute but it works for 9x9.
 *
 * Time: hard to state, exponential worst case but fast in practice
 * Space: O(1) extra (recursion depth bounded by empty cells)
 *
 * Topics: backtracking, recursion, matrix
 */

import java.util.Arrays;

public class SudokuSolver {

    static boolean solve(int[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == 0) {                  // empty cell to fill
                    for (int d = 1; d <= 9; d++) {
                        if (isValid(board, r, c, d)) {
                            board[r][c] = d;
                            if (solve(board)) return true; // worked downstream
                            board[r][c] = 0;               // undo, try next digit
                        }
                    }
                    return false;   // no digit fit here -> backtrack
                }
            }
        }
        return true;   // no empty cells left -> solved
    }

    static boolean isValid(int[][] b, int row, int col, int d) {
        for (int i = 0; i < 9; i++) {
            if (b[row][i] == d) return false;       // row clash
            if (b[i][col] == d) return false;       // column clash
            int br = 3 * (row / 3) + i / 3;
            int bc = 3 * (col / 3) + i % 3;
            if (b[br][bc] == d) return false;       // 3x3 box clash
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] board = {
            {5,3,0, 0,7,0, 0,0,0},
            {6,0,0, 1,9,5, 0,0,0},
            {0,9,8, 0,0,0, 0,6,0},
            {8,0,0, 0,6,0, 0,0,3},
            {4,0,0, 8,0,3, 0,0,1},
            {7,0,0, 0,2,0, 0,0,6},
            {0,6,0, 0,0,0, 2,8,0},
            {0,0,0, 4,1,9, 0,0,5},
            {0,0,0, 0,8,0, 0,7,9}
        };
        System.out.println("Solvable: " + solve(board)); // expected: true
        for (int[] row : board) System.out.println(Arrays.toString(row));
    }
}
