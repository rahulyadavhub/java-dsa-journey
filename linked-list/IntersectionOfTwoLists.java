/*
 * Problem: Intersection of Two Linked Lists
 *
 * Two lists may merge at some node and share the rest. Return the node where they
 * first intersect, or null.
 *
 * Example:
 * A: 4 -> 1 \
 *            8 -> 4 -> 5
 * B: 5 -> 6 -> 1 /
 * Output: the node with value 8
 *
 * Approach: cute two-pointer trick. pointer a walks list A then continues onto B;
 *           pointer b walks list B then continues onto A. after at most two passes
 *           they've both travelled lenA + lenB steps, so they line up and meet at
 *           the intersection (or both hit null together if there's none). no
 *           length counting needed.
 *
 * Time: O(m + n)
 * Space: O(1)
 *
 * Topics: linked list, two pointers
 */
public class IntersectionOfTwoLists {

    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    static Node getIntersection(Node headA, Node headB) {
        if (headA == null || headB == null) return null;
        Node a = headA, b = headB;
        while (a != b) {
            a = (a == null) ? headB : a.next;  // switch to B when A ends
            b = (b == null) ? headA : b.next;  // switch to A when B ends
        }
        return a;   // either the intersection node or null
    }

    public static void main(String[] args) {
        // build a shared tail: 8 -> 4 -> 5
        Node shared = new Node(8);
        shared.next = new Node(4);
        shared.next.next = new Node(5);

        Node a = new Node(4);
        a.next = new Node(1);
        a.next.next = shared;          // A: 4 -> 1 -> 8 -> 4 -> 5

        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(1);
        b.next.next.next = shared;     // B: 5 -> 6 -> 1 -> 8 -> 4 -> 5

        Node res = getIntersection(a, b);
        System.out.println("Test 1: " + (res == null ? "null" : res.val)); // expected: 8

        // two lists with no intersection
        Node x = new Node(1); x.next = new Node(2);
        Node y = new Node(3); y.next = new Node(4);
        Node res2 = getIntersection(x, y);
        System.out.println("Test 2: " + (res2 == null ? "null" : res2.val)); // expected: null
    }
}
