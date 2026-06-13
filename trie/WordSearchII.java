/*
 * Problem: Word Search II
 *
 * Given a board of letters and a list of words, return all words that can be
 * formed by walking adjacent cells (up/down/left/right) without reusing a cell.
 *
 * Example:
 * board = [[o,a,a,n],[e,t,a,e],[i,h,k,r],[i,f,l,v]]
 * words = ["oath","pea","eat","rain"]
 * Output: ["oath","eat"]
 *
 * Approach: doing a separate DFS per word would be slow. instead, put ALL the
 *           words into a trie, then DFS the board ONCE. as we move on the board we
 *           also walk down the trie - if the current letter isn't a trie child we
 *           prune immediately (the big speedup). when we land on a trie node marked
 *           as a word end, we found one. trie + DFS together is the trick.
 *
 * Time: roughly O(cells * 4^maxWordLen) but pruned hard by the trie
 * Space: O(total chars in words)
 *
 * Topics: trie, DFS, backtracking, matrix
 */

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {

    static class Node {
        Node[] children = new Node[26];
        String word;   // non-null at a node where a word ends (stores the word)
    }

    static Node buildTrie(String[] words) {
        Node root = new Node();
        for (String w : words) {
            Node cur = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (cur.children[i] == null) cur.children[i] = new Node();
                cur = cur.children[i];
            }
            cur.word = w;
        }
        return root;
    }

    static List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        Node root = buildTrie(words);
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++)
                dfs(board, r, c, root, res);
        return res;
    }

    static void dfs(char[][] board, int r, int c, Node node, List<String> res) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) return;
        char ch = board[r][c];
        if (ch == '#') return;                 // already used on this path
        Node next = node.children[ch - 'a'];
        if (next == null) return;              // prune: no word goes this way

        if (next.word != null) {
            res.add(next.word);
            next.word = null;                  // avoid adding the same word twice
        }

        board[r][c] = '#';                      // mark used
        dfs(board, r + 1, c, next, res);
        dfs(board, r - 1, c, next, res);
        dfs(board, r, c + 1, next, res);
        dfs(board, r, c - 1, next, res);
        board[r][c] = ch;                       // restore
    }

    public static void main(String[] args) {
        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        System.out.println("Test 1: " + findWords(board, words)); // expected: [oath, eat]
    }
}
