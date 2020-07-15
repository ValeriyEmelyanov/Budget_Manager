package budget.view;

import java.util.List;

public class ConsoleView {
    public void menu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("0) Exit");
    }

    public void message(String message) {
        System.out.println(message);
    }

    public void messageWithSum(String message, double sum) {
        System.out.printf("%s $%.2f\n\n", message, sum);
    }

    public void list(List<?> list) {
        System.out.println();
        list.forEach(System.out::println);
    }
}
