package budget.model;

public class PurchaseItem {
    private final String name;
    private final double price;
    private final Category category;

    public PurchaseItem(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", name, price);
    }
}
