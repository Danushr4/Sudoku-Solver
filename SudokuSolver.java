import java.util.*;

public class SudokuSolver {
    private static final int BOARD_SIZE = 9;
    private char[][] board;

    public SudokuSolver(char[][] initialBoard) {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(initialBoard[i], 0, this.board[i], 0, BOARD_SIZE);
        }
    }

    public boolean solve() {
        return solveSudoku();
    }

    private boolean solveSudoku() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(row, col, c)) {
                            board[row][col] = c;
                            if (solveSudoku()) {
                                return true;
                            } else {
                                board[row][col] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, char c) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][col] == c || board[row][i] == c ||
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) {
                return false;
            }
        }
        return true;
    }

    public void printSolution() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        char[][] initialBoard = {
                {'9', '5', '7', '.', '1', '3', '.', '8', '4'},
                {'4', '8', '3', '.', '5', '7', '1', '.', '6'},
                {'.', '1', '2', '.', '4', '9', '5', '3', '7'},
                {'1', '7', '.', '3', '.', '4', '9', '.', '2'},
                {'5', '.', '4', '9', '7', '.', '3', '6', '.'},
                {'3', '.', '9', '5', '.', '8', '7', '.', '1'},
                {'8', '4', '5', '7', '9', '.', '6', '1', '3'},
                {'.', '9', '1', '.', '3', '6', '.', '7', '5'},
                {'7', '.', '6', '1', '8', '5', '4', '.', '9'}
        };

        SudokuSolver solver = new SudokuSolver(initialBoard);
        if (solver.solve()) {
            System.out.println("Sudoku puzzle solved:");
            solver.printSolution();
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }
}
