package October_21_2023;

import java.util.Scanner;

public class FishingCompetition2 {
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


        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }

    }
}
