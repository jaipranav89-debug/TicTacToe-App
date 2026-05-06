public class TicTacToe {
    
    // 1. Initialize the board with dashes
    public static void initializeBoard(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-'; 
            }
        }
    }

    // 2. Print the board to the console
    public static void printBoard(char[][] board) {
        System.out.println("Current Board:");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    // 3. Main flow of UC1
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        initializeBoard(board);
        printBoard(board);
    }
}