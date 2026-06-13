/*
 * Problem: Validate Binary Search Tree
 *
 * Return true if a binary tree is a valid BST: every node's value must be greater
 * than ALL values in its left subtree and less than ALL in its right subtree.
 *
 * Example:
 *      2
 *     / \
 *    1   3     -> valid (true)
 *
 *      5
 *     / \
 *    1   4     -> invalid (false), because 3 is in the right subtree of 5 but < 5
 *       / \
 *      3   6
 *
 * Approach: the common mistake is to only compare a node with its direct children
 *           - that's not enough (see the second example). instead pass down a
 *           valid (min, max) range. each node must fall strictly inside its range,
 *           and we tighten the range as we go down: left child gets max = node,
 *           right child gets min = node. use long bounds to dodge Integer overflow.
 *
 * Time: O(n)
 * Space: O(h)
 *
 * Topics: BST, recursion, DFS
 */
public class ValidateBST {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static boolean isValid(Node root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    static boolean check(Node n, long min, long max) {
        if (n == null) return true;
        if (n.val <= min || n.val >= max) return false;   // out of allowed range
        return check(n.left, min, n.val) && check(n.right, n.val, max);
    }

    public static void main(String[] args) {
        Node valid = new Node(2);
        valid.left = new Node(1);
        valid.right = new Node(3);
        System.out.println("Test 1: " + isValid(valid)); // expected: true

        Node invalid = new Node(5);
        invalid.left = new Node(1);
        invalid.right = new Node(4);
        invalid.right.left = new Node(3);
        invalid.right.right = new Node(6);
        System.out.println("Test 2: " + isValid(invalid)); // expected: false
    }
}
