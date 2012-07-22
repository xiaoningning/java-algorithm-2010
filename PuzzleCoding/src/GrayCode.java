/**
 * Created with IntelliJ IDEA.
 * Print the N-bit binary reflected Gray code using recursion.
 * <p/>
 * Gray Code of 3:
 * 000
 * 001
 * 011
 * 010
 * 110
 * 111
 * 101
 * 100
 */
public class GrayCode {
    public static void main(String[] args) {
        int N = 3;
        grayCode(N, new boolean[N]);
    }

    public static void grayCode(int n, boolean[] show) {
        if (n == 0) {
            showCode(show);
        } else {
            show[n - 1] = false;
            grayCode(n - 1, show);
            show[n - 1] = true;
            yargCode(n - 1, show);
        }

    }

    // append reverse of order n gray code
    public static void yargCode(int n, boolean[] show) {
        if (n == 0) {
            showCode(show);
        } else {
            show[n - 1] = true;
            grayCode(n - 1, show);
            show[n - 1] = false;
            yargCode(n - 1, show);
        }
    }

    public static void showCode(boolean[] show) {
        String code = "";
        for (boolean yes : show) {
            if (yes)
                code = "1" + code;
            else
                code = "0" + code;
        }
        System.out.println(code);
        // System.out.println(Integer.parseInt(code, 2));
    }

}
