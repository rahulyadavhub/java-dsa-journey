/*
 * Problem: Implement a Trie (Prefix Tree)
 *
 * A trie stores strings character by character in a tree, which makes prefix
 * lookups super fast. Support insert(word), search(word), and startsWith(prefix).
 *
 * Example:
 * insert("apple"); search("apple") -> true; search("app") -> false;
 * startsWith("app") -> true; insert("app"); search("app") -> true
 *
 * Approach: each node has up to 26 children (one per lowercase letter) and a flag
 *           "isEnd" marking that a word ends here. insert walks/creates nodes
 *           letter by letter and flips isEnd at the last one. search and
 *           startsWith both just walk the letters; the only difference is search
 *           also checks isEnd at the end, while startsWith only needs the path to
 *           exist. that one flag is the whole difference between the two.
 *
 * Time: O(L) per operation (L = word length)
 * Space: O(total characters inserted)
 *
 * Topics: trie, tree, strings
 */
public class TrieImplementation {

    static class Node {
        Node[] children = new Node[26];
        boolean isEnd;
    }

    Node root = new Node();

    void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (cur.children[i] == null) cur.children[i] = new Node();
            cur = cur.children[i];
        }
        cur.isEnd = true;   // a full word ends here
    }

    boolean search(String word) {
        Node node = walk(word);
        return node != null && node.isEnd;   // must be a real word end
    }

    boolean startsWith(String prefix) {
        return walk(prefix) != null;   // just needs the path to exist
    }

    // walk down following the chars, return the node we land on (or null)
    private Node walk(String s) {
        Node cur = root;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            if (cur.children[i] == null) return null;
            cur = cur.children[i];
        }
        return cur;
    }

    public static void main(String[] args) {
        TrieImplementation trie = new TrieImplementation();
        trie.insert("apple");
        System.out.println("search(apple): " + trie.search("apple"));       // true
        System.out.println("search(app): " + trie.search("app"));            // false
        System.out.println("startsWith(app): " + trie.startsWith("app"));    // true
        trie.insert("app");
        System.out.println("search(app): " + trie.search("app"));            // true
    }
}
