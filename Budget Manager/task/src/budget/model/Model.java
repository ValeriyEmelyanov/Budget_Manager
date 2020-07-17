package budget.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private final List<PurchaseItem> purchasesList = new ArrayList<>();
    private double balance = 0;

    public void addIncome(double num) {
        balance += num;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<PurchaseItem> getPurchasesList() {
        return new ArrayList<>(purchasesList);
    }

    public List<PurchaseItem> getPurchasesList(Category category) {
        return purchasesList.stream()
                .filter(item -> item.getCategory() == category)
                .collect(Collectors.toList());
    }

    public double getTotalSum() {
        double totalSum = 0;
        for (PurchaseItem item : purchasesList) {
            totalSum += item.getPrice();
        }
        return totalSum;
    }

    public double getTotalSum(Category category) {
        double totalSum = 0;
        for (PurchaseItem item : purchasesList) {
            if (item.getCategory() != category) {
                continue;
            }
            totalSum += item.getPrice();
        }
        return totalSum;
    }

    public void addPurchase(String name, double price, Category category) {
        purchasesList.add(new PurchaseItem(name, price, category));
        balance -= price;
        if (balance < 0) {
            balance = 0;
        }
    }

    public void addPurchaseWithNoChangeBalance(String name, double price, Category category) {
        purchasesList.add(new PurchaseItem(name, price, category));
    }
}
