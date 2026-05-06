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
            System.out.println("Result: Human won the toss! You will play first as 'X'. Computer is 'O'.\n");
        } else {
            currentPlayer = "Computer";
            computerSymbol = 'X';
            playerSymbol = 'O';
            System.out.println("Result: Computer won the toss! Computer will play first as 'X'. You are 'O'.\n");
        }
    }

    // UC3: Accept User Slot Input
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

    // UC5: Validate User Move (Refactored for Logic Reuse)
    public static boolean isValidMove(char[][] board, int row, int col) {
        // Ensure the move is within bounds and the cell is empty
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            return board[row][col] == '-'; 
        }
        return false; 
    }

    // UC6: Place Move on Board
    public static void placeMove(char[][] board, int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // --- NEW: Refactored Human Turn ---
    public static void humanTurn(Scanner scanner, char[][] board) {
        boolean valid = false;
        while (!valid) {
            int chosenSlot = getUserInput(scanner);
            int[] indices = convertSlotToIndices(chosenSlot);
            int row = indices[0];
            int col = indices[1];
            
            // Using the shared validation logic
            if (isValidMove(board, row, col)) {
                System.out.println("Move accepted! Placing your symbol...");
                placeMove(board, row, col, playerSymbol); 
                printBoard(board);
                valid = true; 
            } else {
                // We handle the error printing here now, so the computer can fail silently!
                System.out.println("Invalid Move: Out of bounds or slot already taken! Try again.\n");
            }
        }
    }

    // --- NEW: UC7 Computer Makes a Random Move ---[cite: 12]
    public static void makeComputerMove(char[][] board) {
        Random random = new Random();
        boolean valid = false;
        
        System.out.println("Computer is thinking...");
        
        // Loop Until Valid[cite: 12]
        while (!valid) {
            // Generate random slot 1–9[cite: 12]
            int chosenSlot = random.nextInt(9) + 1; 
            int[] indices = convertSlotToIndices(chosenSlot);
            int row = indices[0];
            int col = indices[1];
            
            // Logic Reuse: Ensure move validity[cite: 12]
            if (isValidMove(board, row, col)) {
                System.out.println("Computer selected slot: " + chosenSlot);
                placeMove(board, row, col, computerSymbol);
                printBoard(board);
                valid = true; 
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];
        
        initializeBoard(board);
        printBoard(board);
        tossToDecideFirst();

        // Testing UC7: Let's do a sequence of two turns to prove both players can move!
        if (currentPlayer.equals("Human")) {
            humanTurn(scanner, board);
            makeComputerMove(board); // Computer responds[cite: 12]
        } else {
            makeComputerMove(board); // Computer goes first[cite: 12]
            humanTurn(scanner, board);
        }

        scanner.close();
    }
}