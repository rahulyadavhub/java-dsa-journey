/*
 * Problem: Maximum Depth / Height of a Binary Tree
 *
 * Return the height = number of nodes along the longest path from root down to a
 * leaf.
 *
 * Example tree:
 *        3
 *       / \
 *      9  20
 *        /  \
 *       15   7
 * Output: 3
 *
 * Approach: easy recursion. an empty tree has height 0. otherwise the height is
 *           1 (for the current node) plus the taller of the left and right
 *           subtree heights. that's literally the whole solution.
 *
 * Time: O(n)
 * Space: O(h)
 *
 * Topics: binary tree, recursion, DFS
 */
public class HeightOfBinaryTree {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static int height(Node n) {
        if (n == null) return 0;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);
        System.out.println("Test 1: " + height(root)); // expected: 3
        System.out.println("Test 2: " + height(null)); // expected: 0

        Node single = new Node(1);
        System.out.println("Test 3: " + height(single)); // expected: 1
    }
}
