package October_21_2023;

import java.util.Scanner;

import static java.lang.Character.isDigit;

public class FishingCompetition {
    public static int rowVessel;
    public static int colVessel;
    public static boolean fallIntoWhirlpool = false;
    public static int caughtFish = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dimension = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[dimension][dimension];

        for (int r = 0; r < dimension; r++) {
            String row = scanner.nextLine();
            matrix[r] = row.toCharArray();
            int index = row.indexOf('S');
            if (index != -1) {
                rowVessel = r;
                colVessel = index;
            }
        }
        String command = scanner.nextLine();
        while (!command.equals("collect the nets") && !fallIntoWhirlpool) {
            if (command.equals("up")) {
                move(matrix, rowVessel - 1, colVessel);
            } else if (command.equals("down")) {
                move(matrix, rowVessel + 1, colVessel);
            } else if (command.equals("left")) {
                move(matrix, rowVessel, colVessel - 1);
            } else if (command.equals("right")) {
                move(matrix, rowVessel, colVessel + 1);
            }
            command = scanner.nextLine();
        }
        if (fallIntoWhirlpool) {
            System.out.printf("You fell into a whirlpool! The ship sank and you lost the fish you caught. Last coordinates of the ship: [%d,%d]%n", rowVessel, colVessel);
        }else {
        if (caughtFish >= 20 ) {
            System.out.println("Success! You managed to reach the quota!");
        } else if (caughtFish < 20 && caughtFish >= 0 ) {
            int diff = 20 - caughtFish;
            System.out.printf("You didn't catch enough fish and didn't reach the quota! " +
                    "You need %d tons of fish more.%n", diff);
        }
        if (caughtFish > 0) {
            System.out.printf("Amount of fish caught: %d tons.%n", caughtFish);
        }
            printMatrix(matrix);

        }

    }

    private static void move(char[][] matrix, int nextRow, int nextCol) {
        matrix[rowVessel][colVessel] = '-';
        if (isOutOfBounds(matrix, nextRow, nextCol)) {
            if (nextRow < 0 || nextRow >= matrix.length) {
                nextRow = nextRow < 0 ? matrix.length - 1 : 0;
            } else {
                nextCol = nextCol < 0 ? matrix[nextRow].length - 1 : 0;
            }
        }
        if (matrix[nextRow][nextCol] == 'W') {
            fallIntoWhirlpool = true;
            rowVessel = nextRow;
            colVessel = nextCol;
        } else if (isDigit(matrix[nextRow][nextCol])) {
            int currentTons = Integer.parseInt(String.valueOf(matrix[nextRow][nextCol]));
            caughtFish = caughtFish + currentTons;
            rowVessel = nextRow;
            colVessel = nextCol;
            matrix[rowVessel][colVessel] = 'S';
        } else {
            rowVessel = nextRow;
            colVessel = nextCol;
            matrix[rowVessel][colVessel] = 'S';
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
