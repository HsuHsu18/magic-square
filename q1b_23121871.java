import java.util.Random;
import java.util.Scanner;

//Class Declaration and Attributes
public class q1b_23121871 {
    private int size; // size of the magic square
    private int[][] magicSquare;// 2D array that stores the correct magic square
    private int[][] shuffledSquare;
    private int moveCount;
    
    // Constructor to initialize the game
    public q1b_23121871(int size) {
        this.size = size;
        this.magicSquare = generateMagicSquare(size);
        this.shuffledSquare = copySquare(magicSquare);
        this.moveCount = 0;
        shuffleSquare();
    }
    
    // Generating the Magic Square (Siamese Method)
    private int[][] generateMagicSquare(int n) {
        int[][] square = new int[n][n];
        int row = 0;
        int col = n / 2;

        for (int num = 1; num <= n * n; num++) {
            square[row][col] = num;
            int newRow = (row - 1 + n) % n;
            int newCol = (col + 1) % n;

            if (square[newRow][newCol] != 0) {
                row = (row + 1) % n;
            } else {
                row = newRow;
                col = newCol;
            }
        }
        return square;
    }

    // Copying the Magic Square 
    private int[][] copySquare(int[][] source) {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(source[i], 0, copy[i], 0, size);
        }
        return copy;
    }

    // Shuffling the Magic Square
    private void shuffleSquare() {
        Random rand = new Random();
        for (int i = 0; i < size * size; i++) {
            int row1 = rand.nextInt(size);
            int col1 = rand.nextInt(size);
            int row2 = row1, col2 = col1;

            switch (rand.nextInt(4)) {
                case 0: row2 = (row1 - 1 + size) % size; break;
                case 1: row2 = (row1 + 1) % size; break;
                case 2: col2 = (col1 - 1 + size) % size; break;
                case 3: col2 = (col1 + 1) % size; break;
            }

            int temp = shuffledSquare[row1][col1];
            shuffledSquare[row1][col1] = shuffledSquare[row2][col2];
            shuffledSquare[row2][col2] = temp;
        }
    }

    // Displaying the shuffled square
    public void displayShuffledSquare() {
        System.out.println("\nShuffled Magic Square:");
        for (int[] row : shuffledSquare) {
            for (int num : row) {
                System.out.printf("%4d ", num);
            }
            System.out.println();
        }
    }

    // User game play
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (!isSolved()) {
            displayShuffledSquare();

            try {
                System.out.print("Enter row, col, direction (U/D/L/R): ");

                if (!scanner.hasNextInt()) { // Ensure valid row input
                    throw new Exception("Invalid row! Enter a number.");
                }
                int row = scanner.nextInt() - 1;

                if (!scanner.hasNextInt()) { // Ensure valid column input
                    throw new Exception("Invalid column! Enter a number.");
                }
                int col = scanner.nextInt() - 1;

                char direction = scanner.next().toUpperCase().charAt(0);

                if (row < 0 || row >= size || col < 0 || col >= size) {
                    System.out.println("Error: Row and column must be within the square!");
                    continue;
                }

                if (!makeMove(row, col, direction)) {
                    System.out.println("Invalid move. Try again.");
                } else {
                    moveCount++;
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Clear invalid input
            }
        }
        System.out.println("Congratulations! You solved the puzzle in " + moveCount + " moves.");
        scanner.close();
    }

    // Making a Move 
    private boolean makeMove(int row, int col, char direction) {
        int newRow = row, newCol = col;
        switch (direction) {
            case 'U': newRow = row - 1; break;
            case 'D': newRow = row + 1; break;
            case 'L': newCol = col - 1; break;
            case 'R': newCol = col + 1; break;
            default: return false;
        }

        if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
            int temp = shuffledSquare[row][col];
            shuffledSquare[row][col] = shuffledSquare[newRow][newCol];
            shuffledSquare[newRow][newCol] = temp;
            return true;
        } else {
            System.out.println("Error: Move out of bounds!");
        }
        return false;
    }

    // Checking if the Puzzle is Solved 
    private boolean isSolved() {
        return java.util.Arrays.deepEquals(shuffledSquare, magicSquare);
    }

    // Program Execution
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size;
    
    while (true) {
        System.out.print("Enter an odd integer for the magic square size: ");
        if (!scanner.hasNextInt()) { // Check if input is not an integer
            System.out.println("Invalid input! Please enter a valid odd integer.");
            scanner.next(); // Clear invalid input
            continue;
        }

        size = scanner.nextInt();

        if (size < 3 || size % 2 == 0) {
            System.out.println("Error: Please enter an odd integer greater than or equal to 3.");
        } else {
            break; // Valid input, exit loop
        }
    }

    q1b_23121871 game = new q1b_23121871(size);
    game.playGame();
    scanner.close();
    }
}
