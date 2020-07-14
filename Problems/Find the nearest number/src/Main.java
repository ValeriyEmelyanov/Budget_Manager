import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[] numbers = inputArray(scanner);
        final int number = scanner.nextInt();
        scanner.close();

        List<Integer> result = getMinDistances(numbers, number);

        result.stream().sorted().forEach(n -> System.out.print(n + " "));
    }

    private static List<Integer> getMinDistances(int[] numbers, int number) {
        List<Integer> result = new ArrayList<>();
        int distance = Integer.MAX_VALUE;
        int curDist;
        for (int i : numbers) {
            curDist = Math.abs(i - number);
            if (curDist < distance) {
                distance = curDist;
                result.clear();
                result.add(i);
            } else if (curDist == distance) {
                result.add(i);
            }
        }
        return result;
    }

    private static int[] inputArray(Scanner scanner) {
        String[] numbers = scanner.nextLine().split("\\s+");
        return Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
    }
}