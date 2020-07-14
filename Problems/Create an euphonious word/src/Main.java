import java.util.*;

public class Main {
    private static final String VOWELS = "aeiouy";

    public static void main(String[] args) {
        final String word = getWord();
        System.out.println(getInsCount(word));
    }

    private static int getInsCount(String word) {
        final char[] chars = word.toLowerCase().toCharArray();
        int vowelCount = 0;
        int consonantCount = 0;
        int insCount = 0;
        for (char c : chars) {
            if (isVowel(c)) {
                vowelCount++;
                consonantCount = 0;
            } else {
                vowelCount = 0;
                consonantCount++;
            }

            if (vowelCount == 3) {
                insCount++;
                vowelCount = 1;
            }

            if (consonantCount == 3) {
                insCount++;
                consonantCount = 1;
            }
        }
        return insCount;
    }

    private static String getWord() {
        final Scanner scanner = new Scanner(System.in);
        final String word = scanner.nextLine();
        scanner.close();
        return word;
    }

    private static boolean isVowel(char c) {
        return VOWELS.indexOf(c) >= 0;
    }
}