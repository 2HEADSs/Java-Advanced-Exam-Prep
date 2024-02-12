package June_17_2023;

import java.util.Arrays;
import java.util.Scanner;

public class MouseInTheKitchen {
    private static int mouseCurrentRow = 0;
    public static int mouseCurrentCol = 0;
    public static int countCheese = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        char[][] matrix = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            String row = scanner.nextLine();
            matrix[r] = row.toCharArray();
            int indexOfMouse = row.indexOf("M");
            if (indexOfMouse != -1) {
                mouseCurrentRow = r;
                mouseCurrentCol = indexOfMouse;
            }
            for (int i = 0; i < matrix[r].length; i++) {
                if (matrix[r][i] == 'C') {
                    countCheese++;
                }
            }
        }
        boolean stilMoving = true;
        while (stilMoving) {
            String direction = scanner.nextLine();
            if (direction.equals("danger")) {
                System.out.println("Mouse will come back later!");
                break;
            } else if (direction.equals("up")) {
                stilMoving = moving(matrix, mouseCurrentRow - 1, mouseCurrentCol);
            } else if (direction.equals("down")) {
                stilMoving = moving(matrix, mouseCurrentRow + 1, mouseCurrentCol);
            } else if (direction.equals("left")) {
                stilMoving = moving(matrix, mouseCurrentRow, mouseCurrentCol - 1);
            } else if (direction.equals("right")) {
                stilMoving = moving(matrix, mouseCurrentRow, mouseCurrentCol + 1);
            }
        }

        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }

    }

    private static boolean moving(char[][] matrix, int nextRow, int nextCol) {
        if (!isInBound(matrix, nextRow, nextCol)) {
            System.out.println("No more cheese for tonight!");
            return false;
        }
        if (matrix[nextRow][nextCol] == '*') {
            matrix[mouseCurrentRow][mouseCurrentCol] = '*';
            mouseCurrentRow = nextRow;
            mouseCurrentCol = nextCol;
            matrix[mouseCurrentRow][mouseCurrentCol] = 'M';
            return true;
        } else if (matrix[nextRow][nextCol] == '@') {
            return true;
        } else if (matrix[nextRow][nextCol] == 'T') {
            matrix[mouseCurrentRow][mouseCurrentCol] = '*';
            mouseCurrentRow = nextRow;
            mouseCurrentCol = nextCol;
            matrix[mouseCurrentRow][mouseCurrentCol] = 'M';
            System.out.println("Mouse is trapped!");
            return false;
        } else {
            matrix[mouseCurrentRow][mouseCurrentCol] = '*';
            mouseCurrentRow = nextRow;
            mouseCurrentCol = nextCol;
            matrix[mouseCurrentRow][mouseCurrentCol] = 'M';
            countCheese--;
            if (countCheese <= 0) {
                System.out.println("Happy mouse! All the cheese is eaten, good night!");
                return false;
            }
            return true;
        }
    }

    private static boolean isInBound(char[][] matrix, int r, int c) {
        return r >= 0 && r < matrix.length && c >= 0 && c < matrix[r].length;
    }
}
