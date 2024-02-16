package December_17_2019;

import java.util.Scanner;

public class PresentDelivery {
    public static int santaRow;
    public static int santaCol;
    public static int presentsCount;
    public static int kidsWithPresent;
    public static int niceKids;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        presentsCount = Integer.parseInt(scanner.nextLine());
        int size = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][size];

        for (int row = 0; row < size; row++) {
            String[] matrixRow = scanner.nextLine().split(" ");
            for (int c = 0; c < matrixRow.length; c++) {
                matrix[row][c] = matrixRow[c].charAt(0);
                if (matrix[row][c] == 'S') {
                    santaRow = row;
                    santaCol = c;
                }
                if (matrix[row][c] == 'V') {
                    niceKids++;
                }
            }
        }
        String command = scanner.nextLine();
        while (!command.equals("Christmas morning")) {
            if (command.equals("up")) {
                move(matrix, santaRow - 1, santaCol);
            } else if (command.equals("down")) {
                move(matrix, santaRow + 1, santaCol);
            } else if (command.equals("left")) {
                move(matrix, santaRow, santaCol - 1);
            } else if (command.equals("right")) {
                move(matrix, santaRow, santaCol + 1);
            }
            if (presentsCount <= 0) {
                break;
            }
            command = scanner.nextLine();
        }
        if (presentsCount <= 0) {
            System.out.println("Santa ran out of presents!");
        }
        printMatrix(matrix);
        if (kidsWithPresent >= niceKids) {
            System.out.println("Good job, Santa! " + kidsWithPresent + " happy nice kid/s.");
        } else {
            int diff = niceKids - kidsWithPresent;
            System.out.println("No presents for " + diff + " nice kid/s.");
        }

    }

    private static void move(char[][] matrix, int nextRow, int nextCol) {
        if (isOutOfBounds(matrix, nextRow, nextCol)) {
            return;
        }
        matrix[santaRow][santaCol] = '-';
        if (matrix[nextRow][nextCol] == 'V') {
            presentsCount--;
            kidsWithPresent++;
            santaRow = nextRow;
            santaCol = nextCol;
            matrix[santaRow][santaCol] = 'S';

        } else if (matrix[nextRow][nextCol] == 'C') {
            eatCookie(matrix, nextRow, nextCol);
            santaRow = nextRow;
            santaCol = nextCol;
            matrix[santaRow][santaCol] = 'S';
        } else {
            santaRow = nextRow;
            santaCol = nextCol;
            matrix[santaRow][santaCol] = 'S';
        }
    }

    private static boolean isOutOfBounds(char[][] matrix, int r, int c) {
        return r < 0 || r >= matrix.length || c < 0 || c >= matrix[r].length;
    }

    private static void eatCookie(char[][] matrix, int currentRow, int currentCol) {
        //down
        if (matrix[currentRow + 1][currentCol] == 'V' || matrix[currentRow + 1][currentCol] == 'X') {
            matrix[currentRow + 1][currentCol] = '-';
            presentsCount--;
            kidsWithPresent++;
            //up
        } else if (matrix[currentRow - 1][currentCol] == 'V' || matrix[currentRow - 1][currentCol] == 'X') {
            matrix[currentRow - 1][currentCol] = '-';
            presentsCount--;
            kidsWithPresent++;
            //left
        } else if (matrix[currentRow][currentCol - 1] == 'V' || matrix[currentRow][currentCol - 1] == 'X') {
            matrix[currentRow][currentCol - 1] = '-';
            presentsCount--;
            kidsWithPresent++;
            //right
        } else if (matrix[currentRow][currentCol + 1] == 'V' || matrix[currentRow][currentCol + 1] == 'X') {
            matrix[currentRow][currentCol + 1] = '-';
            presentsCount--;
            kidsWithPresent++;
        }
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
