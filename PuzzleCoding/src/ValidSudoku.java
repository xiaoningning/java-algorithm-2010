/**
 * Determine if a Sudoku is valid,
 * Each row must have the numbers 1-9 occurring just once.
 * Each column must have the numbers 1-9 occurring just once.
 * the numbers 1-9 must occur just once in each of the 9 sub-boxes of the grid.
 * <p/>
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 */
public class ValidSudoku {
    public static void main(String[] args) {
        char[][] grid = new char[9][9];
        grid[0] = "53..7....".toCharArray();
        grid[1] = "6..195...".toCharArray();
        grid[2] = ".98....6.".toCharArray();
        grid[3] = "8...6...3".toCharArray();
        grid[4] = "4..8.3..1".toCharArray();
        grid[5] = "7...2...6".toCharArray();
        grid[6] = ".6....28.".toCharArray();
        grid[7] = "...419..5".toCharArray();
        grid[8] = "....8..79".toCharArray();

        boolean isValid = isValidSudoku(grid);
        System.out.print(isValid);

    }

    public static boolean isValidSudoku(char[][] board) {
        boolean[]  checked;
        //validate row
        for (int i = 0; i < board.length; i++) {
            checked = new boolean[board.length];
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int index = board[i][j] - '0';
                    if (checked[index - 1])
                        return false;
                    else
                        checked[index - 1] = true;
                }
            }
        }
        //validate column
        for (int j = 0; j < board[0].length; j++) {
            checked = new boolean[board.length];
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] != '.') {
                    int index = board[i][j] - '0';
                    if (checked[index - 1])
                        return false;
                    else
                        checked[index - 1] = true;
                }
            }
        }

        //validate 3x3 sub-box
        for (int i = 0; i < board.length; i++) {
            checked = new boolean[board.length];
            for (int j = 0; j < board[0].length; j++) {

                //convert to box sub-index
                int r = i / 3 * 3 + j / 3;
                int c = i % 3 * 3 + j % 3;

                if (board[r][c] != '.') {

                    int index = board[r][c] - '0';
                    if (checked[index - 1])
                        return false;
                    else
                        checked[index - 1] = true;
                }
            }
        }

        return true;
    }
}
