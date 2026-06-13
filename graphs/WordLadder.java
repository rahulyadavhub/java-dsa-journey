/*
 * Problem: Word Ladder
 *
 * Transform beginWord into endWord changing ONE letter at a time, where every
 * intermediate word must be in the given word list. Return the length of the
 * shortest such transformation sequence (number of words), or 0 if impossible.
 *
 * Example:
 * begin = "hit", end = "cog", words = ["hot","dot","dog","lot","log","cog"]
 * hit -> hot -> dot -> dog -> cog  => length 5
 *
 * Approach: think of each word as a node; two words are connected if they differ
 *           by exactly one letter. shortest path on an unweighted graph = BFS.
 *           from a word, generate all one-letter-changed neighbours, and if a
 *           neighbour is in the word set, it's reachable. BFS level count gives
 *           the shortest length. removing words from the set as we visit avoids
 *           revisiting.
 *
 * Time: O(N * L * 26)  N words, L word length
 * Space: O(N)
 *
 * Topics: graph, BFS, shortest path, strings
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

    static int ladderLength(String begin, String end, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(end)) return 0;

        Queue<String> q = new LinkedList<>();
        q.add(begin);
        int level = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String word = q.poll();
                if (word.equals(end)) return level;

                // try changing each position to every letter a-z
                char[] arr = word.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    char original = arr[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        arr[j] = c;
                        String next = new String(arr);
                        if (words.contains(next)) {
                            words.remove(next);   // mark visited
                            q.add(next);
                        }
                    }
                    arr[j] = original;   // restore for the next position
                }
            }
            level++;
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> words = java.util.Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println("Test 1: " + ladderLength("hit", "cog", words)); // expected: 5

        List<String> words2 = java.util.Arrays.asList("hot","dot","dog","lot","log");
        System.out.println("Test 2: " + ladderLength("hit", "cog", words2)); // expected: 0
    }
}
