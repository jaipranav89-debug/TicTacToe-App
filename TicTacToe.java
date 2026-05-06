import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    
    // Game State Variables
    static String currentPlayer;
    static char playerSymbol;
    static char computerSymbol;
    
    // Game State Flags
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

    // UC3: Accept User Input
    public static int getUserInput(Scanner scanner) {
        System.out.print("Enter a slot number (1-9): ");
        return scanner.nextInt(); 
    }

    // UC4: Convert Slot Number to Indices
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

    // UC6: Place Move
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

    // --- NEW: UC9 Check Winning Condition ---[cite: 12]
    public static boolean checkWin(char[][] board, char symbol) {
        // Loop-Based Checks for Rows and Columns[cite: 12]
        for (int i = 0; i < 3; i++) {
            // Check all 3 rows[cite: 12]
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true; 
            }
            // Check all 3 columns[cite: 12]
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true; 
            }
        }
        
        // Logical Conditions for Diagonals[cite: 12]
        // Check main diagonal (top-left to bottom-right)
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
        // Check anti-diagonal (top-right to bottom-left)
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }
        
        return false; // If no patterns match, no win yet[cite: 12]
    }

    // UC10 (Coming next): Dummy checkDraw
    public static boolean checkDraw(char[][] board) {
        return false; 
    }

    // Switch Turn logic
    public static void switchTurn() {
        if (currentPlayer.equals("Human")) {
            currentPlayer = "Computer";
        } else {
            currentPlayer = "Human";
        }
    }

    // UC8: Main Game Loop
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];
        
        initializeBoard(board);
        printBoard(board);
        tossToDecideFirst();

        while (!gameWon && !boardFull) {
            
            if (currentPlayer.equals("Human")) {
                humanTurn(scanner, board);
                if (checkWin(board, playerSymbol)) {
                    gameWon = true;
                    System.out.println("Congratulations! You won the game!");
                    break;
                }
            } else {
                makeComputerMove(board);
                if (checkWin(board, computerSymbol)) {
                    gameWon = true;
                    System.out.println("Game Over! The Computer won!");
                    break;
                }
            }
            
            if (checkDraw(board)) {
                boardFull = true;
                System.out.println("It's a Draw! The board is full.");
                break;
            }
            
            switchTurn();
        }
        
        System.out.println("Thank you for playing Tic-Tac-Toe!");
        scanner.close();
    }
}