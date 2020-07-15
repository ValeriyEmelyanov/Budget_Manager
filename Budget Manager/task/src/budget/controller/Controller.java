package budget.controller;

import budget.model.Category;
import budget.model.Model;
import budget.model.PurchaseItem;
import budget.view.ConsoleView;

import java.util.List;
import java.util.Scanner;

public class Controller {
    private final ConsoleView view = new ConsoleView();
    private final Model model = new Model();
    final Scanner scanner = new Scanner(System.in);

    public void run() {

        boolean exit = false;
        String action;
        while (!exit) {
            view.menu();
            action = scanner.nextLine();
            switch (action) {
                case "0":
                    exit = true;
                    view.message("\nBye!");
                    break;
                case "1":
                    addIncome();
                    break;
                case "2":
                    addPurchase();
                    break;
                case "3":
                    showPurchasesList();
                    break;
                case "4":
                    balance();
                    break;
                default:
                    view.message("Wrong action! Try again");
            }
        }

        scanner.close();
    }

    private void addPurchase() {
        while (true) {
            view.message("\nChoose the type of purchase");
            view.categories();
            int categoryChoice = 0;
            try {
                categoryChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                view.message("Invalid input!");
                continue;
            }

            if (categoryChoice == 5) {
                view.emptyLine();
                return;
            }

            if (categoryChoice < 1 || categoryChoice > 5) {
                view.message("Invalid choice!");
                continue;
            }

            Category category = Category.values()[categoryChoice - 1];
            view.message("\nEnter purchase name:");
            String name = scanner.nextLine();
            view.message("Enter its price:");
            double price = Double.parseDouble(scanner.nextLine());
            model.addPurchase(name, price, category);
            view.message("Purchase was added!");
        }
    }

    private void showPurchasesList() {
        if (model.getPurchasesList().isEmpty()) {
            view.message("\nPurchase list is empty!\n");
            return;
        }

        while (true) {
            view.message("\nChoose the type of purchase");
            view.categoriesWithItemAll();
            int categoryChoice = 0;
            try {
                categoryChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                view.message("Invalid input!");
                continue;
            }
            if (categoryChoice == 6) {
                view.emptyLine();
                return;
            }

            if (categoryChoice < 1 || categoryChoice > 6) {
                view.message("Invalid choice!\n");
                continue;
            }

            if (categoryChoice == 5) {
                view.message("\nAll:");
                view.list(model.getPurchasesList());
                view.messageWithSum("Total sum:", model.getTotalSum());
            } else {
                Category category = Category.values()[categoryChoice - 1];
                List<PurchaseItem> purchasesList = model.getPurchasesList(category);
                view.categoryName(category);
                if (purchasesList.isEmpty()) {
                    view.message("\nPurchase list is empty!");
                } else {
                    view.list(purchasesList);
                    view.messageWithSum("Total sum:", model.getTotalSum(category));
                }
            }
        }

    }

    private void balance() {
        view.messageWithSum("\nBalance:", model.getBalance());
        view.emptyLine();
    }

    private void addIncome() {
        view.message("\nEnter income:");
        double num = Double.parseDouble(scanner.nextLine());
        model.addIncome(num);
        view.message("Income was added!\n");
    }
}
