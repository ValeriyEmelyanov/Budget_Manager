package budget;

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
                    view.showMessage("\nBye!");
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
                    view.showMessage("Wrong action! Try again");
            }
        }

        scanner.close();
    }

    private void addPurchase() {
        view.showMessage("\nEnter purchase name:");
        String name = scanner.nextLine();
        view.showMessage("Enter its price:");
        double price = Double.parseDouble(scanner.nextLine());
        model.addPurchase(name, price);
        view.showMessage("Purchase was added!\n");
    }

    private void showPurchasesList() {
        List<String> purchasesList = model.getPurchasesList();
        if (purchasesList.isEmpty()) {
            view.showMessage("\nPurchase list is empty\n");
        } else {
            view.showList(purchasesList);
            view.showMessage(String.format("Total sum: $%.2f\n", model.getTotalSum()));
        }
    }

    private void balance() {
        view.showMessage(String.format("\nBalance: $%.2f\n", model.getBalance()));
    }

    private void addIncome() {
        view.showMessage("\nEnter income:");
        double num = Double.parseDouble(scanner.nextLine());
        model.addIncome(num);
        view.showMessage("Income was added!\n");
    }
}
