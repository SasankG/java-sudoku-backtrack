public class Sudokusolve {
    
    private int[][] board;

    // Set up game board format
    public Sudokusolve(int[][] board){
        // 9 x 9 board
       this.board = new int[9][9];

       // increment columns and rows of board
       for(int i = 0; i < 9; i++){
           for(int j = 0; j < 9; j++){
               this.board[i][j] = board[i][j];
           }
       }
    }

    // Check for possible numbers in rows, columns, and smaller 3x3 grid
    private boolean rowCheck(int row, int num){
        for(int i = 0; i < 9; i++){
            if(board[row][i] == num){
                return true;
            }
        }
        // default false means there is no constraint in the cell
        return false;
    }

    private boolean colCheck(int col, int num){
        for(int i = 0; i < 9; i++){
            if(board[i][col] == num){
                return true;
            }
        }
        return false;
    }

    private boolean boxCheck(int row, int col, int num){
        int rowStart = row - row % 3;
        int colStart = col - col% 3;
        for(int i = rowStart; i < rowStart + 3; i++){
            for(int j = colStart; j < colStart + 3; j++){
                if(board[i][j] == num){
                    return true;
                }
            }
        }
        return false;
    }

    // Method to check that there are no constraints in the row, column, or 3x3 grid
    // Runs only when the check methods return false indicating there are no constraints and no numbers in the cell
    private boolean setNum(int row, int col, int num){
        return !(
            rowCheck(row, num) || colCheck(col, num) || boxCheck(row, col, num)
        );
    }

    // Solve the sudoku
    public boolean solve(){
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                if(board[r][c] == 0){
                    // check for numbers in range
                    for(int num = 1; num <= 9; num++){
                        if(setNum(r,c,num)){
                            // set number if it passes all checks
                            board[r][c] = num;
                            // backtrack if we run into constraints, other wise return true to finish sudoku
                            if(solve()){
                                return true;
                            } else {
                                // reset the cell to 0 and backtrack
                                board[r][c] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        // true if solved
        return true;
    }

    // Print solved puzzle
    public void printSudoku(){
        for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
                System.out.print(" " + board[i][j]);
            }
			System.out.println();
		}
		
		System.out.println();
    }
    
    public static void main(String[] args){
        // Our game board 'The hardest sudoku board' - found online
        // 2D array, empty boxes are 0

        int[][] sudokuPuzzle =  {
            { 8, 0, 0, 0, 0, 0, 0, 0, 0 }, 
            { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
            { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
            { 0, 5, 0, 0, 0, 7, 0, 0, 0 }, 
            { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 3, 0 }, 
            { 0, 0, 1, 0, 0, 0, 0, 6, 8 }, 
            { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
            { 0, 9, 0, 0, 0, 0, 4, 0, 0 }, 
        };

        // getting execution time
        final long startTime = System.currentTimeMillis();
        // create new object
        Sudokusolve sudoku = new Sudokusolve(sudokuPuzzle);
        
        System.out.println("Solving this sudoku via recursion / backtracking");
        sudoku.printSudoku();
        
        // Run solve method
        if(sudoku.solve()){
            System.out.println("Solved sudoku");
            final long endTime = System.currentTimeMillis();
            // print solved sudoku
            sudoku.printSudoku();
            System.out.println("Total execution time: " + (endTime - startTime) + "ms");
        } else {
            System.out.println("This sudoku cannot be solved");
        }
    }
   
}


   
    