import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] array = getArray();
        System.out.println(sortAndCountSwaps(array));
    }

    private static int sortAndCountSwaps(int[] array) {
        int count = 0;
        int tmp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] < array[j + 1]) {
                    tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                    count++;
                }
            }
        }
        return count;
    }

    private static int[] getArray() {
        final Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("[\\u00A0\\s]+");
        scanner.close();

        int[] array = new int[strings.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(strings[i]);
        }
        return array;
    }
}