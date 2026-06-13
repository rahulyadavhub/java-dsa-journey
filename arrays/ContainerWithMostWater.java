/*
 * Problem: Container With Most Water
 *
 * Each number is a vertical line height. Pick two lines that together with the
 * x-axis hold the most water. Return that max area.
 *
 * Example:
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * Approach: area = width * min(left height, right height).
 *           start with widest container (both ends). to possibly get more area
 *           we must move the SHORTER line inward (moving the taller one can only
 *           lose width without helping, since the short line caps the height).
 *           greedy two pointers.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: two pointers, greedy, array
 */
public class ContainerWithMostWater {

    static int solve(int[] h) {
        int left = 0, right = h.length - 1;
        int best = 0;

        while (left < right) {
            int area = (right - left) * Math.min(h[left], h[right]);
            best = Math.max(best, area);

            // move whichever side is shorter, hoping for a taller wall
            if (h[left] < h[right]) left++;
            else right--;
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{1,8,6,2,5,4,8,3,7})); // expected: 49
        System.out.println("Test 2: " + solve(new int[]{1,1}));               // expected: 1
        System.out.println("Test 3: " + solve(new int[]{4,3,2,1,4}));         // expected: 16
    }
}
