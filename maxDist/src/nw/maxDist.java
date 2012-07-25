package nw;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Scanner;

/**
 *  For a given array arr[], returns the maximum j – i such that
 *  arr[j] > arr[i]
 */
public class maxDist {
    private static int maxDist(List<Integer> list) {

        if (list == null || list.size() == 0)
            return -1;

        int s = 0, e = list.size() - 1;

        while (s <= e) {

            if (list.get(s) < list.get(e)) {

                System.out.println(s + ": " + list.get(s));
                System.out.println(e + ": " + list.get(e));
                return e - s;

            } else {

                if (list.get(s) < list.get(e-1)) {
                    e--;
                }
                else if (list.get(e) > list.get(s+1)) {
                    s++;
                }
                else{
                    s++;
                    e--;
                }
            }
        }
        return -1;

    }

    public static void main(String args[]) {

        List<Integer> list = new ArrayList<Integer>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your int array : ");
        String in = scanner.nextLine();
        String[] ins = in.trim().split(",");

        boolean rList = true;

        if (ins.length > 0) {

            try {
                for (String s : ins) {
                    list.add(Integer.parseInt(s.trim()));
                }
            } catch (NumberFormatException e) {
                // e.printStackTrace();
                rList = false;

            }

        }

        if (!rList) {
            final int size = 10;
            Random rg = new Random();

            for (int j = 0; j < size; j++) {
                int tmp;
                tmp = rg.nextInt(10);
                if (!list.contains(tmp))
                    list.add(tmp);
                else
                    j--;
            }
        }


        System.out.println(list.toString());

        System.out.println("result: " + maxDist(list));
    }
}
