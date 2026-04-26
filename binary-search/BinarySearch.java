/*
 * Problem: Binary Search
 * Source: Classic DSA
 * Difficulty: Easy
 *
 * Approach:
 * Repeatedly compare the middle element with the target and discard the half
 * where the target cannot exist.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {2, 4, 7, 10, 14, 19, 23, 31};
        int target = 19;

        int index = search(nums, target);

        if (index != -1) {
            System.out.println("Found at index " + index);
        } else {
            System.out.println("Element not found");
        }
    }

    static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
