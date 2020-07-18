import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("[\\u00A0\\s]+");
        scanner.close();

        int[] arr = new int[strings.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }

        int count = 0;
        int tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}