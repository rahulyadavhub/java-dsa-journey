/*
 * Problem: Subset Sum (DP version)
 *
 * Given an array of positive integers and a target, can any subset add up exactly
 * to the target? (The recursive version is in recursion/SubsetSum.java - this is
 * the DP that kills the exponential time.)
 *
 * Example:
 * Input: arr = [3,34,4,12,5,2], target = 9
 * Output: true
 *
 * Approach: boolean DP. dp[i][s] = can we make sum s using the first i elements?
 *           dp[i][0] is always true (empty subset). for each element either don't
 *           use it (dp[i-1][s]) or use it if it fits (dp[i-1][s - arr[i-1]]).
 *           it's the same "take or skip" as knapsack but tracking reachability
 *           instead of value.
 *
 * Time: O(n*target)
 * Space: O(n*target)
 *
 * Topics: dp, subset sum, knapsack family
 */
public class SubsetSumProblem {

    static boolean solve(int[] arr, int target) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][target + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = true;  // sum 0 always doable (take nothing)

        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= target; s++) {
                dp[i][s] = dp[i - 1][s];               // skip arr[i-1]
                if (arr[i - 1] <= s) {
                    dp[i][s] = dp[i][s] || dp[i - 1][s - arr[i - 1]];  // or take it
                }
            }
        }
        return dp[n][target];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{3,34,4,12,5,2}, 9)); // expected: true
        System.out.println("Test 2: " + solve(new int[]{1,2,3}, 7));         // expected: false
        System.out.println("Test 3: " + solve(new int[]{1,5,11,5}, 11));     // expected: true
    }
}
