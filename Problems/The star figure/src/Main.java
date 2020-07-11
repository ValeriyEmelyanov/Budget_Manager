import java.util.Arrays;
import java.util.Scanner;

class Main {
    private static final String BACKDROP = ".";
    private static final String BRUSH = "*";

    public static void main(String[] args) {
        final int size = getMatrixSize();
        String[][] matrix = new String[size][size];

        fill(matrix, BACKDROP);
        drawLine(matrix, size / 2, BRUSH);
        drawColumn(matrix, size / 2, BRUSH);
        drawLeftDiagonal(matrix, BRUSH);
        drawRightDiagonal(matrix, BRUSH);

        printMatrix(matrix);
    }

    private static void drawRightDiagonal(String[][] matrix, String s) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            matrix[i][size - 1 - i] = s;
        }
    }

    private static void drawLeftDiagonal(String[][] matrix, String s) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][i] = s;
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (String[] strings : matrix) {
            for (String s : strings) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    private static void drawColumn(String[][] matrix, int column, String s) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[column][i] = s;
        }
    }

    private static void drawLine(String[][] matrix, int line, String s) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][line] = s;
        }
    }

    private static void fill(String[][] matrix, String s) {
        for (String[] strings : matrix) {
            Arrays.fill(strings, s);
        }
    }

    private static int getMatrixSize() {
        final Scanner scanner = new Scanner(System.in);
        final int size = scanner.nextInt();
        scanner.close();
        return size;
    }
}