package budget.model;

public class PurchaseItem {
    private final String name;
    private final long price;

    public PurchaseItem(String name, double price) {
        this.name = name;
        this.price = (long) (price * 100.);
    }

    long getLongPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", name, price / 100.);
    }
}
