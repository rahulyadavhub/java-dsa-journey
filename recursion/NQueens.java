/*
 * Problem: N-Queens
 *
 * Place N queens on an N x N board so none attack each other (no two share a
 * row, column, or diagonal). Return how many distinct ways there are.
 *
 * Example:
 * Input: n = 4
 * Output: 2
 *
 * Approach: classic backtracking. go row by row. in each row try every column,
 *           place a queen if it's safe, recurse to the next row, then "undo" the
 *           placement and try the next column. we only need to check columns and
 *           the two diagonals already used (track them in boolean sets) - rows
 *           are automatically fine since we place one per row.
 *
 * Time: O(n!) ish
 * Space: O(n)
 *
 * Topics: backtracking, recursion
 */
public class NQueens {

    static int count;

    static int solve(int n) {
        count = 0;
        boolean[] cols = new boolean[n];
        boolean[] diag = new boolean[2 * n];     // row + col
        boolean[] antiDiag = new boolean[2 * n]; // row - col + n
        place(0, n, cols, diag, antiDiag);
        return count;
    }

    static void place(int row, int n, boolean[] cols, boolean[] diag, boolean[] antiDiag) {
        if (row == n) {            // placed a queen in every row -> valid board
            count++;
            return;
        }
        for (int col = 0; col < n; col++) {
            int d = row + col, ad = row - col + n;
            if (cols[col] || diag[d] || antiDiag[ad]) continue; // under attack

            cols[col] = diag[d] = antiDiag[ad] = true;   // place
            place(row + 1, n, cols, diag, antiDiag);
            cols[col] = diag[d] = antiDiag[ad] = false;  // undo (backtrack)
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(4)); // expected: 2
        System.out.println("Test 2: " + solve(1)); // expected: 1
        System.out.println("Test 3: " + solve(8)); // expected: 92
    }
}
