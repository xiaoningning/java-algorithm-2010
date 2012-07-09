/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 */
public class MinWindowSubString {
    public static void main(String[] args) {
        String[] S = {"ADOBECODEBANC", "ABBADBABDRGBACCCB", "HBBHHCADBEBCAECBCCBB"};
        String[] T = {"ABC", "ABBC", "ABBC"};

        for (int i = 0; i < S.length; i++) {
            String ret = minWindowSubString(S[i], T[i]);
            System.out.println(ret);
        }
    }

    public static String minWindowSubString(String source, String test) {
        source.toUpperCase();
        test.toUpperCase();

        char[] sourceArray = source.toCharArray();
        char[] testArray = test.toCharArray();

        int sLen = source.length();
        int tLen = test.length();

        int minWindow = Integer.MAX_VALUE;
        int minEnd = 0, minStart = 0;
        int start = 0, end = 0, foundCounter = 0;

        int[] needToFind = new int[256];
        int[] hasFound = new int[256];

        for (int i = 0; i < tLen; ++i)
            needToFind[testArray[i]]++;

        for (; end < sLen; end++) {

            char endChar = sourceArray[end];

            if (needToFind[endChar] == 0)
                continue;
            hasFound[endChar]++;

            if (hasFound[endChar] <= needToFind[endChar])
                foundCounter++;

            if (foundCounter == tLen) { // found all
                char startChar = sourceArray[start];
                while (needToFind[startChar] == 0
                        || hasFound[startChar] > needToFind[startChar]) {
                    if (hasFound[startChar] > needToFind[startChar])
                        hasFound[startChar]--;
                    start++;
                    startChar = sourceArray[start];
                }

                int currentWindow = end - start + 1;
                if (currentWindow < minWindow) {
                    minWindow = currentWindow;
                    minEnd = end;
                    minStart = start;
                }

            }
        }

        return (foundCounter == tLen) ? source.substring(minStart, minEnd + 1) : "";

    }
}
