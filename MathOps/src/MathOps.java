

public class MathOps {

    public static int add(int x, int y){

        if(y==0) return x;
        int a = x^y;        // add
        int b = (x&y)<<1;   // carry
        return add(a, b);
    }

    public static double sqrt(double x){

        double y = x;
        double e = 1e-15;

        while(Math.abs(x/y - y) > e*y){
            y = (x/y+y)/2.0;
        }
        return y;
    }

    public static double pow(double x, int y){
        if (y==0) return 1;
        if (y==1) return x;

        boolean negative = false;
        if(y<0){
            negative = true;
            y = -y;
        }

        double r = pow(x, y/2);
        r = r*r;

        if(y%2 != 0) r = r*x;
        if(negative) r = 1/r;

        return r;
    }

    public static void main(String[] args){

        System.out.println("add: " + add(6, -2));
        System.out.println("sqrt: " + sqrt(120.0));
        System.out.println("pow: " + pow(2.0,-3));
    }
}
