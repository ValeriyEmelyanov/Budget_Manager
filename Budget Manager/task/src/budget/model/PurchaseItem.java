package budget.model;

public class PurchaseItem {
    private final String name;
    private final long price;
    private final Category category;

    public PurchaseItem(String name, double price, Category category) {
        this.name = name;
        this.price = (long) (price * 100.);
        this.category = category;
    }

    long getLongPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", name, price / 100.);
    }
}
