import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int[] array = inputArray();
        System.out.println(getIndexOfMax(array));
    }

    private static int getIndexOfMax(int[] array) {
        int index = 0;
        int max = array[index];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                index = i;
                max = array[i];
            }
        }
        return index;
    }

    private static int[] inputArray() {
        final Scanner scanner = new Scanner(System.in);
        final int len = scanner.nextInt();
        final int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();
        return array;
    }
}