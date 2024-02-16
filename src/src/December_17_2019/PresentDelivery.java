package December_17_2019;

import java.util.Scanner;

public class PresentDelivery {
    public static int santaRow;
    public static int santaCol;
    public static int presentsCount;
    public static int niceKids;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        presentsCount = Integer.parseInt(scanner.nextLine());
        int size = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][size];

        for (int row = 0; row < size; row++) {
            String[] matrixRow = scanner.nextLine().split(" ");
            for (int c = 0; c < matrixRow.length; c++) {
                matrix[row][c]= matrixRow[c].charAt(0);
                if(matrix[row][c] == 'S'){
                    santaRow = row;
                    santaCol = c;
                }
                if(matrix[row][c]=='V'){
                    niceKids++;
                }
            }
        }
        printMatrix(matrix);

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
