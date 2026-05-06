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
        int slot = scanner.nextInt(); 
        return slot; 
    }

    // UC4: Convert Slot Number (1–9) to Board Index (Row, Column)[cite: 10]
    public static int[] convertSlotToIndices(int slot) {
        // Adjust to zero-based indexing[cite: 10]
        int index = slot - 1; 
        
        // Mathematical Mapping using Division and Modulo[cite: 10]
        int row = index / 3;
        int col = index % 3;
        
        // Return the row & column generated as an array[cite: 10]
        return new int[]{row, col}; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // UC1
        char[][] board = new char[3][3];
        initializeBoard(board);
        printBoard(board);
        
        // UC2
        tossToDecideFirst();

        // Testing UC3 & UC4 integration
        if (currentPlayer.equals("Human")) {
            int chosenSlot = getUserInput(scanner);
            int[] indices = convertSlotToIndices(chosenSlot);
            
            System.out.println("Awesome, you selected slot: " + chosenSlot);
            System.out.println("This converts to -> Row: " + indices[0] + ", Column: " + indices[1]);
        } else {
            System.out.println("Computer is thinking...");
        }

        scanner.close();
    }
}