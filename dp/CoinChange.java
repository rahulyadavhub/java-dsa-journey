/*
 * Problem: Coin Change
 *
 * Given coin denominations and an amount, return the FEWEST coins needed to make
 * that amount, or -1 if impossible. Unlimited coins of each type.
 *
 * Example:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3   (5 + 5 + 1)
 *
 * Approach: dp[a] = fewest coins to make amount a. start dp[0] = 0 (zero coins for
 *           zero amount). for every amount from 1..amount, try each coin: if it
 *           fits, dp[a] could be dp[a - coin] + 1. take the minimum over all coins.
 *           initialize with a big "infinity" so unreachable amounts stay unreachable.
 *           classic unbounded-knapsack style DP.
 *
 * Time: O(amount * number of coins)
 * Space: O(amount)
 *
 * Topics: dp, bottom-up, unbounded knapsack
 */

import java.util.Arrays;

public class CoinChange {

    static int solve(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);   // "infinity" (can't need more than amount coins)
        dp[0] = 0;

        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (coin <= a) dp[a] = Math.min(dp[a], dp[a - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{1,2,5}, 11)); // expected: 3
        System.out.println("Test 2: " + solve(new int[]{2}, 3));      // expected: -1
        System.out.println("Test 3: " + solve(new int[]{1}, 0));      // expected: 0
    }
}
