/*
 * Problem: Stone Game
 *
 * Piles of stones in a row. Alice and Bob take turns, each taking a whole pile
 * from EITHER end. Both play optimally. Alice goes first - does she win? (Total
 * stones is odd so there are no ties.)
 *
 * Example:
 * Input: [5,3,4,5]
 * Output: true   (Alice can always win)
 *
 * Approach: game DP. dp[i][j] = the best SCORE DIFFERENCE (current player's stones
 *           minus opponent's) the player to move can guarantee on piles i..j. on
 *           your turn you take pile i or pile j; whatever you take adds to your
 *           score, and then the opponent faces the smaller range (so you subtract
 *           their best result). dp[i][j] = max(piles[i] - dp[i+1][j],
 *           piles[j] - dp[i][j-1]). Alice wins if dp[0][n-1] > 0. the "subtract the
 *           opponent's best" is the minimax trick.
 *
 * Time: O(n^2)
 * Space: O(n^2)
 *
 * Topics: dp, game theory, interval dp
 */
public class StoneGame {

    static boolean solve(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = piles[i];  // one pile: take it all

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j],   // take left
                                    piles[j] - dp[i][j - 1]);  // take right
            }
        }
        return dp[0][n - 1] > 0;   // Alice ahead -> she wins
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{5,3,4,5})); // expected: true
        System.out.println("Test 2: " + solve(new int[]{3,7,2,3})); // expected: true
    }
}
