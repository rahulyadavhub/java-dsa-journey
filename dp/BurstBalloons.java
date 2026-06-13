/*
 * Problem: Burst Balloons
 *
 * Balloons in a row each have a number. Bursting balloon i gives you
 * nums[left] * nums[i] * nums[right] coins, where left/right are the balloons
 * still next to it. Maximize total coins. (Missing edges count as 1.)
 *
 * Example:
 * Input: [3,1,5,8]
 * Output: 167
 *
 * Approach: this is a hard interval DP and the twist is thinking BACKWARDS. instead
 *           of "which balloon to burst first", ask "which balloon in this range is
 *           burst LAST". if k is last in range (i..j), then by the time we burst it
 *           its only neighbours are the boundaries i-1 and j+1 (everything inside is
 *           already gone), giving nums[i-1]*nums[k]*nums[j+1] plus the best of the
 *           two sub-ranges. I pad the array with 1s on both ends to handle edges.
 *           genuinely took me a few rewatches to get the "last to burst" framing.
 *
 * Time: O(n^3)
 * Space: O(n^2)
 *
 * Topics: dp, interval dp
 */
public class BurstBalloons {

    static int solve(int[] nums) {
        int n = nums.length;
        int[] balloons = new int[n + 2];
        balloons[0] = balloons[n + 1] = 1;          // padding
        for (int i = 0; i < n; i++) balloons[i + 1] = nums[i];

        int[][] dp = new int[n + 2][n + 2];

        for (int len = 1; len <= n; len++) {         // window length
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {       // k = last balloon burst in [i,j]
                    int coins = balloons[i - 1] * balloons[k] * balloons[j + 1]
                              + dp[i][k - 1] + dp[k + 1][j];
                    dp[i][j] = Math.max(dp[i][j], coins);
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{3,1,5,8})); // expected: 167
        System.out.println("Test 2: " + solve(new int[]{1,5}));      // expected: 10
    }
}
