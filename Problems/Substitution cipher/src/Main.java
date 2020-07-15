import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        char[] keys = scanner.nextLine().toCharArray();
        char[] values = scanner.nextLine().toCharArray();
        if (keys.length != values.length) {
            throw new IllegalArgumentException("Wrong input!");
        }
        char[] toEncode = scanner.nextLine().toCharArray();
        char[] toDecode = scanner.nextLine().toCharArray();
        scanner.close();

        Map<Character, Character> encMap = new HashMap<>();
        Map<Character, Character> decMap = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            encMap.put(keys[i], values[i]);
            decMap.put(values[i], keys[i]);
        }

        encode(toEncode, encMap);
        System.out.println(String.valueOf(toEncode));

        encode(toDecode, decMap);
        System.out.println(String.valueOf(toDecode));
    }

    private static void encode(char[] toEncode, Map<Character, Character> encMap) {
        for (int i = 0; i < toEncode.length; i++) {
            toEncode[i] = encMap.get(toEncode[i]);
        }
    }
}