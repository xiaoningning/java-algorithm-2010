/**
 * An O(N) Solution (Manacher’s Algorithm):
 * S = “abaaba”, T = “#a#b#a#a#b#a#”.
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String s = "cabaaba";
        System.out.println(s);

        String longestPalindrome = longestPalindrome(s);
        System.out.println(longestPalindrome);
        String longestPalindrome1 = longestPalindrome1(s);
        System.out.println(longestPalindrome1);
    }

    public static String preProcess(String s) {
        String T = "";
        for (int i = 0; i < s.length(); ++i)
            T += "#" + s.substring(i, i + 1);

        T += "#";
        return T;
    }

    public static String longestPalindrome(String s) {
        String T = preProcess(s);
        int N = T.length();
        int[] p = new int[N]; // record the range
        int center = 1; // the center
        int R = 0; // the range, not including itself

        for (int i = 1; i < N; ++i) {
            int i_mirror = center - (i - center);
            p[i] = (R > i) ? Math.min(R - i, p[i_mirror]) : 0;

            while (i - 1 - p[i] >= 0 && i + 1 + p[i] < N) {
                if (T.charAt(i + 1 + p[i]) == T.charAt(i - 1 - p[i]))
                    p[i]++;
                else
                    break;
            }

            if (i + p[i] > R) {
                R = i + p[i];
                center = i;
            }
        }

        int maxLength = 0;
        int centerIndex = 0;
        for (int i = 1; i < N - 1; ++i) {
            if (p[i] > maxLength) {
                maxLength = p[i];
                centerIndex = i;
            }
        }

        int start = (centerIndex - maxLength) / 2;
        return s.substring(start, start + maxLength);

    }

    public static String longestPalindrome1(String s){
        String longPalindromicString = s.substring(0, 1);

        for (int i = 0; i < s.length(); i++) {
            String l1 = getCenterAroundString(s, i, i);
            if (l1.length() > longPalindromicString.length())
                longPalindromicString = l1;
            String l2 = getCenterAroundString(s, i, i + 1);
            if (l2.length() > longPalindromicString.length())
                longPalindromicString = l2;
        }
        return longPalindromicString;
    }

    public static String getCenterAroundString(String s, int left, int right) {

        while (left >= 0 && right < s.length()) {
            if (s.charAt(right) == s.charAt(left)) {
                right++;
                left--;
            } else
                break;
        }
        //left = (left < 0) ? 0 : left + 1;
        left = left+1;
        return s.substring(left, right);


    }
}

