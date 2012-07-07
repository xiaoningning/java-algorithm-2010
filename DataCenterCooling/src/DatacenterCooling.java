import java.util.ArrayList;
import java.util.Arrays;


public class DatacenterCooling {
    public static void main(String[] args) {
        int[][] duct = new int[][]{{2, 0, 0,0},
                {0, 0, 0, 0},
                {0, 0, 3, 1}};
        /*
        int[][] duct = new int[][]{{2, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {3, 0, 0, 0, 0, 1, 1}};
         */

        boolean[][] visited = new boolean[duct.length][duct[0].length];
        ArrayList<ArrayList<String>> allPath = new ArrayList<ArrayList<String>>();
        ArrayList<String> path = new ArrayList<String>();

        System.out.println(Arrays.deepToString(duct));

        findPath(duct, 0, 0, visited, path, allPath);

        int count = 0;
        for (ArrayList<String> p : allPath) {
            if (p.size() == duct.length * duct[0].length - 1) {
                // System.out.println(p.toString());
                count++;
            }
        }
        System.out.println("how many ways to run the cooling duct: " + count);


    }


    public static void findPath(int[][] duct,
                                int r,
                                int c,
                                boolean[][] visited,
                                ArrayList<String> path,
                                ArrayList<ArrayList<String>> allPath) {
        if (r < 0 || c < 0 || r >= duct.length || c >= duct[0].length)
            return;

        if (visited[r][c] == true || duct[r][c] == 1)
            return;

        ArrayList<String> p = new ArrayList<String>(path);
        boolean[][] v = visited;

        p.add(r + "," + c);
        v[r][c] = true;

        if (duct[r][c] == 3) {
            allPath.add(p);

        }

        findPath(duct, r + 1, c, v, p, allPath);
        findPath(duct, r - 1, c, v, p, allPath);
        findPath(duct, r, c + 1, v, p, allPath);
        findPath(duct, r, c - 1, v, p, allPath);

        v[r][c] = false;

    }
}
