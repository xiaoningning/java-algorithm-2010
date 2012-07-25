import java.util.Arrays;
import java.util.Stack;

/*
* http://tech-queries.blogspot.com/2011/09/find-largest-sub-matrix-with-all-1s-not.html
*
* Given a 2D binary matrix filled with 0's and 1's,
* find the largest rectangle containing all ones and return its area.
*
* Given n non-negative integers representing the histogram's bar height
* where the width of each bar is 1,
* find the area of largest rectangle in the histogram.
*
* For example,
* Given height = [2,1,5,6,2,3],
* return 10.
*/
public class FindLargestSubMatrix {
    public static void main(String[] args) {
        int[] height = new int[]{2, 1, 5, 6, 2, 3};

        System.out.println("the histogram: " + Arrays.toString(height));
        System.out.println("the largest rectangle in the histogram: " + largestArea(height));
        System.out.println("the largest rectangle in the histogram1: " + largestArea1(height));


        int[][] matrix = new int[][]{
                {1, 1, 0, 0, 1, 0},
                {0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 0, 0}
        };

        System.out.println(Arrays.deepToString(matrix));
        largestMatrix(matrix);

    }

    // convert it to histogram
    public static void largestMatrix(int[][] m){

        if(m.length == 0 || m[0].length == 0)
            System.out.println("empty matrix");

        int max = 0;
        int end_row = 0;
        int left_col = 0;
        int right_col = 0;
        int h = 0 ;

        int[] height= new int[m[0].length];

        for(int i =0; i < m.length; i++){
            for(int j =0; j < m[0].length; j++){
                if(m[i][j]==1)
                    height[j] ++;
                else
                    height[j] = 0;
            }

            int[] tmp = largestAreaArray(height);
            if(tmp[0] > max){

                max = tmp[0];
                end_row = i;
                h = tmp[1];
                left_col = tmp[2];
                right_col = tmp[3];

            }
        }

        System.out.println("max area : " + max
                + " start " + (end_row - h +1) + "," + left_col
                + " end " + (end_row) +","+right_col);

    }

    // It's complicated to hold left/right in stack
    public static int largestArea(int[] h) {
        int max = 0, tmp;
        int[] area = new int[h.length];

        Stack<Integer> s = new Stack<Integer>();

        //find the left
        for (int i = 0; i <= h.length - 1; i++) {
            while (!s.isEmpty()) {
                if (h[i] <= h[s.peek()])
                    s.pop();
                else
                    break;
            }
            if (s.isEmpty())
                tmp = -1;
            else
                tmp = s.peek();
            area[i] = i - tmp - 1; // include itself
            s.push(i);
        }

        // empty stack for the right
        s.clear();

        //find right
        for (int i = h.length - 1; i >= 0; i--) {
            while (!s.isEmpty()) {
                if (h[i] <= h[s.peek()])
                    s.pop();
                else
                    break;
            }
            if (s.isEmpty())
                tmp = h.length;
            else
                tmp = s.peek();

            area[i] += tmp - i - 1; // left + right range and itself
            s.push(i);
        }

        // find max
        for (int i = 0; i < h.length; i++) {
            area[i] = h[i] * (area[i] + 1);
            if (area[i] > max)
                max = area[i];
        }
        return max;
    }

    public static int largestArea1(int[] h) {
        int max = 0;
        int[] left = new int[h.length];
        int[] right = new int[h.length];

        //find the left
        for (int i = 0; i <= h.length - 1; i++) {
            int j = i;
            while (j >= 0) {
                if (h[i] <= h[j])
                    j--;
                else
                    break;
            }
            left[i] = j + 1;
        }

        //find right
        for (int i = 0; i <= h.length - 1; i++) {
            int j = i;
            while (j < h.length) {
                if (h[i] <= h[j])
                    j++;
                else
                    break;
            }
            right[i] = j - 1;
        }
        // find max
        for (int i = 0; i <= h.length - 1; i++) {

            int tmp = h[i] * (right[i] - left[i] + 1); // left+right+itself
            if (tmp > max)
                max = tmp;
        }
        return max;
    }

    public static int[] largestAreaArray(int[] h) {
        int max = 0;
        int[] left = new int[h.length];
        int[] right = new int[h.length];

        //find the left
        for (int i = 0; i <= h.length - 1; i++) {
            int j = i;
            while (j >= 0) {
                if (h[i] <= h[j])
                    j--;
                else
                    break;
            }
            left[i] = j + 1;
        }

        //find right
        for (int i = 0; i <= h.length - 1; i++) {
            int j = i;
            while (j < h.length) {
                if (h[i] <= h[j])
                    j++;
                else
                    break;
            }
            right[i] = j - 1;
        }

        // need to a better structure to hold data
        int[] arr = new int[4]; // height, left, right
        // find max
        for (int i = 0; i <= h.length - 1; i++) {

            int tmp = h[i] * (right[i] - left[i] + 1);
            if (tmp > max){
                max = tmp;
                arr[0] = tmp; // max area
                arr[1] = h[i]; // max area of height
                arr[2] = left[i]; // left index
                arr[3] = right[i]; // right index

            }
        }
        return arr;
    }
}
