// Siqi Li
// CS 143
// Sudoku #1 – Board Setup //
// This program instantiates a SudokuBoard from a given file and prints the board to console.

// pre : args[0] (if provided) must be a valid .sdk file path;otherwise the file "data1.sdk" must exist in the working directory.
// post: constructs a SudokuBoard from the file and prints the board to stdout.
public class PlaySudoku {
    public static void main(String[] args) {
        // Accept file name via command-line or hard-code for testing
        String fileName = (args.length > 0) ? args[0] : "data1.sdk";
        SudokuBoard sb = new SudokuBoard(fileName);
        System.out.println(sb);  // implicitly calls toString()
    }
}

/*
 ----jGRASP exec: java PlaySudoku
 2 . . | 1 . 5 | . . 3 
 . 5 4 | . . . | 7 1 . 
 . 1 . | 2 . 3 | . 8 . 
 ------+-------+------
 6 . 2 | 8 . 7 | 3 . 4 
 . . . | . . . | . . . 
 1 . 5 | 3 . 9 | 8 . 6 
 ------+-------+------
 . 2 . | 7 . 1 | . 6 . 
 . 8 1 | . . . | 2 4 . 
 7 . . | 4 . 2 | . . 1 
 
 
  ----jGRASP: Operation complete.
 
 */