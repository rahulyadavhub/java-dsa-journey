/*
 * Problem: Next Permutation
 *
 * Rearrange numbers into the next lexicographically greater permutation.
 * If it's already the largest, wrap around to the smallest (sorted) order.
 *
 * Example:
 * Input: [1,2,3]
 * Output: [1,3,2]
 *
 * Input: [3,2,1]
 * Output: [1,2,3]
 *
 * Approach: this one is a known recipe, hard to come up with on the spot.
 *   1) walk from the right, find first index i where nums[i] < nums[i+1].
 *      that's the "pivot" - the spot we can grow.
 *   2) if no such i, whole thing is descending -> just reverse to smallest.
 *   3) else find the smallest number to the right of i that's still bigger
 *      than nums[i], swap them.
 *   4) reverse everything after i so the tail is as small as possible.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: array, math/permutations
 */

import java.util.Arrays;

public class NextPermutation {

    static int[] solve(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // step 1: find the pivot
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        if (i >= 0) {
            // step 3: find element just bigger than pivot, from the right
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }

        // step 4: reverse the suffix after i
        reverse(nums, i + 1, n - 1);
        return nums;
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    static void reverse(int[] a, int lo, int hi) {
        while (lo < hi) swap(a, lo++, hi--);
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + Arrays.toString(solve(new int[]{1,2,3}))); // expected: [1, 3, 2]
        System.out.println("Test 2: " + Arrays.toString(solve(new int[]{3,2,1}))); // expected: [1, 2, 3]
        System.out.println("Test 3: " + Arrays.toString(solve(new int[]{1,1,5}))); // expected: [1, 5, 1]
    }
}
