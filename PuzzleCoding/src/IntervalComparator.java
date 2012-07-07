import java.util.Comparator;

public class IntervalComparator implements Comparator<MergeIntervals> {
    public int compare(MergeIntervals in1, MergeIntervals in2) {
        if (in1.start <= in2.start && in1.end < in2.end)
            return -1;
        else if (in1.start == in2.start && in1.end == in2.end)
            return 0;
        else if (in1.start > in2.start && in1.end < in2.end)
            return 0;
        else
            return 1;
    }
}
