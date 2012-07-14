import java.util.Arrays;

public class StringAnagram {
    public static void main(String[] args) {
        // String s1 = "apple";
        // String s2 = "palpe";
        String s1 = "tiger";
        String s2 = "tgrie";

        System.out.println(isAnagram(s1, s2));
        System.out.println(isPermutation(s1, s2));


    }

    public static String stringSort(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }

    public static String stringSort1(String s) {
        char[] letter = new char[256];
        char[] a = s.toCharArray();
        for (char c : a) {
            letter[c]++;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < letter.length; i++) {
            if (letter[i] > 0) {
                while (letter[i] > 0) {
                    sb.append((char) i);
                    letter[i]--;
                }
            }
        }
        return sb.toString();
    }

    public static boolean isAnagram(String s1, String s2) {
        return stringSort(s1).equals(stringSort1(s2));
    }

    public static boolean isPermutation(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        char[] letter = new char[256];
        char[] a1 = s1.toCharArray();
        for (char c : a1) {
            letter[c]++;
        }
        char[] a2 = s2.toCharArray();
        for (char c : a2) {
            letter[c]--;
        }

        for (int i = 0; i < letter.length; ++i) {
            if ((letter[i] > 0) || (letter[i] < 0))
                return false;
        }
        return true;
    }


}
