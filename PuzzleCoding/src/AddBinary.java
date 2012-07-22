/*
* Given two binary strings, return their sum (also a binary string).
* For example,
* a = "11"
* b = "1"
* Return "100".
*/
public class AddBinary {
    public static void main(String[] args) {
        String s1 = "11";
        String s2 = "110";

        int result = string2Integer(s1, 2) + string2Integer(s2, 2);
        System.out.println(toBinary(result));
        result = Integer.parseInt(s1, 2) + Integer.parseInt(s2, 2);
        System.out.println(Integer.toBinaryString(result));

        System.out.print(~(0 & -1));


    }

    public static int string2Integer(String s, int code) {
        int len = s.length();
        int result = 0;
        for (int i = 0; i < len; ++i) {
            int temp = Integer.valueOf(s.substring(len - i - 1, len - i));
            result += Math.pow(code, i) * temp;
        }
        return result;
    }

    public static String toBinary(int integer) {
        StringBuilder builder = new StringBuilder();
        int temp;
        while (integer >= 0) {
            temp = integer;
            integer = (temp >> 1);
            builder.append(temp % 2);
            if(integer ==0)
                break;
        }
        return builder.reverse().toString();
    }
}
