package EXAM;

import java.util.Scanner;

public class Matrix_2 {
    public static int jetRow;
    public static int jetCol;
    public static int enemyCount;
    public static int shootEnemy;
    public static boolean isAlive;
    public static int jetPoints;
    public static boolean hasWin;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][size];

        hasWin = false;
        isAlive = true;
        jetPoints = 300;
        enemyCount = 4;

        for (int row = 0; row < size; row++) {
            String[] matrixRow = scanner.nextLine().split("");
            for (int c = 0; c < matrixRow.length; c++) {
                matrix[row][c] = matrixRow[c].charAt(0);
                if (matrix[row][c] == 'J') {
                    jetRow = row;
                    jetCol = c;
                }
//                if (matrix[row][c] == 'E') {
//                    enemyCount++;
//                }
            }
        }

        String command = scanner.nextLine();
        while (isAlive && jetPoints > 0) {
            if (command.equals("up")) {
                move(matrix, jetRow - 1, jetCol);
            } else if (command.equals("down")) {
                move(matrix, jetRow + 1, jetCol);
            } else if (command.equals("left")) {
                move(matrix, jetRow, jetCol - 1);
            } else if (command.equals("right")) {
                move(matrix, jetRow, jetCol + 1);
            }

            if (jetPoints <= 0) {
                break;
            }
            if (enemyCount <= 0) {
                break;
            }
            command = scanner.nextLine();
        }
        if (enemyCount > 0 && jetPoints <= 0) {
            System.out.printf("Mission failed, your jetfighter was shot down! Last coordinates [%d, %d]!%n", jetRow, jetCol);
        }
        if(enemyCount<=0){
            System.out.println("Mission accomplished, you neutralized the aerial threat!");
        }
        printMatrix(matrix);
    }

    private static void move(char[][] matrix, int nextRow, int nextCol) {
        matrix[jetRow][jetCol] = '-';
        if (matrix[nextRow][nextCol] == 'E') {
            jetRow = nextRow;
            jetCol = nextCol;
            matrix[jetRow][jetCol] = 'J';
            if (enemyCount == 1) {
                enemyCount--;
                hasWin = true;
            } else {
                jetPoints -= 100;
                if (jetPoints <= 0) {
                    isAlive = false;
                } else {
                    enemyCount--;
                }
            }
        } else if (matrix[nextRow][nextCol] == 'R') {
            jetPoints = 300;
            jetRow = nextRow;
            jetCol = nextCol;
            matrix[jetRow][jetCol] = 'J';
        } else {
            jetRow = nextRow;
            jetCol = nextCol;
            matrix[jetRow][jetCol] = 'J';
        }
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
