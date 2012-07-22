import java.util.ArrayList;
import java.util.Arrays;

public class allSubsets{
    public static void main(String[] args){
        int[] a = {1,2,3};
        ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
        System.out.println("take subset:");
        allsubsets = genSubsets(a, 0);
        System.out.println(allsubsets.toString());

        // gray code to generate all sets
        System.out.println("gray code:");
        boolean[] pick = new boolean[a.length];

        //gray code
        yarg(a, a.length, pick);

        // move in/out

        System.out.println("move in out:");
        moveIn(a, a.length, new ArrayList<Integer>(), true);

    }

    public static void moveIn(int[] a, int k, ArrayList<Integer> t, boolean pick){
        if (k == 0){
            System.out.println(t.toString());
            return;
        }
        moveIn(a, k-1, t, true);
        if (pick){
            t.add(a[k-1]);
            //System.out.println("in " + a[k-1]);
        }
        else{
            // remove object, not index,
            // if int, it is taken as an index
            t.remove((Object)a[k-1]);
            //System.out.println("out " + a[k-1]);
        }
        moveIn(a, k-1, t, false);

    }


    public static ArrayList<ArrayList<Integer>> genSubsets(int[] a, int index){
        ArrayList<ArrayList<Integer>> allsubsets;
        if(a.length == index){
            allsubsets = new ArrayList<ArrayList<Integer>>();
            allsubsets.add( new ArrayList<Integer>());
        }
        else{
            allsubsets = genSubsets(a, index+1);
            int item = a[index];
            ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<ArrayList<Integer>>();
            for(ArrayList<Integer> subset : allsubsets) {
                ArrayList<Integer> newsubset = new ArrayList<Integer>();
                newsubset.addAll(subset); //
                newsubset.add(item);
                moresubsets.add(newsubset);
            }
            allsubsets.addAll(moresubsets);
        }

        return allsubsets;

    }

    public static void show(boolean[] a, int[] n) {
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        for (int i = 0; i < a.length; i++){
            if (a[i] == true)
                tmp.add(n[i]);
            // gray code
            /*
            if (a[i] == true)
                System.out.print(1);
            else
                System.out.print(0);
            */
        }

        System.out.println(tmp.toString());
    }

    public static void yarg(int[] n, int k, boolean[] a) {
        if (k == 0) show(a, n);
        else {
            a[k-1] = true;
            gray(n, k-1, a);
            a[k-1] = false;
            yarg(n, k-1, a);
        }
    }

    public static void gray(int[] n, int k, boolean[] a) {
        if (k == 0) show(a, n);
        else {
            a[k-1] = false;
            gray(n, k-1, a);
            a[k-1] = true;
            yarg(n, k-1, a);
        }
    }

}