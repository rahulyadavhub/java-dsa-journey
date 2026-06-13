/*
 * Problem: Find Peak Element
 *
 * A peak is an element strictly greater than its neighbors. Return the index of
 * ANY peak. Assume nums[-1] and nums[n] are -infinity. Must be O(log n).
 *
 * Example:
 * Input: [1,2,3,1]
 * Output: 2   (nums[2]=3 is a peak)
 *
 * Approach: feels weird to binary search something unsorted, but it works.
 *           look at mid. if nums[mid] < nums[mid+1], a peak must exist to the
 *           RIGHT (we're going uphill, and the array ends eventually). otherwise
 *           a peak is at mid or to the left. keep halving until lo == hi.
 *
 * Time: O(log n)
 * Space: O(1)
 *
 * Topics: binary search, array
 */
public class PeakElement {

    static int solve(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[mid + 1]) lo = mid + 1; // uphill, peak is right
            else hi = mid;                                // downhill, peak is mid or left
        }
        return lo;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{1,2,3,1}));       // expected: 2
        System.out.println("Test 2: " + solve(new int[]{1,2,1,3,5,6,4})); // expected: 5 (or 1)
        System.out.println("Test 3: " + solve(new int[]{1}));             // expected: 0
    }
}
