/*
 * Problem: Serialize and Deserialize Binary Tree
 *
 * Serialize: turn a tree into a string. Deserialize: rebuild the exact same tree
 * from that string. Must round-trip perfectly.
 *
 * Example:
 *      1
 *     / \
 *    2   3
 *       / \
 *      4   5
 * serialized (preorder, # = null): "1,2,#,#,3,4,#,#,5,#,#"
 *
 * Approach: do a preorder DFS and write each node's value, using a marker like
 *           "#" for null children. that null marker is what makes the string
 *           unambiguous to rebuild. to deserialize, read tokens in the same
 *           preorder order: read a value -> make a node -> recursively build its
 *           left then right from the following tokens. a shared index/queue keeps
 *           our place in the token stream.
 *
 * Time: O(n) both ways
 * Space: O(n)
 *
 * Topics: binary tree, DFS, preorder, design
 */

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBinaryTree {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        build(root, sb);
        return sb.toString();
    }

    static void build(Node n, StringBuilder sb) {
        if (n == null) { sb.append("#,"); return; }
        sb.append(n.val).append(",");
        build(n.left, sb);
        build(n.right, sb);
    }

    static Node deserialize(String data) {
        Queue<String> tokens = new LinkedList<>();
        for (String t : data.split(",")) tokens.add(t);
        return rebuild(tokens);
    }

    static Node rebuild(Queue<String> tokens) {
        String t = tokens.poll();
        if (t.equals("#")) return null;
        Node n = new Node(Integer.parseInt(t));
        n.left = rebuild(tokens);    // same preorder order we wrote them
        n.right = rebuild(tokens);
        return n;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);

        String s = serialize(root);
        System.out.println("serialized: " + s); // 1,2,#,#,3,4,#,#,5,#,#,

        Node rebuilt = deserialize(s);
        System.out.println("round-trip equal: " + serialize(rebuilt).equals(s)); // expected: true
    }
}
