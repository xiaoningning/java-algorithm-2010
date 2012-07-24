import java.util.ArrayList;

/*
Given a set of candidate numbers (C) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … ,ak)
must be in non-descending order. (ie, a1 ? a2 ? … ? ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7,
A solution set is:
[7]
[2, 2, 3]
*/
public class CombinationSum {
    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 5, 6, 7, 10};
        int target = 10;

        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> r = new ArrayList<Integer>();
        combinationSum(a, 0, target, r, results);

        System.out.println(results.toString());

    }

    public static void combinationSum(int[] a,
                                      int index,
                                      int target,
                                      ArrayList<Integer> r,
                                      ArrayList<ArrayList<Integer>> results) {

        // find it: target == 0
        if (target == 0) {
            results.add(new ArrayList<Integer>(r));
            return;
        }
        if (a.length == index)
            return;

        combinationSum(a, index + 1, target, r, results);

        int tmp = a[index];
        int canUse = target / tmp;
        for (int i = 1; i <= canUse; ++i) {
            r.add(tmp);
            combinationSum(a, index + 1, target - tmp * i, r, results);
        }

        for (int i = 1; i <= canUse; ++i) {
            r.remove(Integer.valueOf(tmp));
        }
    }
}
