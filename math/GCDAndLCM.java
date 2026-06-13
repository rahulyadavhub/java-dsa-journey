/*
 * Problem: GCD and LCM
 *
 * Compute the greatest common divisor and least common multiple of two numbers.
 *
 * Example:
 * Input: a = 12, b = 18
 * Output: GCD = 6, LCM = 36
 *
 * Approach: GCD uses Euclid's algorithm - gcd(a, b) = gcd(b, a % b), keep going
 *           until b is 0, then a is the answer. it works because any common divisor
 *           of a and b also divides a % b. once you have the GCD, LCM is just
 *           a * b / gcd (divide first to avoid overflow). Euclid's is ancient and
 *           still the standard way.
 *
 * Time: O(log(min(a,b)))
 * Space: O(1)
 *
 * Topics: math, number theory, Euclid
 */
public class GCDAndLCM {

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;   // divide first so we don't overflow
    }

    public static void main(String[] args) {
        System.out.println("gcd(12,18): " + gcd(12, 18)); // expected: 6
        System.out.println("lcm(12,18): " + lcm(12, 18)); // expected: 36
        System.out.println("gcd(7,13): " + gcd(7, 13));   // expected: 1 (coprime)
        System.out.println("lcm(4,6): " + lcm(4, 6));     // expected: 12
    }
}
