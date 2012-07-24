import java.util.ArrayList;

public class CombinationPermutation {
    public static void main(String[] args) {

        String s = "abcd";
        int k = 3;
        System.out.println("Combination");
        combinationK("", s, k);
        System.out.println("Permutation");
        permutationK(s.toCharArray(), s.length(), k);
        System.out.println("Permutation1");
        permutationK1("", s, k);
        System.out.println("MoveInOut");
        move(s.toCharArray(), s.length(), new ArrayList<String>(), true);

    }

    public static void combinationK(String prefix, String s, int K) {
        if (K == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < s.length(); ++i)
            combinationK(prefix + s.charAt(i), s.substring(i + 1), K - 1);
    }

    public static void permutationK1(String prefix, String s, int K) {
        if (K == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < s.length(); ++i)
            permutationK1(prefix + s.charAt(i), s.substring(0,i)+s.substring(i + 1), K - 1);
    }

    public static void permutationK(char[] s, int n, int K) {
        if (K == 0) {
            for (int i = n; i < s.length; ++i)
                System.out.print(s[i]);
            System.out.println();
            return;
        }
        for (int i = 0; i < n; ++i) {
            swap(s, i, n - 1);
            permutationK(s, n - 1, K - 1);
            swap(s, i, n - 1);
        }
    }

    public static void swap(char[] a, int left, int right) {
        char tmp = a[left];
        a[left] = a[right];
        a[right] = tmp;
    }

    public static void move(char[] s, int n, ArrayList<String> result, boolean pick) {
        if (n == 0) {
            System.out.println(result.toString());
            return;
        }
        move(s, n - 1, result, true);
        if (pick) {
            result.add(String.valueOf(s[n - 1]));
        } else {
            result.remove(String.valueOf(s[n - 1]));
        }
        move(s, n - 1, result, false);

    }
}
