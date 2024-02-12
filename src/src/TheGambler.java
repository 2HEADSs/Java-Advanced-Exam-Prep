import java.util.Scanner;

public class TheGambler {

    public static int rowGambler;
    public static int colGambler;
    public static int amount = 100;
    public static boolean printMatrix = true;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dimensions = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[dimensions][dimensions];

        for (int r = 0; r < dimensions; r++) {
            String row = scanner.nextLine();
            matrix[r] = row.toCharArray();
            int index = row.indexOf('G');
            if (index != -1) {
                rowGambler = r;
                colGambler = index;
            }
        }

        boolean stilMoving = true;
        String direction = scanner.nextLine();
        while (stilMoving && !direction.equals("end")) {
            if (direction.equals("up")) {
                stilMoving = move(matrix, rowGambler - 1, colGambler);
            } else if (direction.equals("down")) {
                stilMoving = move(matrix, rowGambler + 1, colGambler);
            } else if (direction.equals("right")) {
                stilMoving = move(matrix, rowGambler, colGambler + 1);
            } else {
                stilMoving = move(matrix, rowGambler, colGambler - 1);
            }

            direction = scanner.nextLine();
        }

        if (printMatrix) {
            System.out.println("End of the game. Total amount: " + amount + "$");
            for (char[] chars : matrix) {
                for (char c : chars) {
                    System.out.print(c);
                }
                System.out.println();
            }
        }
    }

    private static boolean move(char[][] matrix, int nextRow, int nextCol) {

        if (!isInBound(matrix, nextRow, nextCol)) {
            matrix[rowGambler][colGambler] = '-';
            System.out.println("Game over! You lost everything!");
            printMatrix = false;
            return false;
        }

        if (matrix[nextRow][nextCol] == 'W') {
            amount += 100;
        } else if (matrix[nextRow][nextCol] == 'P') {
            amount -= 200;
            if (amount <= 0) {
                System.out.println("Game over! You lost everything!");
                printMatrix = false;
                return false;
            }
        } else if (matrix[nextRow][nextCol] == 'J') {
            amount += 100000;
            System.out.println("You win the Jackpot!");
            matrix[rowGambler][colGambler] = '-';
            rowGambler = nextRow;
            colGambler = nextCol;
            matrix[rowGambler][colGambler] = 'G';
            return false;
        }

        matrix[rowGambler][colGambler] = '-';
        rowGambler = nextRow;
        colGambler = nextCol;
        matrix[rowGambler][colGambler] = 'G';
        return true;
    }

    private static boolean isInBound(char[][] matrix, int r, int c) {
        return r >= 0 && r < matrix.length && c >= 0 && c < matrix[r].length;

    }
}
