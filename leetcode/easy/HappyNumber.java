/*
 * Problem: Happy Number  (LeetCode #202 - Easy)
 *
 * Repeatedly replace a number with the sum of the squares of its digits. If it
 * eventually reaches 1, it's "happy". If it loops forever (never hits 1), not happy.
 *
 * Example:
 * Input: 19
 * Output: true   (1+81=82 -> 68 -> 100 -> 1)
 *
 * Approach: the danger is the infinite loop for unhappy numbers. classic fix: this
 *           is really cycle detection! use Floyd's slow/fast pointers. slow does one
 *           "digit-square-sum" step, fast does two. if they meet at a value other
 *           than 1, there's a cycle -> not happy. (a HashSet of seen numbers also
 *           works and is easier to read, but the slow/fast version is O(1) space.)
 *
 * Time: O(log n) per step
 * Space: O(1)
 *
 * Topics: math, cycle detection, Floyd's
 */
public class HappyNumber {

    static int next(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }

    static boolean solve(int n) {
        int slow = n, fast = n;
        do {
            slow = next(slow);          // one step
            fast = next(next(fast));    // two steps
        } while (slow != fast);
        return slow == 1;               // met at 1 -> happy, else stuck in a cycle
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(19)); // expected: true
        System.out.println("Test 2: " + solve(2));  // expected: false
        System.out.println("Test 3: " + solve(1));  // expected: true
    }
}
