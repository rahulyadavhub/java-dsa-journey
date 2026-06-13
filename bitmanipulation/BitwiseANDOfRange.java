/*
 * Problem: Bitwise AND of Numbers Range
 *
 * Given [left, right], return the bitwise AND of ALL numbers in that range
 * (inclusive).
 *
 * Example:
 * Input: left = 5, right = 7
 * Output: 4   (5 & 6 & 7 = 4)
 *
 * Approach: ANDing a whole range zeroes out any bit that changes within the range.
 *           the only bits that survive are the COMMON PREFIX bits that left and
 *           right share. so the answer is just that common high prefix with zeros
 *           after it. trick: shift both numbers right until they're equal (counting
 *           shifts), then shift the common value back left by that count.
 *
 * Time: O(log n)
 * Space: O(1)
 *
 * Topics: bit manipulation
 */
public class BitwiseANDOfRange {

    static int solve(int left, int right) {
        int shift = 0;
        // strip differing low bits until left and right match (their common prefix)
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        return left << shift;   // shift the common prefix back, zeros fill the rest
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(5, 7));   // expected: 4
        System.out.println("Test 2: " + solve(0, 0));   // expected: 0
        System.out.println("Test 3: " + solve(1, 2147483647)); // expected: 0
    }
}
