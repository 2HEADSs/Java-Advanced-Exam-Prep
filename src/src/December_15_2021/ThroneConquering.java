package December_15_2021;

import java.util.Scanner;

public class ThroneConquering {
    public static int playerRow;
    public static int playerCol;
    public static int helenRow;
    public static int helenCol;
    public static int energy;

    public static boolean hasWon = false;
    public static boolean isAlive = true;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        energy = Integer.parseInt(scanner.nextLine());
        int dimensions = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[dimensions][dimensions];


        for (int row = 0; row < dimensions; row++) {
            String matrixRow = scanner.nextLine();
            matrix[row] = matrixRow.toCharArray();
            if (matrixRow.contains("P")) {
                playerRow = row;
                playerCol = matrixRow.indexOf("P");
            }
            if (matrixRow.contains("H")) {
                helenRow = row;
                helenCol = matrixRow.indexOf("H");
            }
        }
        hasWon = playerRow == helenRow && playerCol == helenCol;

        while (energy > 0 && !hasWon && isAlive) {
            String[] commands = scanner.nextLine().split("\\s+");
            String direction = commands[0];
            int spartanRow = Integer.parseInt(commands[1]);
            int spartanCol = Integer.parseInt(commands[2]);
            matrix[spartanRow][spartanCol] = 'S';
            if (direction.equals("up")) {
                move(matrix, playerRow - 1, playerCol);
            } else if (direction.equals("down")) {
                move(matrix, playerRow + 1, playerCol);
            } else if (direction.equals("right")) {
                move(matrix, playerRow, playerCol + 1);
            } else {
                move(matrix, playerRow, playerCol - 1);
            }

        }
        if(!isAlive){
            System.out.printf("Paris died at %d;%d.%n", playerRow,playerCol);
        }else {
            System.out.printf("Paris has successfully abducted Helen! Energy left: %d%n", energy);

        }
        printMatrix(matrix);

    }

    private static void move(char[][] matrix, int nextRow, int nextCol) {
        energy = energy - 1;
        matrix[playerRow][playerCol]='-';
        if(isOutOfBounds(matrix,nextRow,nextCol)){
            if(energy<=0){
                isAlive = false;
                matrix[playerRow][playerCol] = 'X';
                return;
            }
           return;
        }
        if(energy<=0){
            isAlive=false;
            matrix[nextRow][nextCol]='X';
            playerRow = nextRow;
            playerCol=nextCol;
            return;
        }
        if(matrix[nextRow][nextCol]=='S'){
            energy = energy-2;
            if(energy<=0){
                matrix[nextRow][nextCol]= 'X';
                isAlive=false;
            }else {
                matrix[nextRow][nextCol] = '-';
                playerRow = nextRow;
                playerCol=nextCol;
            }
        }else if(matrix[nextRow][nextCol]=='H'){
            playerRow = nextRow;
            playerCol=nextCol;
            matrix[playerRow][playerCol]='-';
            hasWon=true;
        }else {
            matrix[playerRow][playerCol] = '-';
            playerRow=nextRow;
            playerCol=nextCol;
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
