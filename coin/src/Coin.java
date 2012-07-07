
import java.util.*;

public class Coin {


    public static void main(String[] args) {
        new Coin().run();

        System.out.println(get_distinct_ways(3));
        System.out.println(fibo(3));
    }


    int[][] dp = null;

    public void run() {
        Scanner in = new Scanner(System.in);
        // coin
        int[] arr = new int[]
                {1, 5, 10, 25};
        int n = in.nextInt();

        dp = new int[arr.length][n + 1];
        for (int i = 0; i < arr.length; i++)
            Arrays.fill(dp[i], -1);

        int ans = makeChange(arr, 0, n);

        System.out.println(ans);
        System.out.println(howMany(arr, n));
        System.out.println(change(25, n));


        System.out.close();
    }

    // climb stairs by 1 or 2
    public static int get_distinct_ways(int n)
    {
        int s0 = 0, s1 = 1, temp;

        while (n > 0){
            temp = s0 + s1;
            s0 = s1;
            s1 = temp;
            n--;
        }

        return s1;
    }


    // climb stairs by 1 or 2
    // it is a fibonacci sequence
    public static int fibo(int n){
        if(n<=1) return n ;
        else return fibo(n-1)+fibo(n-2) ; // add the last step 1 or 2.
    }

    /*
    把所有硬币放在一个数组c里面。然后函数F[i,j]代表用前i个硬币摆出value j的摆法。
    初始F[*,*] = 0
    然后F[i,j] = sum(F[i-1, j - k * c[i]]) 0 <= k <= j/c[i]

    实现起来可以用背包九讲里面第二讲的方法：
    F[i,j] = F[i, j - c[i]] + F[i-1, j]

     */

    public static int howMany(int[] coin, int n) {

        int[] dp = new int[n + 1];

        for (int i = 0; i < coin.length; i++) {
            for (int j = coin[i]; j <= n; j++) {

                dp[j] += (j == coin[i]) ? 1 : dp[j - coin[i]];
            }
        }

        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    public static int makeChange(int[] arr, int start, int value) {
        if (value == 0)
            return 1;

        if (start == arr.length) {

            return 0;
        }

        int total = 0;
        for (int i = 0; i * arr[start] <= value; i++) {
            System.out.println("coin "+arr[start] + " loop " + i);
            total += makeChange(arr, start + 1, value - i * arr[start]);
            System.out.println("current total " + total);

        }


        return total;

    }

    public static int change(int denom, int n){
        int next_denom = 0;
        switch (denom){
            case 25:
                next_denom = 10;
                break;
            case 10:
                next_denom = 5;
                break;
            case 5:
                next_denom = 1;
                break;
            case 1:
                return 1;
        }
        int ways = 0;

        for(int i =0; i*denom<=n;i++){
            ways += change(next_denom, n-i*denom);
        }

        return ways;

    }
}
