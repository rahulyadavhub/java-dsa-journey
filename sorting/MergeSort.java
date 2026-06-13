/*
 * Problem: Merge Sort
 *
 * Sort an array using divide and conquer.
 *
 * Example:
 * Input:  [38, 27, 43, 3, 9, 82, 10]
 * Output: [3, 9, 10, 27, 38, 43, 82]
 *
 * Approach: keep splitting the array in half until each piece is size 1 (a single
 *           element is already "sorted"). then merge pairs back together in
 *           sorted order. the merge step is the heart of it - walk two sorted
 *           halves with two pointers, always taking the smaller front element.
 *           guaranteed O(n log n), stable, but needs extra space.
 *
 * Time: O(n log n)
 * Space: O(n)
 *
 * Topics: divide and conquer, sorting, recursion
 */

import java.util.Arrays;

public class MergeSort {

    static void sort(int[] a, int lo, int hi) {
        if (lo >= hi) return;            // one element or empty -> done
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);                // sort left half
        sort(a, mid + 1, hi);            // sort right half
        merge(a, lo, mid, hi);           // combine the two sorted halves
    }

    static void merge(int[] a, int lo, int mid, int hi) {
        int[] temp = new int[hi - lo + 1];
        int i = lo, j = mid + 1, k = 0;

        // pick the smaller front element from each half
        while (i <= mid && j <= hi) {
            if (a[i] <= a[j]) temp[k++] = a[i++];
            else temp[k++] = a[j++];
        }
        while (i <= mid) temp[k++] = a[i++];   // leftovers from left
        while (j <= hi) temp[k++] = a[j++];    // leftovers from right

        // copy merged result back into the original array
        for (int x = 0; x < temp.length; x++) a[lo + x] = temp[x];
    }

    public static void main(String[] args) {
        int[] a = {38, 27, 43, 3, 9, 82, 10};
        sort(a, 0, a.length - 1);
        System.out.println("Test 1: " + Arrays.toString(a)); // [3, 9, 10, 27, 38, 43, 82]

        int[] b = {5, 1, 1, 2, 0, 0};
        sort(b, 0, b.length - 1);
        System.out.println("Test 2: " + Arrays.toString(b)); // [0, 0, 1, 1, 2, 5]
    }
}
