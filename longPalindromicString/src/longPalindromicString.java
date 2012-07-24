
public class longPalindromicString {
    public static void main(String[] args) {
        final String s = "abbbacddcaffgcddca";

        if (s.length() == 0)
            System.out.println(s); // "" is palindromic string

        String longPalindromicString = s.substring(0, 1);

        for (int i = 0; i < s.length(); i++) {
            String l1 = getCenterAroundString(s, i, i);
            if (l1.length() > longPalindromicString.length())
                longPalindromicString = l1;
            String l2 = getCenterAroundString(s, i, i + 1);
            if (l2.length() > longPalindromicString.length())
                longPalindromicString = l2;
        }

        System.out.println(longPalindromicString);
    }

    public static String getCenterAroundString(String s, int l, int r) {

        while (l >= 0 && r < s.length()) {
            if (s.charAt(r) == s.charAt(l)) {
                r++;
                l--;
            } else
                break;
        }

        //l = (l < 0) ? 0 : l + 1;
        l = l+1;
        return s.substring(l, r);


    }
}
