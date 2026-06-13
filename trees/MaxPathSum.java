/*
 * Problem: Binary Tree Maximum Path Sum
 *
 * A "path" is any sequence of connected nodes (it doesn't have to go through the
 * root and doesn't have to touch leaves). Return the maximum possible sum of node
 * values along any path.
 *
 * Example:
 *      -10
 *      /  \
 *     9   20
 *        /  \
 *       15   7
 * Output: 42   (15 -> 20 -> 7)
 *
 * Approach: similar idea to the diameter problem. for each node, the best path
 *           that PASSES THROUGH it = node.val + bestLeftGain + bestRightGain. we
 *           track that in a global max. BUT what we RETURN upward is node.val +
 *           max(leftGain, rightGain), because a parent can only attach to one side
 *           of us (a path can't fork). also clamp negative gains to 0 - if a
 *           subtree only hurts, just don't take it. that clamp is the subtle part.
 *
 * Time: O(n)
 * Space: O(h)
 *
 * Topics: binary tree, recursion, DFS
 */
public class MaxPathSum {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static int best;

    static int maxPathSum(Node root) {
        best = Integer.MIN_VALUE;
        gain(root);
        return best;
    }

    // max sum of a downward path starting at n (going into at most one child)
    static int gain(Node n) {
        if (n == null) return 0;
        int left = Math.max(0, gain(n.left));    // drop negative branches
        int right = Math.max(0, gain(n.right));
        best = Math.max(best, n.val + left + right);  // path that bends at n
        return n.val + Math.max(left, right);          // parent can use one side only
    }

    public static void main(String[] args) {
        Node root = new Node(-10);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);
        System.out.println("Test 1: " + maxPathSum(root)); // expected: 42

        Node two = new Node(1);
        two.left = new Node(2);
        two.right = new Node(3);
        System.out.println("Test 2: " + maxPathSum(two));  // expected: 6
    }
}
