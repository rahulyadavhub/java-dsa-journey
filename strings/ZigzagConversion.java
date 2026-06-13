/*
 * Problem: Zigzag Conversion
 *
 * Write the string in a zigzag pattern across numRows rows, then read it row
 * by row.
 *
 * Example:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * The zigzag looks like:
 *   P   A   H   N
 *   A P L S I I G
 *   Y   I   R
 * Output: "PAHNAPLSIIGYIR"
 *
 * Approach: don't actually build the 2D grid. just keep one StringBuilder per
 *           row. walk the string and drop each char into the current row. bounce
 *           the row index up and down (flip direction when you hit top or bottom).
 *           then glue all the rows together.
 *
 * Time: O(n)
 * Space: O(n)
 *
 * Topics: string, simulation
 */
public class ZigzagConversion {

    static String solve(String s, int numRows) {
        if (numRows == 1) return s;   // edge case: no zigzag possible

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

        int row = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            rows[row].append(c);
            if (row == 0 || row == numRows - 1) goingDown = !goingDown; // bounce
            row += goingDown ? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder r : rows) res.append(r);
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve("PAYPALISHIRING", 3)); // expected: PAHNAPLSIIGYIR
        System.out.println("Test 2: " + solve("PAYPALISHIRING", 4)); // expected: PINALSIGYAHRPI
        System.out.println("Test 3: " + solve("A", 1));              // expected: A
    }
}
