import java.util.Scanner;

public class Permutations {

    // print N! permutation of the characters of the string s (in order)
    public static void perm1(String s) {
        perm1("", s);
    }

    private static void perm1(String prefix, String s) {
        // System.out.println("prefix: " + prefix);
        // System.out.println("s: " + s);
        int N = s.length();
        if (N == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < N; i++)
                perm1(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, N));
        }

    }

    // print N! permutation of the elements of array a (not in order)
    public static void perm2(String s) {
        int N = s.length()-1;
        /*
        char[] a = new char[N];
        for (int i = 0; i < N; i++)
            a[i] = s.charAt(i);
        */
        char[] a = s.toCharArray();
        perm2(a, N);
    }

    private static void perm2(char[] a, int n) {
        if (n == 0) {
            System.out.println(a);
            return;
        }
        for (int i = 0; i <= n; i++) {
            swap(a, i, n - 1);
            perm2(a, n - 1);
            swap(a, i, n - 1);
        }
    }

    // swap the characters at indices i and j
    private static void swap(char[] a, int i, int j) {

        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("enter the number: ");
        int N = Integer.parseInt(s.nextLine());
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String elements = alphabet.substring(0, N);
        perm1(elements);
        System.out.println();
        perm2(elements);
    }
}
