/*
 * Problem: Count Set Bits (number of 1s in binary, aka Hamming weight)
 *
 * Count how many 1-bits are in the binary representation of a number.
 *
 * Example:
 * Input: 11   (binary 1011)
 * Output: 3
 *
 * Approach: the slick way is Brian Kernighan's trick: n & (n - 1) flips off the
 *           LOWEST set bit of n. so keep doing n = n & (n - 1) and count how many
 *           times until n becomes 0 - that count is the number of set bits. it only
 *           loops once per set bit (not once per total bit), which is neat.
 *
 * Time: O(number of set bits)
 * Space: O(1)
 *
 * Topics: bit manipulation, Brian Kernighan
 */
public class CountSetBits {

    static int solve(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);   // clears the lowest set bit
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(11)); // expected: 3 (1011)
        System.out.println("Test 2: " + solve(0));  // expected: 0
        System.out.println("Test 3: " + solve(7));  // expected: 3 (111)
        System.out.println("Test 4: " + solve(128)); // expected: 1 (10000000)
    }
}
