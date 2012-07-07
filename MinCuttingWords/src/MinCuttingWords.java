import java.util.List;
import java.util.Arrays;

public class MinCuttingWords {

    public static void minCuttingWords(List<String> dict, String tar_str){
        int tar_str_len = tar_str.length();
        int[] cost = new int[tar_str_len + 1];
        cost[0] = 0;

        for (int i = 1; i <= tar_str.length(); ++i) {
            cost[i] = Integer.MAX_VALUE;

            //for all possible cut from 0 to i -1
            for (int possible_cut = i - 1; possible_cut >= 0; --possible_cut) {

                //check if substring is valid. yes, try this cut
                if (dict.indexOf(tar_str.substring(possible_cut, i)) != -1) {

                    // System.out.println(tar_str.substring(possible_cut, i));
                    //if it is better than existing cut, store it
                    if (cost[possible_cut] + 1 < cost[i])
                        cost[i] = cost[possible_cut] + 1;
                }
            }
            System.out.println("cost " + i + ": " + (cost[i]));
        }

        System.out.println("min cut is " + (cost[tar_str_len] - 1));
    }

    public static void main(String[] args) {
        String dict_str = "app ap le pie apple pie pi ie a b c d e f g h i k l m n o p q r s t u v w x y z ";
        String tar_str = "applepieie";
        List<String> dict = Arrays.asList(dict_str.split(" "));

        minCuttingWords(dict, tar_str);
    }
}


