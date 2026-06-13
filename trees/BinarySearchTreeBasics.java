/*
 * Problem: Binary Search Tree Basics
 *
 * Build a BST and support insert, search, and delete. In a BST every left child
 * is smaller than its parent and every right child is bigger, which makes lookups
 * fast (you only go one direction at each step).
 *
 * Approach:
 *   - search/insert: compare with the node, go left if smaller, right if bigger.
 *   - delete is the tricky one. three cases:
 *       1) leaf node          -> just remove it
 *       2) one child          -> replace node with that child
 *       3) two children       -> find the smallest node in the right subtree
 *                                (the inorder successor), copy its value here,
 *                                then delete that successor. case 3 tripped me up
 *                                the first time.
 *
 * Time: O(h) per op (O(log n) balanced, O(n) worst)
 * Space: O(h) recursion
 *
 * Topics: BST, recursion
 */

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeBasics {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static Node insert(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.val) root.left = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);
        return root;   // ignore duplicates
    }

    static boolean search(Node root, int val) {
        if (root == null) return false;
        if (val == root.val) return true;
        return val < root.val ? search(root.left, val) : search(root.right, val);
    }

    static Node delete(Node root, int val) {
        if (root == null) return null;
        if (val < root.val) root.left = delete(root.left, val);
        else if (val > root.val) root.right = delete(root.right, val);
        else {
            // found the node to delete
            if (root.left == null) return root.right;   // case 1 & 2
            if (root.right == null) return root.left;
            // case 3: two children -> inorder successor (min of right subtree)
            Node succ = root.right;
            while (succ.left != null) succ = succ.left;
            root.val = succ.val;
            root.right = delete(root.right, succ.val);
        }
        return root;
    }

    static void inorder(Node n, List<Integer> out) {
        if (n == null) return;
        inorder(n.left, out);
        out.add(n.val);
        inorder(n.right, out);
    }

    public static void main(String[] args) {
        Node root = null;
        for (int v : new int[]{50, 30, 70, 20, 40, 60, 80}) root = insert(root, v);

        List<Integer> in = new ArrayList<>(); inorder(root, in);
        System.out.println("inorder (sorted): " + in);  // expected: [20, 30, 40, 50, 60, 70, 80]
        System.out.println("search(60): " + search(root, 60)); // expected: true
        System.out.println("search(45): " + search(root, 45)); // expected: false

        root = delete(root, 30);   // node with two children
        List<Integer> in2 = new ArrayList<>(); inorder(root, in2);
        System.out.println("after delete(30): " + in2);  // expected: [20, 40, 50, 60, 70, 80]
    }
}
