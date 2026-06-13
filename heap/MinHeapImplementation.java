/*
 * Problem: Min Heap (built from scratch)
 *
 * Implement a min-heap: a binary tree (stored in an array) where every parent is
 * <= its children, so the smallest element is always at the root. Support insert
 * and extractMin (and peek).
 *
 * Approach: store the heap in an array. for index i: parent = (i-1)/2, children =
 *           2i+1 and 2i+2. two helper moves keep the heap property:
 *           - insert: add at the end, then "bubble up" while it's smaller than its parent.
 *           - extractMin: take root, move the last element to the root, then
 *             "bubble down" swapping with the smaller child until it settles.
 *           heaps are basically just these two sift operations on an array.
 *
 * Time: O(log n) insert and extractMin
 * Space: O(n)
 *
 * Topics: heap, priority queue, array
 */

import java.util.ArrayList;
import java.util.List;

public class MinHeapImplementation {

    List<Integer> heap = new ArrayList<>();

    void insert(int val) {
        heap.add(val);
        bubbleUp(heap.size() - 1);
    }

    int peek() {
        if (heap.isEmpty()) throw new RuntimeException("heap empty");
        return heap.get(0);
    }

    int extractMin() {
        if (heap.isEmpty()) throw new RuntimeException("heap empty");
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);     // move last to root
            bubbleDown(0);
        }
        return min;
    }

    void bubbleUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i) >= heap.get(parent)) break;  // heap property ok
            swap(i, parent);
            i = parent;
        }
    }

    void bubbleDown(int i) {
        int n = heap.size();
        while (true) {
            int smallest = i, left = 2 * i + 1, right = 2 * i + 2;
            if (left < n && heap.get(left) < heap.get(smallest)) smallest = left;
            if (right < n && heap.get(right) < heap.get(smallest)) smallest = right;
            if (smallest == i) break;
            swap(i, smallest);
            i = smallest;
        }
    }

    void swap(int i, int j) {
        int t = heap.get(i); heap.set(i, heap.get(j)); heap.set(j, t);
    }

    public static void main(String[] args) {
        MinHeapImplementation h = new MinHeapImplementation();
        for (int x : new int[]{5, 3, 8, 1, 9, 2}) h.insert(x);
        System.out.println("peek (min): " + h.peek());      // expected: 1
        System.out.print("extract order: ");
        while (!h.heap.isEmpty()) System.out.print(h.extractMin() + " "); // expected: 1 2 3 5 8 9
        System.out.println();
    }
}
