/*
 * Problem: Median of Two Sorted Arrays
 *
 * Two sorted arrays. Find the median of the combined array in O(log(m+n)).
 *
 * Example:
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.0
 *
 * Approach: this is one of the hardest binary search problems imo. the idea:
 *           binary search a "cut" in the smaller array. wherever we cut array1,
 *           the cut in array2 is forced (so left side has exactly half the total
 *           elements). a cut is valid when maxLeft1 <= minRight2 AND maxLeft2 <= minRight1.
 *           then the median comes from the elements right around the two cuts.
 *           honestly took me multiple tries + a video to really get this.
 *
 * Time: O(log(min(m,n)))
 * Space: O(1)
 *
 * Topics: binary search, array
 */
public class MedianOfTwoSortedArrays {

    static double solve(int[] a, int[] b) {
        if (a.length > b.length) return solve(b, a); // always binary search the smaller one
        int m = a.length, n = b.length;
        int total = m + n;
        int lo = 0, hi = m;

        while (lo <= hi) {
            int cut1 = (lo + hi) / 2;        // how many from a go left
            int cut2 = (total + 1) / 2 - cut1; // rest from b go left

            int left1  = (cut1 == 0) ? Integer.MIN_VALUE : a[cut1 - 1];
            int right1 = (cut1 == m) ? Integer.MAX_VALUE : a[cut1];
            int left2  = (cut2 == 0) ? Integer.MIN_VALUE : b[cut2 - 1];
            int right2 = (cut2 == n) ? Integer.MAX_VALUE : b[cut2];

            if (left1 <= right2 && left2 <= right1) {  // valid cut
                if (total % 2 == 1) return Math.max(left1, left2);
                return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
            } else if (left1 > right2) {
                hi = cut1 - 1;   // took too many from a
            } else {
                lo = cut1 + 1;   // took too few from a
            }
        }
        return 0.0; // won't reach here for valid input
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{1,3}, new int[]{2}));     // expected: 2.0
        System.out.println("Test 2: " + solve(new int[]{1,2}, new int[]{3,4}));   // expected: 2.5
        System.out.println("Test 3: " + solve(new int[]{0,0}, new int[]{0,0}));   // expected: 0.0
    }
}
