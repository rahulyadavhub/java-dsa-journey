/*
 * Problem: Permutations
 *
 * Return all possible orderings of the numbers in an array (all distinct).
 *
 * Example:
 * Input: [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Approach: backtracking. build a permutation by picking an unused number,
 *           recursing, then un-picking it. a boolean[] used tracks which numbers
 *           are already in the current arrangement. when the current list is full
 *           we found one permutation.
 *
 * Time: O(n * n!)
 * Space: O(n)
 *
 * Topics: backtracking, recursion, array
 */

import java.util.ArrayList;
import java.util.List;

public class PermutationsOfArray {

    static List<List<Integer>> solve(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        build(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    static void build(List<List<Integer>> res, List<Integer> cur, int[] nums, boolean[] used) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));   // copy, because we'll keep mutating cur
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;           // already in this permutation
            used[i] = true;
            cur.add(nums[i]);
            build(res, cur, nums, used);
            cur.remove(cur.size() - 1);      // undo
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{1,2,3}));
        // expected: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        System.out.println("Test 2: " + solve(new int[]{0,1})); // expected: [[0,1],[1,0]]
    }
}
