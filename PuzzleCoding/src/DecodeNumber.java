/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p/>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * <p/>
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * <p/>
 * The number of ways decoding "12" is 2.
 */
public class DecodeNumber {
    public static void main(String[] args) {
        String s1 = "11";
        String s2 = "2119";
        String s3 = "116";
        decodeNumber(s1);
        decodeNumber(s2);
        decodeNumber(s3);
    }

    public static void decodeNumber(String s) {
        int len = s.length();

        if (len == 0) {
            System.out.println("empty string");
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!((s.charAt(i) - '0') <= 9) && !(('9' - s.charAt(i)) >= 0)) {
                System.out.println(s + " is not number.");
                return;
            }
        }

        int[] ways = new int[len];
        ways[0] = (s.charAt(0) == '0') ? 0 : 1;
        for (int i = 1; i < len; i++) {
            char p = s.charAt(i - 1);
            char c = s.charAt(i);
            if (p !='0' && ways[i - 1] == 0)
                ways[i] = 1;
            if ( ways[i-1] != 0) {
                ways[i] = ways[i-1];
            }
            if (p == '1' || (p == '2' && c <= '6')) {
                if(i>=2)
                    ways[i] = ways[i - 2] + ways[i];
                else
                    ways[i] = ways[i-1] + ways[i];
            }
        }
        System.out.println(s + ":" + ways[len - 1]);
    }
}
