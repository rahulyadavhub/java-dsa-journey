/*
 * Problem: Diameter of a Binary Tree
 *
 * The diameter is the length (in edges) of the longest path between ANY two nodes.
 * That path may or may not pass through the root.
 *
 * Example tree:
 *        1
 *       / \
 *      2   3
 *     / \
 *    4   5
 * Output: 3   (path 4 -> 2 -> 5 ... actually 4->2->1->3 is length 3)
 *
 * Approach: at each node, the longest path THROUGH it = leftHeight + rightHeight
 *           (edges). so we compute heights with recursion, and while we're at it
 *           update a global best with left+right at every node. the trick is to
 *           reuse the single height-recursion to also track the diameter, instead
 *           of recomputing heights over and over (which would be O(n^2)).
 *
 * Time: O(n)
 * Space: O(h)
 *
 * Topics: binary tree, recursion, DFS
 */
public class DiameterOfBinaryTree {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static int best;

    static int diameter(Node root) {
        best = 0;
        height(root);
        return best;
    }

    // returns height in edges, and updates best (diameter) along the way
    static int height(Node n) {
        if (n == null) return 0;
        int left = height(n.left);
        int right = height(n.right);
        best = Math.max(best, left + right);   // path through this node
        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        System.out.println("Test 1: " + diameter(root)); // expected: 3

        Node two = new Node(1);
        two.left = new Node(2);
        System.out.println("Test 2: " + diameter(two));  // expected: 1
    }
}
