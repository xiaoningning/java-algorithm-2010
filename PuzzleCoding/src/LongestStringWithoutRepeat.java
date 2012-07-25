/**
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc",
 * which the length is 3.
 * For "bbbbb" the longest substring is "b", with the length of 1.
 */
public class LongestStringWithoutRepeat {
    public static void main(String[] args) {
        String s1 = "abcabcdbb";
        System.out.println(longestStringWithoutRepeat(s1));
        String s2 = "bbb";
        System.out.println(longestStringWithoutRepeat(s2));
    }

    public static String longestStringWithoutRepeat(String s) {

        boolean[] exist = new boolean[256]; //256 ASCII
        int start = 0, j = 0, maxStart = 0, maxLen = 0, n = s.length();

        while (j < n) {
            if (exist[s.charAt(j)]) {
                if (j - start > maxLen) {
                    maxLen = j - start;
                    maxStart = start;
                }

                while (s.charAt(start) != s.charAt(j)) {
                    exist[s.charAt(start)] = false;
                    start++;
                }
                j++;
                start++;
            } else {
                exist[s.charAt(j)] = true;
                j++;
            }
        }

        // the last case a[n-1] is not repeated
        if (n - start > maxLen) {
            maxLen = n - start;
            maxStart = start;
        }

        return s.substring(maxStart, maxStart + maxLen);

    }
}
