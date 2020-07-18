package budget.controller;

import budget.model.Category;
import budget.model.Model;
import budget.model.PurchaseItem;
import budget.view.ConsoleView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private static final String PATH_TO_STORAGE = "purchases.txt";
    private static final String DELIMITER = ";";

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
                case "5":
                    save();
                    break;
                case "6":
                    load();
                    break;
                case "7":
                    analyze();
                    break;
                default:
                    view.message("Wrong action! Try again");
            }
        }

        scanner.close();
    }

    private void analyze() {
        while (true) {
            view.analysisMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    sortAllPurchases();
                    break;
                case "2":
                    sortByType();
                    break;
                case "3":
                    sortCertainType();
                    break;
                case "4":
                    view.emptyLine();
                    return;
                default:
                    view.message("Invalid choice!\n");
            }
        }
    }

    private void sortAllPurchases() {
        List<PurchaseItem> list = model.getPurchasesList();
        if (list.isEmpty()) {
            view.message("\nPurchase list is empty!");
        } else {
            list.sort((item1, item2) -> Double.compare(item2.getPrice(), item1.getPrice()));

            view.message("\nAll:");
            view.list(list);
            view.messageWithSum("Total:", model.getTotalSum());
        }
    }

    private void sortByType() {
        view.message("\nTypes:");
        view.listTree(model.getSortedCategiriesWithSum());
        view.messageWithSum("Total sum:", model.getTotalSum());
    }

    private void sortCertainType() {
        view.typeOfPurchaseMenu();
        String choice = scanner.nextLine();
        if (choice.length() != 1 || !"1|2|3|4".contains(choice)) {
            view.message("Invalid choice");
            return;
        }

        Category category = Category.values()[Integer.parseInt(choice) - 1];
        List<PurchaseItem> list = model.getPurchasesList(category);
        if (list.isEmpty()) {
            view.message("\nPurchase list is empty!");
            return;
        }

        list.sort((item1, item2) -> Double.compare(item2.getPrice(), item1.getPrice()));
        view.message(String.format("\n%s:", category.getName()));
        view.list(list);
        view.messageWithSum("Total sum:", model.getTotalSum(category));
    }

    private void load() {
        File file = new File(PATH_TO_STORAGE);
        if (!file.exists()) {
            view.message("The file does not exist.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNextLine()) {
                view.message("The file is empty.");
                return;
            }

            model.setBalance(Double.parseDouble(scanner.nextLine().replaceAll(",", ".")));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int firstDelimiter = line.indexOf(DELIMITER);
                int lastDelimiter = line.lastIndexOf(DELIMITER);
                model.addPurchaseWithNoChangeBalance(
                        line.substring(firstDelimiter + 1, lastDelimiter),
                        Double.parseDouble(line.substring(lastDelimiter + 1).replaceAll(",", ".")),
                        Category.valueOf(line.substring(0, firstDelimiter))
                );
            }

            view.message("\nPurchases were loaded!\n");
        } catch (IOException e) {
            view.message("The file was not loaded: " + e.getMessage());
        }
    }

    private void save() {
        try (FileWriter writer = new FileWriter(PATH_TO_STORAGE)) {
            writer.write(String.format("%f\n", model.getBalance()));
            for (PurchaseItem item : model.getPurchasesList()) {
                writer.write(String.format("%s%s%s%s%f\n",
                        item.getCategory(),
                        DELIMITER,
                        item.getName(),
                        DELIMITER,
                        item.getPrice()));
            }

            view.message("\nPurchases were saved!\n");
        } catch (IOException e) {
            view.message("The file was not saved: " + e.getMessage());
        }
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
                view.message(String.format("\n%s:", category.getName()));
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
