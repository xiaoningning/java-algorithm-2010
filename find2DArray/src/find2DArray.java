import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: ningwei
 * Date: 5/15/12
 * Time: 8:27 PM
 * To find a x in a 2D sorted array
 */
public class find2DArray {
    public static void main(String[] args) {
        int[][] arr = {{15, 20, 40, 85},
                {20, 35, 50, 95},
                {40, 55, 80, 110},
                {70, 80, 95, 120},
                {95, 110, 120, 140},
                {100, 120, 140, 150}};

        System.out.println(Arrays.deepToString(arr));

        int target = 80;

        //System.out.println(arr[0].length - 1);

        find2DArrayNum(arr, target);
    }

    public static void find2DArrayNum(int[][] n, int x) {
        int r = 0;
        int c = n[0].length -1;
        while( r < n.length && c >= 0){
            // System.out.println(n[r][c]);
            if(n[r][c] == x){
                System.out.println(r + " " + c );
                return;
            }
            else if (n[r][c] < x)
                r++;
            else // n[r][c] >x
                c--;
        }
        System.out.println("not found");
    }



}

