package October_21_2023;

import java.util.Map;
import java.util.Scanner;

public class FishingCompetition {
    public static int rolVessel;
    public static int colVessel;
    public static boolean printMatrix = true;
    public static int fishAmount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimensions = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[dimensions][dimensions];
        for (int r = 0; r < dimensions; r++) {
            String row = scanner.nextLine();
            matrix[r] = row.toCharArray();
            int index = row.indexOf('S');
            if (index != -1) {
                rolVessel = r;
                colVessel = index;
            }
        }

        boolean stilMoving = true;
        String direction = scanner.nextLine();
        while (stilMoving && !direction.equals("collect the nets")) {
            if (direction.equals("up")) {
                if (isInBound(matrix, rolVessel - 1, colVessel)) {
                    stilMoving = move(matrix, rolVessel - 1, colVessel);
                } else {
                    stilMoving = move(matrix, matrix.length - 1, colVessel);
                }
            } else if (direction.equals("down")) {
                if (isInBound(matrix, rolVessel + 1, colVessel)) {
                    stilMoving = move(matrix, rolVessel + 1, colVessel);
                } else {
                    stilMoving = move(matrix, 0, colVessel);
                }
            } else if (direction.equals("right")) {
                if (isInBound(matrix, rolVessel, colVessel + 1)) {
                    stilMoving = move(matrix, rolVessel, colVessel + 1);
                } else {
                    stilMoving = move(matrix, rolVessel, 0);
                }
            } else {
                if (isInBound(matrix, rolVessel, colVessel - 1)) {
                    stilMoving = move(matrix, rolVessel, matrix[rolVessel].length);
                } else {
                    stilMoving = move(matrix, rolVessel, colVessel);
                }
            }

            direction = scanner.nextLine();
        }


        if (printMatrix) {
            int diff = fishAmount - 20;
            if (fishAmount < 20) {
                System.out.printf("You didn't catch enough fish and didn't reach the quota!" +
                                " You need %d tons of fish more.%n",
                        Math.abs(diff));
            } else {
                System.out.println("Success! You managed to reach the quota!");
            }
            System.out.printf("Amount of fish caught: %d tons.%n", fishAmount);
            for (char[] chars : matrix) {
                for (char c : chars) {
                    System.out.print(c);
                }
                System.out.println();
            }
        }

    }

    private static boolean move(char[][] matrix, int nextRow, int nextCol) {
        if (matrix[nextRow][nextCol] == 'W') {
            rolVessel = nextRow;
            colVessel = nextCol;
            System.out.printf(
                    "You fell into a whirlpool! The ship sank and you lost the fish you caught. Last coordinates of the ship: [%d,%d]%n"
                    , rolVessel, colVessel);
            printMatrix = false;

            return false;
        }
        char current = matrix[nextRow][nextCol];

        if (Character.isDigit(current)) {
            fishAmount += Integer.parseInt(String.valueOf(current));
            matrix[rolVessel][colVessel] = '-';
            rolVessel = nextRow;
            colVessel = nextCol;
            matrix[rolVessel][colVessel] = 'S';
            return true;
        }
        matrix[rolVessel][colVessel] = '-';
        rolVessel = nextRow;
        colVessel = nextCol;
        matrix[rolVessel][colVessel] = 'S';
        return true;
    }

    private static boolean isInBound(char[][] matrix, int r, int c) {
        if (r >= 0 && r < matrix.length && c >= 0 && c < matrix[r].length) {
            return true;
        } else {
            return false;
        }


    }
}
