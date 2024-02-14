package October_26_2019;

import java.util.Scanner;

public class BookWorm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String startText = scanner.nextLine();
        StringBuilder textBuilder = new StringBuilder(startText);
        int size = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[size][size];
        fillMatrix(matrix, scanner);

        int playerRow = -1;//на кой ред се намира играча
        int playerCol = -1;//на коя колона се намира играча

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (matrix[row][col].equals("P")) {
                    playerRow = row;
                    playerCol = col;
                }
            }
        }


        String command = scanner.nextLine();
        while (!command.equals("end")) {
            boolean isOutside = false;
            int startRow = playerRow;//ред от който тръгва
            int startCol = playerCol;//колона от който тръгва
            switch (command) {
                case "up":
                    //нагоре
                    playerRow--;
                    if (playerRow < 0) {
                        playerRow++;
                        isOutside = true;
                    }
                    break;
                case "down":
                    //надолу
                    playerRow++;
                    if (playerRow >= size) {
                        playerRow--;
                        isOutside = true;
                    }
                    break;
                case "left":
                    //ляво
                    playerCol--;
                    if (playerCol < 0) {
                        playerCol++;
                        isOutside = true;
                    }
                    break;
                case "right":
                    //дясно
                    playerCol++;
                    if (playerCol >= size) {
                        playerCol--;
                        isOutside = true;
                    }
                    break;
            }
            if (!isOutside) {
                String currentText = matrix[playerRow][playerCol];
                if (!currentText.equals("-")) {
                    //буква
                    textBuilder.append(currentText);
                }
                matrix[playerRow][playerCol] = "P"; //мястото на което отива
                matrix[startRow][startCol] = "-";//мястото от което е тръгнал
            } else {
                if (startText.length() > 0) {
                    textBuilder.deleteCharAt(textBuilder.length() - 1);
                }
            }
            command = scanner.nextLine();
        }

        System.out.println(textBuilder);
        printMatrix(matrix);
    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = scanner.nextLine().split("");
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }


}

