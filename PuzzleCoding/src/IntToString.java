/**
 * A:0, B:1, C:2, ... , AA:26, AB:27, ... , given an integer, return
 * corresponding string
 */
public class IntToString {
    public static void main(String[] args) {
        int n = 52;
        System.out.println(intToString(n));
    }

    public static String intToString(int n) {
        StringBuilder s = new StringBuilder();
        n += 1; // b/c A:0

        while (n > 0) {
            if (n % 26 == 0) {
                s.insert(0, "Z");
                n -= 26;
            } else {
                s.insert(0, (char) ('A' + n % 26 - 1));
            }
            n /= 26;
        }
        return s.toString();
    }
}
