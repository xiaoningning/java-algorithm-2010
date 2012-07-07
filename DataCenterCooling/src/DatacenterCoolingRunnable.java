import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class DatacenterCoolingRunnable implements Runnable {

    private int[][] duct;
    private int row, col;
    private boolean[][] visited;
    private ArrayList<String> path;

    public ArrayList<ArrayList<String>> allPath;

    // public static ExecutorService e = Executors.newFixedThreadPool(50);

    public DatacenterCoolingRunnable(int[][] d,
                                     int r,
                                     int c,
                                     boolean[][] v,
                                     ArrayList<String> p,
                                     ArrayList<ArrayList<String>> ap) {
        duct = d;
        row = r;
        col = c;
        visited = v;
        path = p;
        allPath = ap;

    }


    public void run() {
        try {
            if (row < 0 || col < 0 || row >= duct.length || col >= duct[0].length)
                return;


            if (visited[row][col] == true || duct[row][col] == 1)
                return;


            path.add(row + "," + col);
            visited[row][col] = true;

            System.out.println(path.toString());
            System.out.println(Arrays.deepToString(visited));

            if (duct[row][col] == 3) {
                System.out.println(path.toString());
                allPath.add(path);
                synchronized (allPath) {

                    allPath.notifyAll();
                }

            }


            DatacenterCoolingRunnable dc1 = new DatacenterCoolingRunnable(duct, row - 1, col, visited, path, allPath);
            DatacenterCoolingRunnable dc2 = new DatacenterCoolingRunnable(duct, row + 1, col, visited, path, allPath);
            DatacenterCoolingRunnable dc3 = new DatacenterCoolingRunnable(duct, row, col - 1, visited, path, allPath);
            DatacenterCoolingRunnable dc4 = new DatacenterCoolingRunnable(duct, row, col - 1, visited, path, allPath);

            Thread t1 = new Thread(dc1);

            Thread t2 = new Thread(dc2);

            Thread t3 = new Thread(dc3);

            Thread t4 = new Thread(dc4);


            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
            t4.start();
            t4.join();

            //visited[row][col] = false;

        } catch (RejectedExecutionException re) {
            re.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {

        int[][] duct = new int[][]{{2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 3, 1}};
        boolean[][] visited = new boolean[duct.length][duct[0].length];
        ArrayList<String> path = new ArrayList<String>();
        ArrayList<ArrayList<String>> allPath = new ArrayList<ArrayList<String>>();

        try {
            DatacenterCoolingRunnable dc = new DatacenterCoolingRunnable(duct, 0, 0, visited, path, allPath);
            Thread df = new Thread(dc);
            df.start();
            df.join();
            System.out.println(dc.allPath.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /*
        catch (NullPointerException ne){
            ne.printStackTrace();
            System.out.println("no path.");
        }
        finally {
            //e.shutdown();
        }
          */
    }
}
