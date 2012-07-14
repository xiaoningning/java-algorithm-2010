/**
 * Scramble string, for two strings, say str1 = “tiger” and str2 = “itreg”
 * <p/>
 * Scramble string, for two strings, say str1 = “tiger” and str2 = “itreg”
 * tiger
 * /    \
 * ti    ger
 * /  \   /   \
 * t    i  g    er
 * /  \
 * e    r
 * <p/>
 * itreg
 * /    \
 * it     reg
 * /  \   /   \
 * t    i  g    re
 * /  \
 * e    r
 * <p/>
 * In above case, since s1 can be generated from str2, that is, str1 can be generated from the partition tree,
 * which has the same leaf of str2, but the order of its child can be switched.
 * So str1 is said to be a scramble string of str2.
 * <p/>
 * A counter example is that Tiger and Tgrie are not scramble string of each other
 * <p/>
 * The principle of this approach goes as following
 * 1) Given two strings A and B, try to split each of them into two sub-strings sA1,sA2,sB1,
 * sB2 where sA1 and sB1 are anagrams and sA2 and sB2 are anagrams.
 * 2) If sA1 and sB1 are the scramble strings while sA2 and sBs are scramble strings,
 * then A and B should be scramble strings.
 * 3) Recursively analyze sA1 and sB1, sA2 and sB2 by start over from step 1).
 * <p/>
 * In previous example
 * <p/>
 * str1 = aabbbaccd, str2: aabcdbcba,
 * <p/>
 * They are split as: (aab)(bbaccd),  (aab)(cdbcba) => compare bbaccd and cdbcba
 * They are split as: (bbac)(cd),  (cd)(bcba) => compare bbac and bcba
 * They are split as: (b)(bac), b(cba) => (ba)(c) vs (c)(ba)
 */
public class ScrambleString {
    public static void main(String[] args) {

        // they are anagram as a whole string, but not scramble
        String s1 = "tiger";
        String s2 = "tgrie";

        System.out.println(isScrambleStringRecursive(s1, s2));

        // they are anagram as well as scramble
        s1 = "tiger";
        s2 = "itreg";

        System.out.println(isScrambleStringRecursive(s1, s2));

    }

    public static boolean isScrambleStringRecursive(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        if (s1.equals(s2))
            return true;
        else {

            for (int i = 0; i < s1.length() - 1; i++) {
                String s1a = s1.substring(0, i + 1);
                String s1b = s1.substring(i + 1);

                String s2a = s2.substring(0, i + 1);
                String s2b = s2.substring(i + 1);

                String s2ar = s2.substring(s2.length() - 1 - i);
                String s2br = s2.substring(0, s2.length() - 1 - i);

                if ((isScrambleStringRecursive(s1a, s2a) && isScrambleStringRecursive(s1b, s2b)) ||
                        (isScrambleStringRecursive(s1a, s2ar) && isScrambleStringRecursive(s1b, s2br))) {
                    return true;
                }

            }
            return false;
        }
    }
}
