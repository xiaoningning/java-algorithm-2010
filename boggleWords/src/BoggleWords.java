import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class BoggleWords {
    private char[][] board;
    private boolean[][] visited;
    private HashSet<String> dict;

    public BoggleWords(int N, HashSet<String> d){
        dict = d;
        board = new char[N][N];
        visited = new boolean[board.length][board.length]; // init all false

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                char s = (char)(Math.random()*26 + 'A');
                board[i][j] = s;
            }
        }
    }

    public BoggleWords(char[][] words, HashSet<String> d){
        dict = d;
        board = words;
        visited = new boolean[board.length][board.length]; // init all false

    }

    public boolean containsPrefix(String s){
        ArrayList<String> l = new ArrayList<String>(dict);
        for(String word :l){
            if(word.indexOf(s) >= 0)
                return true;

        }
        return false;
    }

    public void dfs(String prefix, int r, int c){

        if(r >=board.length || c >=board.length || r < 0 || c < 0)
            return;

        if (visited[r][c])
            return;

        visited[r][c] = true;
        prefix += board[r][c];

        if(!containsPrefix(prefix)){
            visited[r][c] = false;
            return;
        }

        if(dict.contains(prefix))
            System.out.println(prefix);

        for(int ii = -1; ii <= 1; ii++){
            for(int jj = -1; jj<=1; jj++){
                dfs(prefix, r + ii, c + jj);
            }
        }

        visited[r][c] = false;

    }

    public void dfs(String prefix, int r, int c, int k){

        if(r >=board.length || c >=board.length || r < 0 || c < 0 )
            return;
        if(visited[r][c])
            return;

        visited[r][c] = true;
        prefix += board[r][c];

        if(prefix.length() == k){
            System.out.println(prefix);

        }

        for(int ii = -1; ii <= 1; ii++){
            for(int jj = -1; jj<=1; jj++){
                dfs(prefix, r + ii, c + jj, k);

            }
        }

        visited[r][c] = false;

    }

    public void show(){

        for(int i =0; i < board.length; i++) {
            for(int j=0; j<board.length; j++){
                dfs("", i,j);

            }
        }

    }

    public void show(int k){

        System.out.println("show " + k + " length word");
        for(int i =0; i < board.length; i++) {
            for(int j=0; j<board.length; j++){

                dfs("", i,j, k);

            }
        }

    }


    public static void main(String[] args){
        int N = 3;

        HashSet<String> dict = new HashSet<String>();
        dict.add("AB");
        dict.add("YOU");
        dict.add("C");
        dict.add("ALOM");
        dict.add("EB");
        dict.add("MXY");
        dict.add("OA");
        dict.add("LY");

        BoggleWords boggle = new BoggleWords(N, dict);

        System.out.println(Arrays.deepToString(boggle.board));

        // boggle.show();
        // boggle.show(2);

        char[][] words = {{'A','B','E'},
                {'L','O','M'},
                {'U','Y','X'}};

        BoggleWords boggle2 = new BoggleWords(words, dict);

        System.out.println(Arrays.deepToString(boggle2.board));

        boggle2.show();

        boggle2.show(4);


    }
}
