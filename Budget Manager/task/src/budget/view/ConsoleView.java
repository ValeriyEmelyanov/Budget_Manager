package budget.view;

import budget.model.Category;

import java.util.List;
import java.util.Map;

public class ConsoleView {
    public void menu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("7) Analyze (Sort)");
        System.out.println("0) Exit");
    }

    public void analysisMenu() {
        System.out.println();
        System.out.println("How do you want to sort?");
        System.out.println("1) Sort all purchases");
        System.out.println("2) Sort by type");
        System.out.println("3) Sort certain type");
        System.out.println("4) Back");
    }

    public void typeOfPurchaseMenu() {
        System.out.println();
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
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

    public void listTree(Map<Double, List<Category>> map) {
        for (Map.Entry<Double, List<Category>> entry : map.entrySet()) {
            Double sum = entry.getKey();
            String formatted = sum <= 0.001 ? "%s - $%.0f\n" : "%s - $%.2f\n";
            for (Category category : entry.getValue()) {
                System.out.printf(formatted, category.getName(), sum);
            }
        }
    }
}
