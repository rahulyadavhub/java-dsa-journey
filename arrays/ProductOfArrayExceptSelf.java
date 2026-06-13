/*
 * Problem: Product of Array Except Self
 *
 * Return an array where output[i] = product of every element except nums[i].
 * Not allowed to use division.
 *
 * Example:
 * Input: [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Approach: the answer for index i is (product of everything to its left) *
 *           (product of everything to its right). so do two passes:
 *           one left-to-right filling prefix products, one right-to-left
 *           multiplying by suffix products. no division needed.
 *
 * Time: O(n)
 * Space: O(1) extra (output array doesn't count)
 *
 * Topics: prefix/suffix product, array
 */

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    static int[] solve(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        // first pass: res[i] = product of all elements to the LEFT of i
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        // second pass: multiply by product of all elements to the RIGHT
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            right = right * nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + Arrays.toString(solve(new int[]{1,2,3,4})));  // expected: [24, 12, 8, 6]
        System.out.println("Test 2: " + Arrays.toString(solve(new int[]{-1,1,0,-3,3}))); // expected: [0, 0, 9, 0, 0]
    }
}
