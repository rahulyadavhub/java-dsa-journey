/*
 * Problem: Single Number
 *
 * Every element appears twice except one. Find the one that appears once.
 *
 * Example:
 * Input: [4,1,2,1,2]
 * Output: 4
 *
 * Approach: XOR magic. two key facts: x ^ x = 0 (a number XORed with itself
 *           cancels out) and x ^ 0 = x. so if you XOR ALL the numbers together,
 *           every pair cancels to 0 and only the lonely number is left. O(1) space,
 *           no hashmap needed. one of those tricks that feels like cheating.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: bit manipulation, XOR
 */
public class SingleNumber {

    static int solve(int[] nums) {
        int result = 0;
        for (int n : nums) result ^= n;   // pairs cancel, single survives
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{4,1,2,1,2})); // expected: 4
        System.out.println("Test 2: " + solve(new int[]{2,2,1}));     // expected: 1
        System.out.println("Test 3: " + solve(new int[]{1}));         // expected: 1
    }
}
