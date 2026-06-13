/*
 * Problem: Quick Sort
 *
 * Sort an array using divide and conquer with a pivot.
 *
 * Example:
 * Input:  [10, 7, 8, 9, 1, 5]
 * Output: [1, 5, 7, 8, 9, 10]
 *
 * Approach: pick a pivot (here the last element). partition the array so
 *           everything smaller than pivot goes left, bigger goes right, and the
 *           pivot lands in its final sorted spot. then recurse on the left and
 *           right chunks. no merge step needed - the partitioning does the work.
 *           usually O(n log n), but O(n^2) worst case if pivots are unlucky
 *           (e.g. already sorted with last-element pivot).
 *
 * Time: O(n log n) average, O(n^2) worst
 * Space: O(log n) recursion stack
 *
 * Topics: divide and conquer, sorting, in-place
 */

import java.util.Arrays;

public class QuickSort {

    static void sort(int[] a, int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(a, lo, hi);    // pivot now at its final position p
        sort(a, lo, p - 1);
        sort(a, p + 1, hi);
    }

    static int partition(int[] a, int lo, int hi) {
        int pivot = a[hi];               // pick last element as pivot
        int i = lo - 1;                  // boundary of "smaller than pivot" region
        for (int j = lo; j < hi; j++) {
            if (a[j] < pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, hi);              // put pivot right after the smaller region
        return i + 1;
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {10, 7, 8, 9, 1, 5};
        sort(a, 0, a.length - 1);
        System.out.println("Test 1: " + Arrays.toString(a)); // [1, 5, 7, 8, 9, 10]

        int[] b = {2, 2, 1, 3, 3, 1};
        sort(b, 0, b.length - 1);
        System.out.println("Test 2: " + Arrays.toString(b)); // [1, 1, 2, 2, 3, 3]
    }
}
