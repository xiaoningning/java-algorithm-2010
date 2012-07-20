/*
**	Dynamic Programming, very similar to longest common substring question
**
**	dis[i,j] means the distance from transform s1s2..si to t1t2...tj
**	then the value of dis[i,j] should the minimus value of following:
**		d[i-1, j] + deletion		(just delete si to have d[i-1, j] situation)
**		d[i, j-1] + insertion		(base on d[i, j-1], insert tj to s become d[i, j])
**		d[i-1, j-1]+substitution	(base on d[i-1,j-1], replace si with tj become d[i, j])
*/
public class WordEditDistance {
    public static void main(String[] args) {

        wordEditDistance("aabc", "aacc");
        wordEditDistance("aabbcc", "aabb");
        wordEditDistance("aabac", "abac");
        wordEditDistance("algorithm", "altruistic");

    }

    public static void wordEditDistance(String source, String target) {
        int sLen = source.length();
        int tLen = target.length();

        int[][] dist = new int[sLen + 1][tLen + 1];
        int i = 0, j = 0;

        for (i = 1; i < sLen + 1; ++i)
            dist[i][0] = i;

        for (j = 1; j < tLen + 1; ++j)
            dist[0][j] = j;

        for (i = 1; i < sLen + 1; ++i) {
            for (j = 1; j < tLen + 1; ++j) {
                int d1 = dist[i - 1][j] + 1;
                int d2 = dist[i][j - 1] + 1;
                int d3 = dist[i - 1][j - 1] + ((source.charAt(i - 1) == target.charAt(j - 1)) ? 0 : 1);

                dist[i][j] = Math.min(d1, Math.min(d2, d3));

            }
        }

        System.out.println(source + "->" + target + ": " + dist[sLen][tLen]);

    }
}
