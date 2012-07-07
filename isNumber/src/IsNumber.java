public class IsNumber {
    public static void main(String[] args){
        String s1 = "abc";
        String s2 = "-1.2";
        String s3 = "1e-2";
        String s4 = "0+01";
        String s5 = " 1.2 ";

        System.out.println(s1 + " " + isNumber(s1));
        System.out.println(s2 + " " + isNumber(s2));
        System.out.println(s3 + " " + isNumber(s3));
        System.out.println(s4 + " " + isNumber(s4));
        System.out.println(s5 + " " + isNumber(s5));
    }

    public static boolean isNumber(String s){
        boolean num = false;
        boolean exp = false;
        boolean dot = false;
        boolean space = false;

        int N = s.length();
        int i = 0;

        while(i < N){
            if(s.charAt(i) == ' ')
                space = true;
            else if(space)
                return false; // no space for a number
            else if(s.charAt(i) == '-' || s.charAt(i) == '+'){
                // +/- should be after e
                if (i > 0)
                    if (s.charAt(i-1) != 'e' && s.charAt(i-1) != 'E')
                        return false;
                // i == 0 true if num == true
            }
            else if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                num = true;
            else if(s.charAt(i) == 'e' || s.charAt(i) == 'E'){
                // only one e, at least one num in the front
                if (exp || !num)
                    return false;
                exp = true;
            }
            else if (s.charAt(i) == '.'){
                // no e if dot, only one dot
                if(exp || dot)
                    return false;
                dot = true;
            }
            else
                return false;

            i++;
        }

        return num;
    }
}
