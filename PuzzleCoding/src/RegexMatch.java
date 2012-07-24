/**
 * implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * <p/>
 * The matching should cover the entire input string (not partial).
 * <p/>
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * <p/>
 * Some examples:
 * isMatch("aa","a") ? false
 * isMatch("aa","aa") ? true
 * isMatch("aaa","aa") ? false
 * isMatch("aa", "a*") ? true
 * isMatch("aa", ".*") ? true
 * isMatch("ab", ".*") ? true
 * isMatch("aab", "c*a*b") ? true
 */
public class RegexMatch {
    public static void main(String[] args) {
        // boolean match =  isMatch("aa","aa");
        // boolean match =  isMatch("aaa","aa");
        // boolean match =  isMatch("aa", "a*");
        // boolean match =  isMatch("aa", ".*");
        boolean match = isMatch("ab", ".*");
        // boolean match = isMatch("aab", "c*a*b");
        System.out.println(match);

    }

    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        if (p.isEmpty()) return s.isEmpty();

        if (p.length() >= 2) {
            if (p.charAt(1) == '*') {
                if (!s.isEmpty()) {
                    if (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0))
                        return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
                    else
                        return isMatch(s, p.substring(2));
                } else
                    return true; // s:"", p:"a*" || ".*"
            }
        }

        // p is not empty, then at least 1
        if (p.charAt(0) == '.') {
            if (s.isEmpty()) return false; // s:"", p:"."
            else return isMatch(s.substring(1), p.substring(1));
        }

        if (s.charAt(0) != p.charAt(0)) return false;

        return isMatch(s.substring(1), p.substring(1));
    }
}
