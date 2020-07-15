import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        int dictLines = Integer.parseInt(scanner.nextLine());
        Set<String> dict = new HashSet<>(dictLines);
        for (int i = 0; i < dictLines; i++) {
            dict.add(scanner.nextLine().toLowerCase());
        }

        int wordsLines = Integer.parseInt(scanner.nextLine());
        Set<String> erroneous = new HashSet<>();
        for (int i = 0; i < wordsLines; i++) {
            String[] words = scanner.nextLine().toLowerCase().split("\\s+");
            for (String word : words) {
                if (!dict.contains(word)) {
                    erroneous.add(word);
                }
            }
        }

        scanner.close();

        erroneous.forEach(System.out::println);
    }
}