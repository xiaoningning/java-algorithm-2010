package nw;

import java.lang.System;
import java.util.*;

/**
 * @author ning wei
 *         Date: 4/24/12
 *         Time: 10:19 PM
 *         given an array which consists of unique element,
 *         find the three elements which sum as Zero.
 */

public class SumZero {

    // a very bad implementation
    private static void sumZero(List<Integer> list) {

        int i = 0,
                j = 1,
                m = list.size() - 1,
                n = list.size() - 2;

        if (list.size() < 3) {
            System.out.println("list contains less than 3 elements");
        } else if (list.size() == 3) {
            if (list.get(0) + list.get(1) + list.get(m) == 0) {
                System.out.println(list.get(0) + " " + list.get(1) + " " + list.get(m));
            } else {
                System.out.println("not found");
            }

        } else {
            while (j < n) {
                //System.out.println("check " + list.get(i) + " " + list.get(j) + " " + list.get(n) + " " + list.get(m));
                if (Math.abs(list.get(i)) < Math.abs(list.get(m))) {
                    if (Math.abs(list.get(i)) + Math.abs(list.get(j)) == Math.abs(list.get(n))) {
                        System.out.println("winner: " + list.get(i) + " " + list.get(j) + " " + list.get(n));
                    } else if (Math.abs(list.get(i)) + Math.abs(list.get(j)) == Math.abs(list.get(m))) {
                        System.out.println("winner: " + list.get(i) + " " + list.get(j) + " " + list.get(n));
                    }

                    m--;
                    n--;

                } else if (Math.abs(list.get(i)) > Math.abs(list.get(m))) {
                    if (Math.abs(list.get(m)) + Math.abs(list.get(n)) == Math.abs(list.get(j))) {
                        System.out.println("winner: " + list.get(j) + " " + list.get(n) + " " + list.get(m));
                    } else if (Math.abs(list.get(m)) + Math.abs(list.get(n)) == Math.abs(list.get(i))) {
                        System.out.println("winner: " + list.get(j) + " " + list.get(n) + " " + list.get(m));
                    }
                    i++;
                    j++;

                } else {
                    if (list.get(j) == 0 || list.get(n) == 0) {
                        System.out.println("winner: " + list.get(i) + " " + 0 + " " + list.get(m));
                    }
                    i++;
                    j++;
                    m--;
                    n--;

                }
            }


        }


    }

    public static int binarySearch(int[] list, int k) {
        int s = 0;
        int e = list.length - 1;

        while (s <= e) {
            int m = (e+s) / 2;
            if (list[m] == k)
                return m;
            else if (list[m] > k) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }

        return -1;
        // return Integer.MIN_VALUE;
    }

    public static void sum3Zero(int[] list) {
        if (list.length < 3) {
            System.out.println("too short of list");
            return;
        }

        for (int i = 0; i < list.length-2; ++i) {
            if (list[i] > 0) {
                System.out.println("not found");
                break;
            }
            for (int j = i + 1; j < list.length-1; ++j) {
                int tmp = (list[i] + list[j]) * -1;

                int k = binarySearch(list, tmp);
                //int k = Arrays.binarySearch(list,tmp);

                if (k < 0) {
                    System.out.println("not found");
                    return;
                } else {
                    System.out.println(list[i] + " " + list[j] + "  " + list[k]);
                    return;

                }


            }

        }
    }

    public static void sum3Zero1(int[] list) {
        if (list.length < 3) {
            System.out.println("too short of list");
            return;
        }

        for (int i = 0; i < list.length-2; ++i) {
            if (list[i] > 0) {
                System.out.println("not found");
                break;
            }
            for (int j = i + 1; j < list.length-1; ++j) {
                int tmp = (list[i] + list[j]) * -1;

                int s = j + 1, e = list.length - 1;
                while (s <= e) {
                    int m = (s + e) / 2;
                    if (list[m] == tmp) {
                        System.out.println(list[i] + " " + list[j] + "  " + list[m]);
                        return;
                    } else if (list[m] < tmp) {
                        s = m + 1;
                    } else {
                        e = m - 1;
                    }
                }

            }

        }
        System.out.println("not found");
        return;
    }

    public static void main(String args[]) {

        final int size = 10;
        Random rg = new Random();
        List<Integer> list = new ArrayList<Integer>();

        for (int j = 0; j < size; j++) {
            int tmp;
            tmp = rg.nextInt(20) - 10;
            if (!list.contains(tmp))
                list.add(tmp);
            else
                j--;
        }

        Collections.sort(list);

        System.out.println("original list: " + list);

        sumZero(list);
        int[] a = new int[]{-2, -1, 2, 3, 4, 6};
        Arrays.sort(a);
        sum3Zero(a);
        sum3Zero1(a);

        System.exit(0);

    }
}
