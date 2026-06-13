/*
 * Problem: Fast Exponentiation (binary exponentiation)
 *
 * Compute base^exp efficiently, way faster than multiplying base by itself exp
 * times.
 *
 * Example:
 * Input: base = 2, exp = 10
 * Output: 1024
 *
 * Approach: naive is exp multiplications = O(exp). the fast way uses the fact that
 *           x^n = (x^2)^(n/2) when n is even, and x * x^(n-1) when odd. so each step
 *           we square the base and halve the exponent, only multiplying into the
 *           result when the current exponent bit is 1. that turns O(exp) into
 *           O(log exp). this is everywhere in competitive programming (and modular
 *           versions are used in crypto).
 *
 * Time: O(log exp)
 * Space: O(1)
 *
 * Topics: math, binary exponentiation
 */
public class FastExponentiation {

    static long power(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) result *= base;  // odd exponent -> multiply current base in
            base *= base;                         // square the base
            exp >>= 1;                            // halve the exponent
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + power(2, 10)); // expected: 1024
        System.out.println("Test 2: " + power(3, 5));  // expected: 243
        System.out.println("Test 3: " + power(5, 0));  // expected: 1
        System.out.println("Test 4: " + power(2, 20)); // expected: 1048576
    }
}
