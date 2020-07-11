package budget;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        long intTotal = 0;
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            stringBuilder.append(input).append("\n");
            intTotal += Double.parseDouble(input.substring(input.lastIndexOf("$") + 1)) * 100;
        }
        scanner.close();

        System.out.println(stringBuilder);
        System.out.println(String.format("Total: $%.2f", intTotal / 100.));
    }
}
