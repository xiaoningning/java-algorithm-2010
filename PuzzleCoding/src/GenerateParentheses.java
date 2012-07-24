public class GenerateParentheses {
    public static void main(String[] args) {
        int N = 3;
        generateParentheses(new char[2 * N], N, N, 0);

        System.out.println(isGoodParentheses("(()][())".toCharArray()));
    }

    public static void generateParentheses(char[] prefix, int left, int right, int level) {
        if (left > right)
            return;
        if (left == 0 && right == 0) {
            System.out.println(String.valueOf(prefix));
            return;
        } else {
            if (left > 0 ) {
                prefix[level] = '(';
                generateParentheses(prefix, left - 1, right, level + 1);
            }
            if (right > 0 ) {
                prefix[level] = ')';
                generateParentheses(prefix, left, right - 1, level + 1);
            }
        }
    }

    public static boolean isGoodParentheses(char[] s) {

        int level1 = 0;
        int level2 = 0;

        for (int i = 0; i < s.length; i++) {
            if (level1 == 0)
                if (s[i] == ')')
                    return false;
            if (level2 == 0)
                if (s[i] == ']')
                    return false;

            if (s[i] == '(')
                level1++;
            if (s[i] == ')')
                level1--;
            if (s[i] == '[')
                level2++;
            if (s[i] == ']')
                level2--;

        }

        return (level1 == 0) && (level2 == 0);
    }
}
