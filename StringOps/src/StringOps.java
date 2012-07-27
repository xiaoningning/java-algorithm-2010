import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class StringOps {
    public static void main(String[] args) {
        String s1 = "aaadbscddddedee";
        System.out.println("s1: " + s1);
        String lrs = LRS(s1);
        System.out.println("LRS: " + lrs);
        String lrs1 = LRS1(s1);
        System.out.println("LRS1: " + lrs1);

        String s2 = "ddaacddddess";
        System.out.println("s2: " + s2);
        String lcs = LCS(s1, s2);
        System.out.println("LCS: " + lcs);

        String lcs1 = LCS1(s1, s2);
        System.out.println("LCS1: " + lcs1);

        String s3 = "abcdefgh";
        System.out.println("s3: " + s3);
        int k = 3;
        String rs3 = rotateString(s3, k);
        System.out.println("rotate " + s3 + " in " + k + " : " + rs3);

        String s4 = "abc";
        String s5 = "a*b.";
        System.out.println(s4 + " : " + s5 + " : " + isRexMatch(s4, s5));

        s5 = "a*b*.*";
        System.out.println(s4 + " : " + s5 + " full lenght match : " + isRexMatch1(s4, s5));

        System.out.println("perm s4: " + s4);
        perm("", s4);

        System.out.println("perm1 s4: " + s4);
        perm1(s4.toCharArray(), s4.length());

        String s6 = "abcd";
        System.out.println("move s6: " + s6);
        move(s6.toCharArray(), s6.length(), new ArrayList<String>(), true);

        HashMap<Integer, String> phoneDict = new HashMap<Integer, String>();
        phoneDict.put(2, "abc");
        phoneDict.put(3, "def");
        phoneDict.put(4, "ghi");
        phoneDict.put(5, "gkl");
        phoneDict.put(6, "mno");
        phoneDict.put(7, "pqrs");
        phoneDict.put(8, "tuv");
        phoneDict.put(9, "wxyz");

        System.out.println("Generate words from phone pad");
        generateWordsFromPhone("24", "", phoneDict);

    }

    public static void generateWordsFromPhone(String digits, String prefix, HashMap<Integer, String> phoneDict) {
        if (digits.length() == 0) {
            System.out.println(prefix);
            return;
        }

        String dict = phoneDict.get(Integer.valueOf(digits.substring(0, 1)));
        String rest = digits.substring(1);

        for (int i = 0; i < dict.length(); i++) {
            generateWordsFromPhone(rest, prefix + dict.substring(i, i + 1), phoneDict);

        }
    }

    // longest repeated string
    public static String LRS(String s) {
        StringBuilder sb1 = new StringBuilder();
        String sb2 = "";     // it is a holder for LRS
        char[] chars = s.toCharArray();
        sb1.append(chars[0]);

        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == chars[i]) {
                sb1.append(chars[i]);
            } else {
                if (sb1.length() > sb2.length()) {
                    sb2 = sb1.toString();
                }
                sb1 = new StringBuilder();
                sb1.append(chars[i]);
            }
        }
        return sb2;

    }

    public static String LRS1(String st) {
        char[] chars = st.toCharArray();
        int i = 0, s = 0, e = 0, j = i;
        while (i < chars.length) {

            if (j - i >= e - s) {
                s = i;
                e = j;
            }

            i = j;

            for (j = i; j < chars.length; j++) {
                if (chars[i] != chars[j]) break;
            }
        }
        char[] lrs = Arrays.copyOfRange(chars, s, e);
        return new String(lrs);
    }

    // longest common prefix
    public static String LCP(String s1, String s2) {
        int n = Math.min(s1.length(), s2.length());
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i))
                return s1.substring(0, i);
        }
        return s1;
    }
    // longest common string
    public static String LCS(String x, String y) {
        int N = x.length();
        int M = y.length();
        String[] suffixes = new String[M + N];
        for (int i = 0; i < N; i++) {
            suffixes[i] = x.substring(i);
        }
        for (int i = 0; i < M; i++) {
            suffixes[N + i] = y.substring(i);
        }
        Arrays.sort(suffixes);

        String lrs = "";
        for (int i = 0; i < suffixes.length - 1; i++) {
            String lcps = LCP(suffixes[i], suffixes[i + 1]);
            if (lcps.length() > lrs.length())
                lrs = lcps;
        }
        return lrs;
    }

    //back-track
    public static String LCS1(String x, String y) {
        int[][] lcs = new int[x.length()][y.length()];  // all 0

        int max = 0;
        int end = 0;

        for (int i = 0; i < x.length(); i++) {
            for (int j = 0; j < y.length(); j++) {
                if (x.charAt(i) == y.charAt(j)) {
                    if (i == 0 || j == 0)
                        lcs[i][j] = 1;
                    else
                        lcs[i][j] = lcs[i - 1][j - 1] + 1;

                    if (lcs[i][j] > max) {
                        max = lcs[i][j];
                        end = i;
                    }

                }

            }
        }

        return x.substring(end - max + 1, end + 1);

    }

    public static String reverse(String st, int s, int e) {
        if (s < 0 || e > st.length())
            return st;
        char[] chars = st.toCharArray();
        while (s < e) {
            char tmp = chars[s];
            chars[s] = chars[e];
            chars[e] = tmp;
            s++;
            e--;
        }
        return new String(chars);
    }

    public static String rotateString(String s, int k) {
        int n = s.length();
        /*
        String rs1 = reverse(s, 0, n - 1);
        String rs2 = reverse(rs1, 0, n-k-1);
        String rs3 = reverse(rs2, n-k, n - 1);
        */
        String rs1 = reverse(s, 0, k-1);
        String rs2 = reverse(rs1, k, n-1);
        String rs3 = reverse(rs2, 0, n - 1);

        return rs3;

    }

    public static boolean isRexMatch(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s2.isEmpty()) return s2.isEmpty();    // "" , ""


        if (s2.length() == 1) {
            if (s2.charAt(0) == s1.charAt(0) || s2.charAt(0) == '.' || s2.charAt(0) == '*') {
                return true;
            } else
                return false;
        }

        if (s2.length() >= 2) {
            if (s2.charAt(1) != '*') {

                if (s1.charAt(0) == s2.charAt(0) || s2.charAt(0) == '.') {
                    if (s1.length() > 1)
                        return isRexMatch(s1.substring(1), s2.substring(1));
                    else
                        return true;
                } else
                    return false;
            } else { // s2.charAt(1) == '*'
                if ((s2.charAt(0) == '.') || (s1.charAt(0) == s2.charAt(0))) {

                    if (s1.length() == 1)
                        return true;
                    else if (s2.length() > 2)
                        return isRexMatch(s1, s2.substring(2)) || isRexMatch(s1.substring(1), s2.substring(2));
                    else //s2.length() == 2
                        return true;
                } else
                    return false;
            }
        }

        return isRexMatch(s1.substring(1), s2.substring(2));
    }

    public static boolean isRexMatch1(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s2.isEmpty()) return s2.isEmpty();    // "" , ""


        if (s2.length() == 1) {
            if ((s2.charAt(0) == s1.charAt(0) || s2.charAt(0) == '.') && (s1.length() == 1)) {
                return true;
            } else
                return false;
        }

        if (s2.length() >= 2) {
            if (s2.charAt(1) != '*') {

                if (s1.charAt(0) == s2.charAt(0) || s2.charAt(0) == '.') {
                    if (s1.length() > 1)
                        return isRexMatch1(s1.substring(1), s2.substring(1));
                    else
                        return false;
                } else
                    return false;
            } else { // s2.charAt(1) == '*'
                if ((s2.charAt(0) == '.') || (s1.charAt(0) == s2.charAt(0))) {

                    if (s1.length() == 1)
                        if (s2.length() == 2)
                            return true;
                        else
                            return false;
                    else // (s1.length() > 1)
                        if (s2.length() > 2)
                            return isRexMatch1(s1, s2.substring(2)) || isRexMatch1(s1.substring(1), s2.substring(2));
                        else // s2.length() == 2
                            return isRexMatch1(s1.substring(1), s2);


                } else
                    return false;
            }
        }

        return isRexMatch1(s1.substring(1), s2.substring(2));
    }

    public static void perm(String prefix, String s) {
        int N = s.length();
        if (N == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < N; i++)
            perm(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1));

    }

    public static void swap(char[] a, int s, int e) {
        char tmp = a[s];
        a[s] = a[e];
        a[e] = tmp;

    }

    // n = s.length -1
    public static void perm1(char[] s, int n) {
        if (n == 0) {
            System.out.println(s);
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(s, i, n - 1);
            perm1(s, n - 1);
            swap(s, i, n - 1);
        }
    }

    // all combinations
    // n = s.length
    public static void move(char[] s, int n, ArrayList<String> t, boolean pick) {
        if (n == 0) {
            System.out.println(t.toString());
            return;
        }
        move(s, n - 1, t, true);
        if (pick) {
            t.add(String.valueOf(s[n - 1]));
        } else {
            t.remove(String.valueOf(s[n - 1]));
        }

        move(s, n - 1, t, false);

    }

}
