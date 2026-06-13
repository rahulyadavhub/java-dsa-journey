/*
 * Problem: Roman to Integer  (LeetCode #13 - Easy)
 *
 * Convert a Roman numeral string to an integer.
 *
 * Example:
 * Input: "MCMXCIV"
 * Output: 1994   (M=1000, CM=900, XC=90, IV=4)
 *
 * Approach: normally you add up the symbol values. the only twist is subtractive
 *           pairs like IV (4) or CM (900), where a smaller symbol sits BEFORE a
 *           bigger one. trick: walk left to right, and if the current symbol's value
 *           is smaller than the NEXT one, subtract it instead of adding. that one
 *           rule handles all the subtractive cases cleanly.
 *
 * Time: O(n)
 * Space: O(1)
 *
 * Topics: string, hashmap, math
 */

import java.util.Map;

public class RomanToInteger {

    static int solve(String s) {
        Map<Character, Integer> val = Map.of(
            'I', 1, 'V', 5, 'X', 10, 'L', 50,
            'C', 100, 'D', 500, 'M', 1000);

        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int cur = val.get(s.charAt(i));
            // if a bigger symbol follows, this one is subtractive
            if (i + 1 < s.length() && cur < val.get(s.charAt(i + 1))) total -= cur;
            else total += cur;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("MCMXCIV")); // expected: 1994
        System.out.println("Test 2: " + solve("LVIII"));   // expected: 58
        System.out.println("Test 3: " + solve("IV"));      // expected: 4
    }
}
