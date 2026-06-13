/*
 * Problem: Find Minimum in Rotated Sorted Array
 *
 * A sorted array was rotated. Find the smallest element. O(log n).
 *
 * Example:
 * Input: [3,4,5,1,2]
 * Output: 1
 *
 * Approach: the minimum is the "pivot" point where the rotation happened.
 *           binary search: compare nums[mid] with nums[hi]. if nums[mid] > nums[hi]
 *           the min must be to the RIGHT of mid (the drop hasn't happened yet).
 *           otherwise the min is at mid or to its left. keep narrowing until
 *           lo == hi, that's the answer.
 *
 * Time: O(log n)
 * Space: O(1)
 *
 * Topics: binary search, array
 */
public class FindMinimumInRotatedSortedArray {

    static int solve(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) lo = mid + 1;  // min is to the right
            else hi = mid;                            // min is mid or left
        }
        return nums[lo];
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{3,4,5,1,2}));     // expected: 1
        System.out.println("Test 2: " + solve(new int[]{4,5,6,7,0,1,2})); // expected: 0
        System.out.println("Test 3: " + solve(new int[]{11,13,15,17}));   // expected: 11
    }
}
