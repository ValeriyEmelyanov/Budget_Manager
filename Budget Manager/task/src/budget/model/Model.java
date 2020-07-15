package budget.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<PurchaseItem> purchasesList = new ArrayList<>();
    private long balance = 0;

    public void addIncome(double num) {
        balance += (long) (num * 100.);
    }

    public double getBalance() {
        return balance / 100.;
    }

    public List<PurchaseItem> getPurchasesList() {
        return new ArrayList<>(purchasesList);
    }

    public double getTotalSum() {
        long totalSum = 0;
        for (PurchaseItem item : purchasesList) {
            totalSum += item.getLongPrice();
        }
        return totalSum / 100.;
    }

    public void addPurchase(String name, double price) {
        purchasesList.add(new PurchaseItem(name, price));
        balance -= (long) (price * 100.);
        if (balance < 0) {
            balance = 0;
        }
    }
}
