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

    // UC4: Convert Slot Number (1–9) to Board Index
    public static int[] convertSlotToIndices(int slot) {
        int index = slot - 1; 
        int row = index / 3;
        int col = index % 3;
        return new int[]{row, col}; 
    }

    // UC5: Validate User Move[cite: 10]
    public static boolean isValidMove(char[][] board, int row, int col) {
        // Ensure the move is within bounds (0-2)[cite: 10]
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            // Ensure the cell is completely empty[cite: 10]
            if (board[row][col] == '-') {
                return true; // Move accepted[cite: 10]
            } else {
                System.out.println("Invalid Move: That slot is already taken! Try again.");
                return false; // Move rejected[cite: 10]
            }
        } else {
            System.out.println("Invalid Move: Slot out of bounds! Please enter a number between 1 and 9.");
            return false; // Move rejected[cite: 10]
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Setup Board
        char[][] board = new char[3][3];
        initializeBoard(board);
        
        // Let's artificially fill a spot just to test our validation!
        board[0][0] = 'X'; // Slot 1 is now taken
        printBoard(board);
        
        tossToDecideFirst();

        // Testing UC5 integration with a while loop
        if (currentPlayer.equals("Human")) {
            boolean valid = false;
            
            // Keep asking until they give us a good move
            while (!valid) {
                int chosenSlot = getUserInput(scanner);
                int[] indices = convertSlotToIndices(chosenSlot);
                int row = indices[0];
                int col = indices[1];
                
                // Perform validation[cite: 10]
                if (isValidMove(board, row, col)) {
                    System.out.println("Move accepted! Placing your symbol...");
                    board[row][col] = playerSymbol; // Place the symbol
                    printBoard(board);
                    valid = true; // Exit the loop
                }
            }
        } else {
            System.out.println("Computer is thinking...");
        }

        scanner.close();
    }
}