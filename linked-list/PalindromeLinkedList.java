/*
 * Problem: Palindrome Linked List
 *
 * Return true if the list reads the same forwards and backwards.
 *
 * Example:
 * Input:  1 -> 2 -> 2 -> 1
 * Output: true
 *
 * Approach: the O(n) space way is dump values into an array and check. the O(1)
 *           space way (this one): find the middle with slow/fast, reverse the
 *           second half, then walk the first half and the reversed second half
 *           in lockstep comparing values. if all match, it's a palindrome.
 *           (proper solution would restore the list after; skipping that here.)
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: linked list, two pointers, reversal
 */
public class PalindromeLinkedList {

    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        // find middle
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second half starting at slow
        Node prev = null;
        while (slow != null) {
            Node next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // compare first half with reversed second half
        Node left = head, right = prev;
        while (right != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }

    static Node build(int[] vals) {
        Node dummy = new Node(0), tail = dummy;
        for (int v : vals) { tail.next = new Node(v); tail = tail.next; }
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + isPalindrome(build(new int[]{1,2,2,1})));   // expected: true
        System.out.println("Test 2: " + isPalindrome(build(new int[]{1,2,3,2,1}))); // expected: true
        System.out.println("Test 3: " + isPalindrome(build(new int[]{1,2})));       // expected: false
    }
}
