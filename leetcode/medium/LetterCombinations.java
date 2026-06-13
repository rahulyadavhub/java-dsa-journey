/*
 * Problem: Letter Combinations of a Phone Number  (LeetCode #17 - Medium)
 *
 * Given digits 2-9, return all letter combos they could spell (old phone keypad,
 * 2=abc, 3=def, ...).
 *
 * Example:
 * Input: "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Approach: backtracking. process the digits one at a time; for the current digit,
 *           try each of its letters, append it, recurse to the next digit, then
 *           remove it (backtrack) and try the next letter. when we've placed a
 *           letter for every digit, we've built one full combination.
 *
 * Time: O(4^n) in the worst case (digits 7 and 9 have 4 letters)
 * Space: O(n) recursion depth
 *
 * Topics: backtracking, strings
 */

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {

    static String[] keypad = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    static List<String> solve(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) return res;
        build(digits, 0, new StringBuilder(), res);
        return res;
    }

    static void build(String digits, int idx, StringBuilder cur, List<String> res) {
        if (idx == digits.length()) {
            res.add(cur.toString());
            return;
        }
        String letters = keypad[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            cur.append(c);
            build(digits, idx + 1, cur, res);
            cur.deleteCharAt(cur.length() - 1);   // backtrack
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("23"));
        // expected: [ad, ae, af, bd, be, bf, cd, ce, cf]
        System.out.println("Test 2: " + solve(""));   // expected: []
        System.out.println("Test 3: " + solve("2"));  // expected: [a, b, c]
    }
}
