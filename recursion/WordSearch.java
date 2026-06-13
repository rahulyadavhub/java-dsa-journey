/*
 * Problem: Word Search
 *
 * Given a grid of letters and a word, return true if the word can be formed by
 * moving through adjacent cells (up/down/left/right), without reusing a cell.
 *
 * Example:
 * board = [[A,B,C,E],[S,F,C,S],[A,D,E,E]], word = "ABCCED"
 * Output: true
 *
 * Approach: try starting a DFS from every cell. from a cell, if the current
 *           letter matches word[idx], mark it used and recurse into the 4
 *           neighbors for word[idx+1]. if any path matches the whole word, done.
 *           un-mark the cell on the way back so other start points can reuse it.
 *           temporarily overwriting the cell with '#' is a cheap "visited" marker.
 *
 * Time: O(m*n*4^L)  L = word length
 * Space: O(L) recursion depth
 *
 * Topics: backtracking, DFS, matrix
 */
public class WordSearch {

    static boolean solve(char[][] board, String word) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (dfs(board, word, r, c, 0)) return true;
            }
        }
        return false;
    }

    static boolean dfs(char[][] b, String word, int r, int c, int idx) {
        if (idx == word.length()) return true;   // matched everything
        if (r < 0 || r >= b.length || c < 0 || c >= b[0].length) return false;
        if (b[r][c] != word.charAt(idx)) return false;

        char saved = b[r][c];
        b[r][c] = '#';   // mark visited

        boolean found = dfs(b, word, r + 1, c, idx + 1)
                     || dfs(b, word, r - 1, c, idx + 1)
                     || dfs(b, word, r, c + 1, idx + 1)
                     || dfs(b, word, r, c - 1, idx + 1);

        b[r][c] = saved; // restore (backtrack)
        return found;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        System.out.println("Test 1: " + solve(board, "ABCCED")); // expected: true
        System.out.println("Test 2: " + solve(board, "SEE"));    // expected: true
        System.out.println("Test 3: " + solve(board, "ABCB"));   // expected: false
    }
}
