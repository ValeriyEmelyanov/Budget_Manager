package budget.view;

import budget.model.Category;

import java.util.List;

public class ConsoleView {
    public void menu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("0) Exit");
    }

    public void message(String message) {
        System.out.println(message);
    }

    public void messageWithSum(String message, double sum) {
        System.out.printf("%s $%.2f\n", message, sum);
    }

    public void emptyLine() {
        System.out.println();
    }

    public void list(List<?> list) {
        list.forEach(System.out::println);
    }

    public void categoryName(Category category) {
        String name = category.name();
        System.out.printf("\n%s%s:\n", name.substring(0, 1), name.substring(1).toLowerCase());
    }

    public void categories() {
        categories(false);
    }

    public void categoriesWithItemAll() {
        categories(true);
    }

    private void categories(boolean isItemAll) {
        for (Category category : Category.values()) {
            System.out.printf("%d) %s\n", category.ordinal() + 1, category.name());
        }
        int itemNumber = Category.values().length;
        if (isItemAll) {
            System.out.printf("%d) %s\n", ++itemNumber, "All");
        }
        System.out.printf("%d) %s\n", ++itemNumber, "Back");
    }
}
