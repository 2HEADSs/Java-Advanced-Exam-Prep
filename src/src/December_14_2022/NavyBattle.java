package December_14_2022;

import java.util.Scanner;

public class NavyBattle {
    public static int rowSubmarine;
    public static int colSubmarine;
    public static int countMineStrikes = 0;
    public static int countDestroyedShips = 0;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dimension = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[dimension][dimension];

        for (int r = 0; r < dimension; r++) {
            String row = scanner.nextLine();
            matrix[r] = row.toCharArray();
            int index = row.indexOf('S');
            if (index != -1) {
                rowSubmarine = r;
                colSubmarine = index;
            }

        }
        boolean stilMoving = true;
        while (stilMoving) {
            String direction = scanner.nextLine();
            if (direction.equals("up")) {
                stilMoving = move(matrix, rowSubmarine - 1, colSubmarine);
            } else if (direction.equals("down")) {
                stilMoving = move(matrix, rowSubmarine + 1, colSubmarine);
            } else if (direction.equals("right")) {
                stilMoving = move(matrix, rowSubmarine, colSubmarine + 1);
            } else {
                stilMoving = move(matrix, rowSubmarine, colSubmarine - 1);
            }

        }

        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();

        }

    }

    private static boolean move(char[][] matrix, int nextRow, int nextCol) {
        matrix[rowSubmarine][colSubmarine] = '-';

        if (matrix[nextRow][nextCol] == '*') {
            rowSubmarine = nextRow;
            colSubmarine = nextCol;
            countMineStrikes++;
            if (countMineStrikes == 3) {
                System.out.printf("Mission failed, U-9 disappeared! Last known coordinates [%d, %d]!%n"
                        , rowSubmarine, colSubmarine);
                matrix[nextRow][nextCol] = 'S';
                return false;
            }
        }

        if (matrix[nextRow][nextCol] == 'C') {
            countDestroyedShips++;
            if (countDestroyedShips == 3) {
                System.out.println("Mission accomplished, U-9 has destroyed all battle cruisers of the enemy!");
                matrix[nextRow][nextCol] = 'S';
                return false;
            }

        }
        matrix[nextRow][nextCol] = '-';
        rowSubmarine = nextRow;
        colSubmarine = nextCol;
        return true;
    }

}
