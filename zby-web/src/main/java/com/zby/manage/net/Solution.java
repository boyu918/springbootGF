package com.zby.manage.net;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public Solution() {
    }
    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean[] oneXie = new boolean[9];
    private boolean[] secondXie= new boolean[9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                }else if(i == j ){
                    int digit = board[i][j] - '0' - 1;
                    oneXie[digit] = true;
                }else if (i+j == 8){
                    int digit = board[i][j] - '0' - 1;
                    secondXie[digit] = true;
                }else {
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }
        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                if (i ==j && oneXie[digit]){
                    continue;
                }
                if ( i +j == 8 && secondXie[digit]){
                    continue;
                }
                if (i == j){
                    oneXie[digit] = true;
                }
                if (i+j == 8){
                    secondXie[digit] = true;
                }
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
                if (i == j){
                    oneXie[digit] = false;
                }
                if (i+j == 8){
                    secondXie[digit] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        char [][]board = new char[][]{
                {'.','.','6','.','.','9','.','7','.',},
                {'.','.','.','.','.','.','9','.','6',},
                {'7','.','.','.','.','3','.','1','.',},
                {'.','.','.','.','6','.','8','.','1',},
                {'.','.','.','1','.','8','.','.','.',},
                {'1','.','4','.','3','.','.','.','.',},
                {'.','7','.','8','.','.','.','.','3',},
                {'8','.','3','.','.','.','.','.','.',},
                {'.','2','.','3','.','.','5','.','.',}};
        Solution solution = new Solution();
        solution.solveSudoku(board);
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
