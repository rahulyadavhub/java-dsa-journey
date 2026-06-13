/*
 * Problem: Decode Ways  (LeetCode #91 - Medium)
 *
 * 'A'-'Z' map to "1"-"26". Given a digit string, how many ways can it be decoded?
 *
 * Example:
 * Input: "226"
 * Output: 3   ("2 2 6" = BBF, "22 6" = VF, "2 26" = BZ)
 *
 * Approach: DP that feels like climbing stairs with rules. dp[i] = ways to decode
 *           the first i chars. at each position you can:
 *             - take the single digit, IF it's not '0' (add dp[i-1])
 *             - take the two-digit number, IF it's between 10 and 26 (add dp[i-2])
 *           the '0' handling is the tricky part - a '0' must pair with the digit
 *           before it (like "10" or "20"), otherwise the string is undecodable.
 *
 * Time: O(n)
 * Space: O(n)
 *
 * Topics: dp, strings
 */
public class DecodeWays {

    static int solve(String s) {
        if (s.isEmpty() || s.charAt(0) == '0') return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;          // empty string -> 1 way
        dp[1] = 1;          // first char is non-zero (checked above)

        for (int i = 2; i <= n; i++) {
            int one = s.charAt(i - 1) - '0';                       // last single digit
            int two = Integer.parseInt(s.substring(i - 2, i));     // last two digits
            if (one >= 1) dp[i] += dp[i - 1];                      // valid single
            if (two >= 10 && two <= 26) dp[i] += dp[i - 2];        // valid pair
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("226")); // expected: 3
        System.out.println("Test 2: " + solve("12"));  // expected: 2
        System.out.println("Test 3: " + solve("06"));  // expected: 0
    }
}
