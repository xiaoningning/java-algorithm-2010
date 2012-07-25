import java.util.HashMap;

/**
 * Problem: Given a string which contains only 1 and 0,
 * find out its longest sub-string that contains equal number of “1” and “0”.
 * It should be done in O(n) time, O(n) space.
 * <p/>
 * Example: “111100? => 1100, “00110? => 0110, “001111111000? => 111000.
 * <p/>
 * We can use an array index to store the difference
 * between the number of 1s and the number of 0s up to each character in string S.
 * That is,we create an array diff with size equals to S.length + 1.
 * diff[i] is the number of “1”s appeared up to the ith character in S
 * subtracts the number of “0”s appeared up to ith character in S.
 * <p/>
 * To be more specific, we have this counter initially set to 0,
 * if we meet a “1”, then counter increases by 1.
 * Otherwise the counter decreases by 1.
 * How long is the longest “1 and 0” balanced substring?
 * That is the longest distance between two identical counter in diff.
 * Because the counters are the same, they should meet same number of “1”s and “0”s in between.
 */
public class LongestBalanceSubstring {
    public static void main(String[] args) {
        String s = "1001110010001";
        String balanceString = longestBalanceSubstring(s);
        System.out.println(balanceString);

        String s1 = "11010";
        String balanceString1 = longestBalanceSubstring(s1);
        System.out.println(balanceString1);

    }

    public static String longestBalanceSubstring(String s) {
        if (s.length() <= 0)
            return "";
        int[] diffCounter = new int[s.length() + 1];
        diffCounter[0] = 0;
        for (int i = 0; i < s.length(); i++) {
            diffCounter[i + 1] = diffCounter[i] + ((s.charAt(i) == '0') ? -1 : 1);
        }
        HashMap<Integer, Integer> counterIndex = new HashMap<Integer, Integer>();
        for (int i = 1, j = diffCounter.length - 1; i < j; ) {
            if (!counterIndex.containsKey(diffCounter[i])) {
                counterIndex.put(diffCounter[i], i - 1);
                i++;
            } else {
                return s.substring(i, j);
            }
            if (!counterIndex.containsKey(diffCounter[j])) {
                counterIndex.put(diffCounter[j], j - 1);
                j--;
            } else {
                return s.substring(i - 1, j);
            }
        }
        return "";
    }
}
