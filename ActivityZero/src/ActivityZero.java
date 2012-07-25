import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
  *
 * You'll be given a list of activities and their caloric impact.
 * Write a program that outputs the names of activities one should choose to partake in
 * so that the sum of their caloric impact is zero.
 * Once an activity is selected, it cannot be chosen again.
 *
 * It is a question to find a sum to zero from all possible elements of an array
 * Basically use gray code to pick element from a given array and calculate the sum
 *
 */

public class ActivityZero {
    private String activity;
    private int calorie;

    public ActivityZero(String a, int c) {
        this.activity = a;
        this.calorie = c;
    }

    public static void main(String args[]) {

        List<ActivityZero> listActivityZero = new ArrayList<ActivityZero>();

        Scanner scanner = new Scanner(System.in);

        //TODO: input exception is not handled.

        int n = Integer.parseInt(scanner.nextLine());
        while (n > 0) {
            String newInput = scanner.nextLine();
            String[] tempArray = newInput.split(" ");

            ActivityZero az = new ActivityZero(tempArray[0], Integer.parseInt(tempArray[1]));
            listActivityZero.add(az);
            n--;
        }

        scanner.close();

        List<ActivityZero> tmpList = new ArrayList<ActivityZero>();
        sumCalorie(listActivityZero, listActivityZero.size(), tmpList, true);

    }

    public static void sumCalorie(List<ActivityZero> list, int n, List<ActivityZero> tmpList, boolean pick) {
        if (n == 0)
            return; // start recursion back
        sumCalorie(list, n - 1, tmpList, true);
        if (pick) {
            tmpList.add(list.get(n - 1));
            showSum(tmpList);
        } else {
            tmpList.remove(list.get(n - 1));
            showSum(tmpList);
        }
        sumCalorie(list, n - 1, tmpList, false);

    }

    public static void showSum(List<ActivityZero> l) {
        int sumCalorie = 0;
        for (ActivityZero az : l) {
            sumCalorie += az.calorie;

        }
        if (l.size() != 0 && sumCalorie == 0) {
            System.out.println("The winner picks: ");
            for (ActivityZero az : l) {
                System.out.println(az.activity + " " + az.calorie);
            }
        }
    }


}
