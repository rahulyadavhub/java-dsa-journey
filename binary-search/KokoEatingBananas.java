/*
 * Problem: Koko Eating Bananas
 *
 * Koko eats bananas at speed k per hour. There are piles, and each hour she
 * picks one pile and eats k from it (if the pile has less, she finishes it and
 * stops for that hour). Find the smallest k so she finishes all piles in h hours.
 *
 * Example:
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 * Approach: this is "binary search on the answer" - a really common pattern once
 *           you see it. the speed k is somewhere between 1 and max(pile). for a
 *           given k we can compute total hours needed. smaller k -> more hours.
 *           so binary search on k for the smallest one that still fits in h hours.
 *
 * Time: O(n log(maxPile))
 * Space: O(1)
 *
 * Topics: binary search on answer, array
 */
public class KokoEatingBananas {

    static int solve(int[] piles, int h) {
        int lo = 1, hi = 0;
        for (int p : piles) hi = Math.max(hi, p);

        while (lo < hi) {
            int k = lo + (hi - lo) / 2;
            if (hoursNeeded(piles, k) <= h) hi = k; // k works, try slower
            else lo = k + 1;                         // too slow, speed up
        }
        return lo;
    }

    static long hoursNeeded(int[] piles, int k) {
        long hours = 0;
        for (int p : piles) hours += (p + k - 1) / k; // ceil(p/k)
        return hours;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{3,6,7,11}, 8));      // expected: 4
        System.out.println("Test 2: " + solve(new int[]{30,11,23,4,20}, 5)); // expected: 30
        System.out.println("Test 3: " + solve(new int[]{30,11,23,4,20}, 6)); // expected: 23
    }
}
