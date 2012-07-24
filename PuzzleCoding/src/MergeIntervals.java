import java.util.ArrayList;
import java.util.Collections;

/**
 * Given a set of non overlapping intervals
 * Example 1 :(1,4) (6,10) (14, 19) and another interval (13, 17) merge them as (1,4) (6,10) (13,19)
 * <p/>
 * Example 2: (1,5) (6, 15) (20, 21) (23, 26) (27, 30) (35, 40),  and the new interval is (14, 33). The
 * output should be (1,5) (6, 33) (35, 40).
 * This is because the new interval overlaps with (6, 15) (20, 21) (23, 26) (27, 30)
 * <p/>
 * Given a collection of intervals, merge all overlapping intervals.
 * <p/>
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
public class MergeIntervals {
    public int start;
    public int end;

    public MergeIntervals(int s, int e) {
        if (s < e) {
            start = s;
            end = e;
        } else {
            throw new RuntimeException("start must be < end");
        }
    }

    public static void main(String[] args) {
        ArrayList<MergeIntervals> intervals = new ArrayList<MergeIntervals>();
        intervals.add(new MergeIntervals(6, 10));
        intervals.add(new MergeIntervals(1, 4));
        intervals.add(new MergeIntervals(14, 19));
        intervals.add(new MergeIntervals(13, 17));

        Collections.sort(intervals, new IntervalComparator());
        System.out.println("original intervals");
        printIntervals(intervals);

        ArrayList<MergeIntervals> result = mergeIntervals(intervals);
        System.out.println("merged intervals");
        printIntervals(result);
    }

    public static ArrayList<MergeIntervals> mergeIntervals(ArrayList<MergeIntervals> intervalsArrayList) {
        if (intervalsArrayList.size() <= 1) return intervalsArrayList;

        ArrayList<MergeIntervals> result = new ArrayList<MergeIntervals>();
        result.add(0, intervalsArrayList.get(0));

        for (int i = 1; i < intervalsArrayList.size(); i++) {
            MergeIntervals prev = result.get(0);
            MergeIntervals curr = intervalsArrayList.get(i);

            if (prev.end < curr.start) {
                result.add(0, curr);
            } else {
                if (prev.end < curr.end)
                    prev.end = curr.end;
                if (prev.start > curr.start)
                    prev.start = curr.start;
                result.remove(0);
                result.add(0, prev);
            }
        }

        return result;
    }

    public static void printIntervals(ArrayList<MergeIntervals> intervalsArrayList) {
        for (int i = 0; i < intervalsArrayList.size(); ++i) {
            MergeIntervals in = intervalsArrayList.get(i);
            System.out.println("(" + in.start + "," + in.end + ")");
        }
    }


}

