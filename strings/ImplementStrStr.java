/*
 * Problem: Implement strStr() / Find the Index of First Occurrence
 *
 * Return the index of the first time needle shows up in haystack, or -1.
 *
 * Example:
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 *
 * Approach: the easy O(n*m) way is to try matching needle at every start index.
 *           but the real lesson here is KMP, which is O(n+m). KMP precomputes an
 *           "lps" array (longest proper prefix that's also a suffix) for the
 *           needle, so when a mismatch happens we don't restart from scratch -
 *           we jump back using lps and reuse the part we already matched.
 *           google "KMP algorithm" + watch a visualization, that's how it clicked for me.
 *
 * Time: O(n + m)
 * Space: O(m)
 *
 * Topics: string, pattern matching, KMP
 */
public class ImplementStrStr {

    static int solve(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int[] lps = buildLps(needle);

        int i = 0, j = 0; // i walks haystack, j walks needle
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == needle.length()) return i - j; // matched the whole needle
            } else if (j > 0) {
                j = lps[j - 1];   // fall back, don't reset to 0
            } else {
                i++;
            }
        }
        return -1;
    }

    // lps[k] = length of the longest prefix of needle[0..k] that is also a suffix
    static int[] buildLps(String p) {
        int[] lps = new int[p.length()];
        int len = 0, i = 1;
        while (i < p.length()) {
            if (p.charAt(i) == p.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("sadbutsad", "sad"));  // expected: 0
        System.out.println("Test 2: " + solve("leetcode", "leeto")); // expected: -1
        System.out.println("Test 3: " + solve("aaaaab", "aab"));     // expected: 3
    }
}
