/*
 * Problem: Count Primes
 *
 * Count how many prime numbers are strictly less than n.
 *
 * Example:
 * Input: n = 10
 * Output: 4   (2, 3, 5, 7)
 *
 * Approach: same Sieve of Eratosthenes idea, just count instead of collecting.
 *           mark all multiples of each prime as non-prime, then count what's left.
 *           checking each number individually for primality would be way too slow
 *           for big n - the sieve is the move.
 *
 * Time: O(n log log n)
 * Space: O(n)
 *
 * Topics: math, primes, sieve
 */
public class CountPrimes {

    static int solve(int n) {
        if (n < 3) return 0;                 // nothing below 2 is prime
        boolean[] notPrime = new boolean[n];
        int count = 0;

        for (int p = 2; p < n; p++) {
            if (!notPrime[p]) {
                count++;
                for (long m = (long) p * p; m < n; m += p) notPrime[(int) m] = true;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(10)); // expected: 4
        System.out.println("Test 2: " + solve(0));  // expected: 0
        System.out.println("Test 3: " + solve(2));  // expected: 0
        System.out.println("Test 4: " + solve(100)); // expected: 25
    }
}
