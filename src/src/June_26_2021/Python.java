package June_26_2021;

import java.util.Scanner;

public class Python {
    public static int pythonRow;
    public static int pythonCol;
    public static int length = 1;
    public static int allFood;
    public static int leftFood;
    public static boolean isDead = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(", ");

        char[][] matrix = new char[size][size];

        for (int row = 0; row < size; row++) {
            String[] matrixRow = scanner.nextLine().split(" ");
            for (int c = 0; c < matrixRow.length; c++) {
                matrix[row][c]= matrixRow[c].charAt(0);
                if(matrix[row][c] == 's'){
                    pythonRow = row;
                    pythonCol = c;
                }
                if(matrix[row][c]=='f'){
                    allFood++;
                    leftFood++;
                }
            }
        }

        for (String command : commands) {
            if (isDead) {
                break;
            }
            if (command.equals("up")) {
                move(matrix, pythonRow - 1, pythonCol);
            } else if (command.equals("down")) {
                move(matrix, pythonRow + 1, pythonCol);
            } else if (command.equals("left")) {
                move(matrix, pythonRow, pythonCol - 1);
            } else if (command.equals("right")) {
                move(matrix, pythonRow, pythonCol + 1);
            }
        }
        if (isDead) {
            System.out.println("You lose! Killed by an enemy!");
        } else {
            if (leftFood > 0) {
                System.out.println("You lose! There is still " + leftFood + " food to be eaten.");
            } else {
                System.out.println("You win! Final python length is " + length);
            }
        }
    }

    private static void move(char[][] matrix, int nextRow, int nextCol) {
        if (isOutOfBounds(matrix, nextRow, nextCol)) {
            if (nextRow < 0 || nextRow >= matrix.length) {
                nextRow = nextRow < 0 ? matrix.length - 1 : 0;
            } else {
                nextCol = nextCol < 0 ? matrix[nextRow].length - 1 : 0;
            }
        }
        matrix[pythonRow][pythonCol] = '*';
        if (matrix[nextRow][nextCol] == 'e') {
            isDead = true;
        } else if (matrix[nextRow][nextCol] == 'f') {
            length++;
            leftFood--;
            pythonRow = nextRow;
            pythonCol = nextCol;
            matrix[pythonRow][pythonCol] = 's';
        } else {
            pythonRow = nextRow;
            pythonCol = nextCol;
            matrix[pythonRow][pythonCol] = 's';
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