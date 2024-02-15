package April_12_2023;

import java.util.Scanner;

public class TheSquirrel {
    public static int squirrellRow;
    public static int squirrellCol;
    public static int collectedHazelnuts = 0;
    public static boolean stepsOut = false;
    public static boolean trapped = false;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(", ");

        char[][] matrix = new char[size][size];

        for (int row = 0; row < size; row++) {
            String matrixRow = scanner.nextLine();
            matrix[row] = matrixRow.toCharArray();

            if (matrixRow.contains("s")) {
                squirrellRow = row;
                squirrellCol = matrixRow.indexOf("s");
            }
        }
        for (String command : commands) {
            if (collectedHazelnuts >= 3 || stepsOut || trapped) {
                break;
            }
            if (command.equals("up")) {
                move(matrix, squirrellRow - 1, squirrellCol);
            } else if (command.equals("down")) {
                move(matrix, squirrellRow + 1, squirrellCol);
            } else if (command.equals("left")) {
                move(matrix, squirrellRow, squirrellCol - 1);
            } else if (command.equals("right")) {
                move(matrix, squirrellRow, squirrellCol + 1);
            }

        }
        if (collectedHazelnuts >= 3) {
            System.out.println("Good job! You have collected all hazelnuts!");
        } else if (trapped) {
            System.out.println("Unfortunately, the squirrel stepped on a trap...");
        } else if (stepsOut) {
            System.out.println("The squirrel is out of the field.");
        } else {
            System.out.println("There are more hazelnuts to collect.");
        }
        System.out.println("Hazelnuts collected: " + collectedHazelnuts);
//        printMatrix(matrix);

    }

    private static void move(char[][] matrix, int nextRow, int nextCol) {
        if (isOutOfBounds(matrix, nextRow, nextCol)) {
            matrix[squirrellRow][squirrellCol] = '*';
            stepsOut = true;
            return;
        }
        if (matrix[nextRow][nextCol] == 'h') {
            matrix[nextRow][nextCol] = '*';
            collectedHazelnuts++;
            squirrellRow = nextRow;
            squirrellCol = nextCol;
//            matrix[squirrellRow][squirrellCol] = 's';
        } else if (matrix[nextRow][nextCol] == 't') {
            matrix[squirrellRow][squirrellCol] = '*';
            trapped = true;
            return;
        } else {
            matrix[nextRow][nextCol] = '*';
            squirrellRow = nextRow;
            squirrellCol = nextCol;
        }
    }

    private static boolean isOutOfBounds(char[][] matrix, int r, int c) {
        return r < 0 || r >= matrix.length || c < 0 || c >= matrix[r].length;
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
