import java.util.Arrays;

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
        grayCode(N);
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

    /*
    **	The BinaryToGray method is base on the bit of gray cod loop occur: 0,1,1,0
    **	Hence, we could calculate each bits of gray code by mod 4 to find the matching value
    **
    **	However, after search on the website, the convert formula is just simply as (i>>1) ^ i.
    **	The ith bit is equal to ith bit plus (i+1)th bit with no carry.
    */

    public static String[] grayCode(int n) {
        int[] code = new int[1 << n];
        String[] codeString = new String[1 << n];

        for (int i = 0; i < (1 << n); i++) {
            code[i] = (i >> 1) ^ i;
            // codeString[i] = Integer.toBinaryString((i >> 1) ^ i);
            codeString[i] = toBinaryString((i >> 1) ^ i);
        }

        System.out.println(Arrays.toString(codeString));
        System.out.println(Arrays.toString(code));

        return codeString;
    }

    public static String toBinaryString(int integer) {
        StringBuilder builder = new StringBuilder();
        int temp;
        while (integer >= 0) { // 0 case
            temp = integer;
            integer = (temp >> 1);
            builder.append(String.valueOf(temp % 2));
            // if insert at 0, no need to reverse
            // builder.insert(0, String.valueOf(temp % 2));
            if(integer ==0)
                break;
        }
        return builder.reverse().toString();
    }

}
