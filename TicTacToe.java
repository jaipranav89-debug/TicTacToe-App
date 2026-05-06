import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    
    // Game State Variables
    static String currentPlayer;
    static char playerSymbol;
    static char computerSymbol;
    
    // Game State Flags for the Loop
    static boolean gameWon = false;
    static boolean boardFull = false;
    
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

    // UC5: Validate Move
    public static boolean isValidMove(char[][] board, int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            return board[row][col] == '-'; 
        }
        return false; 
    }

    // UC6: Place Move on Board
    public static void placeMove(char[][] board, int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // Human Turn Logic
    public static void humanTurn(Scanner scanner, char[][] board) {
        boolean valid = false;
        while (!valid) {
            int chosenSlot = getUserInput(scanner);
            int[] indices = convertSlotToIndices(chosenSlot);
            int row = indices[0];
            int col = indices[1];
            
            if (isValidMove(board, row, col)) {
                System.out.println("Move accepted! Placing your symbol...");
                placeMove(board, row, col, playerSymbol); 
                printBoard(board);
                valid = true; 
            } else {
                System.out.println("Invalid Move: Out of bounds or slot already taken! Try again.\n");
            }
        }
    }

    // UC7: Computer Turn Logic
    public static void makeComputerMove(char[][] board) {
        Random random = new Random();
        boolean valid = false;
        
        System.out.println("Computer is thinking...");
        
        while (!valid) {
            int chosenSlot = random.nextInt(9) + 1; 
            int[] indices = convertSlotToIndices(chosenSlot);
            int row = indices[0];
            int col = indices[1];
            
            if (isValidMove(board, row, col)) {
                System.out.println("Computer selected slot: " + chosenSlot);
                placeMove(board, row, col, computerSymbol);
                printBoard(board);
                valid = true; 
            }
        }
    }

    // --- NEW: Dummy methods for Win/Draw checking ---
    // (We will build the real logic for these in UC9 and UC10!)
    public static boolean checkWin(char[][] board, char symbol) {
        return false; // Dummy return
    }

    public static boolean checkDraw(char[][] board) {
        return false; // Dummy return
    }

    // --- NEW: Turn Switching logic ---
    public static void switchTurn() {
        if (currentPlayer.equals("Human")) {
            currentPlayer = "Computer";
        } else {
            currentPlayer = "Human";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];
        
        initializeBoard(board);
        printBoard(board);
        tossToDecideFirst();

        // --- NEW: UC8 Continuous Turn-Based Game Loop ---
        // Loop continues until a win or draw is detected
        while (!gameWon && !boardFull) {
            
            // 1. Take the turn
            if (currentPlayer.equals("Human")) {
                humanTurn(scanner, board);
                
                // Check if Human won or drew
                if (checkWin(board, playerSymbol)) {
                    gameWon = true;
                    System.out.println("Congratulations! You won!");
                    break;
                }
            } else {
                makeComputerMove(board);
                
                // Check if Computer won or drew
                if (checkWin(board, computerSymbol)) {
                    gameWon = true;
                    System.out.println("Game Over! The Computer won!");
                    break;
                }
            }
            
            // Check for a draw after ANY move
            if (checkDraw(board)) {
                boardFull = true;
                System.out.println("It's a Draw! The board is full.");
                break;
            }
            
            // 2. Switch Turn if no win/draw
            switchTurn();
        }
        
        System.out.println("Thank you for playing Tic-Tac-Toe!");
        scanner.close();
    }
}