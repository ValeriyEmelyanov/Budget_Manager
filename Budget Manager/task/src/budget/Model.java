package budget;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<String> purchasesList = new ArrayList<>();
    private long totalSum = 0;
    private long balance = 0;

    public void addIncome(double num) {
        balance += (long) (num * 100.);
    }

    public double getBalance() {
        return balance / 100.;
    }

    public List<String> getPurchasesList() {
        return new ArrayList<>(purchasesList);
    }

    public double getTotalSum() {
        return totalSum / 100.;
    }

    public void addPurchase(String name, double price) {
        purchasesList.add(String.format("%s $%.2f", name.trim(), price));
        totalSum += (long) (price * 100.);
        balance -= (long) (price * 100.);
        if (balance < 0) {
            balance = 0;
        }
    }
}
