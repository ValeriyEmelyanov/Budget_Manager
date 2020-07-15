package budget.controller;

import budget.view.ConsoleView;
import budget.model.Model;
import budget.model.PurchaseItem;

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
        view.message("\nEnter purchase name:");
        String name = scanner.nextLine();
        view.message("Enter its price:");
        double price = Double.parseDouble(scanner.nextLine());
        model.addPurchase(name, price);
        view.message("Purchase was added!\n");
    }

    private void showPurchasesList() {
        List<PurchaseItem> purchasesList = model.getPurchasesList();
        if (purchasesList.isEmpty()) {
            view.message("\nPurchase list is empty\n");
        } else {
            view.list(purchasesList);
            view.messageWithSum("Total sum:", model.getTotalSum());
        }
    }

    private void balance() {
        view.messageWithSum("\nBalance:", model.getBalance());
    }

    private void addIncome() {
        view.message("\nEnter income:");
        double num = Double.parseDouble(scanner.nextLine());
        model.addIncome(num);
        view.message("Income was added!\n");
    }
}
