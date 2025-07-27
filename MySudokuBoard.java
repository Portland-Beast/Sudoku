// Siqi Li
// CS 143
// Sudoku #2 
//
// This class reads a 9×9 Sudoku puzzle from a .sdk file, 
// stores it in a 2D array (0 = empty), and provides methods 
// to check the validity of the board and whether it is solved.

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Scanner;

public class MySudokuBoard {

    // Field: 9x9 board, 0 means empty
    private int[][] board;

    // Constructor
    // PRE: fileName must be a valid .sdk file with 9 lines, each containing 9 characters (0-9 or '.')
    // POST: Initializes the board with values from the file
    public MySudokuBoard(String fileName) {
        board = new int[9][9];
        try (Scanner in = new Scanner(new File(fileName))) {
            for (int row = 0; row < 9; row++) {
                String line = in.nextLine().trim();
                for (int col = 0; col < 9; col++) {
                    char ch = line.charAt(col);
                    board[row][col] = (ch == '.') ? 0 : ch - '0';
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // toString
    // PRE: None
    // POST: Returns a formatted string of the Sudoku board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 9; r++) {
            if (r % 3 == 0 && r != 0) sb.append("------+-------+------\n");
            for (int c = 0; c < 9; c++) {
                if (c % 3 == 0 && c != 0) sb.append("| ");
                sb.append(board[r][c] == 0 ? "." : board[r][c]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // isValid
    // PRE: board must be initialized
    // POST: Returns true if the board is valid (no duplicates in rows, columns, or mini-squares)
    public boolean isValid() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] < 0 || board[r][c] > 9) return false;  // Invalid value
            }
        }
        for (int i = 0; i < 9; i++) {
            if (!isValidRow(i) || !isValidColumn(i)) return false;
        }
        for (int i = 0; i < 9; i++) {
            if (!isValidMiniSquare(i)) return false;
        }
        return true;
    }

    // Helper: Check if a row is valid
    private boolean isValidRow(int rowIndex) {
        Set<Integer> seen = new HashSet<>();
        for (int col = 0; col < 9; col++) {
            int cell = board[rowIndex][col];
            if (cell != 0 && !seen.add(cell)) return false;
        }
        return true;
    }

    // Helper: Check if a column is valid
    private boolean isValidColumn(int colIndex) {
        Set<Integer> seen = new HashSet<>();
        for (int row = 0; row < 9; row++) {
            int cell = board[row][colIndex];
            if (cell != 0 && !seen.add(cell)) return false;
        }
        return true;
    }

    // Helper: Check if a mini-square is valid
    private boolean isValidMiniSquare(int index) {
        int startRow = (index / 3) * 3;
        int startCol = (index % 3) * 3;
        Set<Integer> seen = new HashSet<>();
        
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                int cell = board[startRow + r][startCol + c];
                if (cell != 0 && !seen.add(cell)) return false;
            }
        }
        return true;
    }

    // isSolved
    // PRE: board must be initialized
    // POST: Returns true if the board is solved (valid and all numbers 1-9 appear exactly once)
    public boolean isSolved() {
        if (!isValid()) return false;

        Map<Integer, Integer> numberCount = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int cell = board[i][j];
                if (cell != 0) numberCount.put(cell, numberCount.getOrDefault(cell, 0) + 1);
            }
        }

        for (int num = 1; num <= 9; num++) {
            if (numberCount.getOrDefault(num, 0) != 9) return false;
        }
        return true;
    }
}
