/*
 * Problem: Subset Sum (does a subset add up to the target?)
 *
 * Given an array and a target sum, decide if ANY subset of the array adds up to
 * exactly the target. Also prints all subsets that do.
 *
 * Example:
 * Input: arr = [3,34,4,12,5,2], target = 9
 * Output: true   (4 + 5 = 9, or 3 + 4 + 2 = 9)
 *
 * Approach: classic "pick or don't pick" recursion. for each element we branch:
 *           include it (subtract from target) or skip it. if target hits 0 we
 *           found a valid subset. this is the recursive view of the subset-sum
 *           DP problem - good to understand before doing the DP version.
 *
 * Time: O(2^n)
 * Space: O(n) recursion depth
 *
 * Topics: backtracking, recursion, subsets
 */

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {

    static boolean solve(int[] arr, int target) {
        return canMake(arr, 0, target, new ArrayList<>());
    }

    static boolean canMake(int[] arr, int i, int remain, List<Integer> picked) {
        if (remain == 0) {
            System.out.println("   found subset: " + picked);
            return true;
        }
        if (i == arr.length || remain < 0) return false;

        // option 1: pick arr[i]
        picked.add(arr[i]);
        boolean withIt = canMake(arr, i + 1, remain - arr[i], picked);
        picked.remove(picked.size() - 1);

        // option 2: skip arr[i]
        boolean withoutIt = canMake(arr, i + 1, remain, picked);

        return withIt || withoutIt;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + solve(new int[]{3,34,4,12,5,2}, 9)); // expected: true
        System.out.println("Test 2: " + solve(new int[]{1,2,3}, 7));         // expected: false
        System.out.println("Test 3: " + solve(new int[]{1,2,3}, 5));         // expected: true
    }
}
