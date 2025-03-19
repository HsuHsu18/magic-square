import java.util.Scanner;

public class q1a_23121871 {
    private int size;
    private int[][] square;

    // Constructor to initialize and generate the magic square
    public q1a_23121871(int size) {
        this.size = size;
        this.square = new int[size][size];
        generateMagicSquare();
    }

    // Algorithm to generate an odd-order Magic Square
    private void generateMagicSquare() {
        int row = 0;
        int col = size / 2;  // Start from the middle of the first row

        for (int num = 1; num <= size * size; num++) {
            square[row][col] = num; // Place number

            // Compute next position
            int newRow = (row - 1 + size) % size;
            int newCol = (col + 1) % size;

            // If the new position is occupied, move down instead
            if (square[newRow][newCol] != 0) {
                row = (row + 1) % size;
            } else {
                row = newRow;
                col = newCol;
            }
        }
    }

    // Display the Magic Square
    public void displaySquare() {
        System.out.println("\nMagic Square:");
        for (int[] row : square) {
            for (int num : row) {
                System.out.printf("%4d", num);
            }
            System.out.println();
        }
    }

    // Main method to take user input and generate magic square
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size;

        // Loop until the user enters a valid input
        while (true) {
            System.out.print("Enter an odd integer for the magic square size: ");
            size = scanner.nextInt();

            if (size >= 3 && size % 2 != 0) {
                break; // Valid input, exit the loop
            }
            System.out.println("Error: Please enter an odd integer greater than or equal to 3.");
        }

        q1a_23121871 magicSquare = new q1a_23121871(size);
        magicSquare.displaySquare();
        scanner.close();
    }
}


