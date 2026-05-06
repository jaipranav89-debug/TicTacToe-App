import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    
    // Game State Variables
    static String currentPlayer;
    static char playerSymbol;
    static char computerSymbol;
    
    // UC1: Initialize the board
    public static void initializeBoard(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-'; 
            }
        }
    }

    // UC1: Print the board
    public static void printBoard(char[][] board) {
        System.out.println("Current Board:");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // UC2: Toss to decide who plays first
    public static void tossToDecideFirst() {
        System.out.println("Tossing a coin to decide who plays first...");
        Random random = new Random();
        int toss = random.nextInt(2);

        if (toss == 0) {
            currentPlayer = "Human";
            playerSymbol = 'X';
            computerSymbol = 'O';
            System.out.println("Result: Human won the toss! You will play first as 'X'. Computer is 'O'.");
        } else {
            currentPlayer = "Computer";
            computerSymbol = 'X';
            playerSymbol = 'O';
            System.out.println("Result: Computer won the toss! Computer will play first as 'X'. You are 'O'.");
        }
    }

    // UC3: Accept User Slot Input (1-9)
    public static int getUserInput(Scanner scanner) {
        System.out.print("Enter a slot number (1-9): ");
        return scanner.nextInt(); 
    }

    // UC4: Convert Slot Number to Board Index
    public static int[] convertSlotToIndices(int slot) {
        int index = slot - 1; 
        int row = index / 3;
        int col = index % 3;
        return new int[]{row, col}; 
    }

    // UC5: Validate User Move
    public static boolean isValidMove(char[][] board, int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (board[row][col] == '-') {
                return true; 
            } else {
                System.out.println("Invalid Move: That slot is already taken! Try again.");
                return false; 
            }
        } else {
            System.out.println("Invalid Move: Slot out of bounds! Please enter a number between 1 and 9.");
            return false; 
        }
    }

    // UC6: Place Move on Board
    public static void placeMove(char[][] board, int row, int col, char symbol) {
        // Update the board array with the given symbol[cite: 11]
        board[row][col] = symbol;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Setup Board
        char[][] board = new char[3][3];
        initializeBoard(board);
        printBoard(board);
        
        tossToDecideFirst();

        // Testing UC6 integration
        if (currentPlayer.equals("Human")) {
            boolean valid = false;
            
            while (!valid) {
                int chosenSlot = getUserInput(scanner);
                int[] indices = convertSlotToIndices(chosenSlot);
                int row = indices[0];
                int col = indices[1];
                
                // If the move is valid, place the symbol and update the board[cite: 11]
                if (isValidMove(board, row, col)) {
                    System.out.println("Move accepted! Placing your symbol...");
                    
                    // Call our new reusable method[cite: 11]
                    placeMove(board, row, col, playerSymbol); 
                    
                    printBoard(board);
                    valid = true; 
                }
            }
        } else {
            System.out.println("Computer is thinking...");
        }

        scanner.close();
    }
}
}