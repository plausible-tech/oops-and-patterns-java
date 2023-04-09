package tech.plausible.dp;

public enum Topping {
    CHEESE("Cheese"),
    PEPPERONI("Pepperoni"),
    HAM("Ham");

    private String topping;

    Topping(String topping) {
        this.topping = topping;
    }

    public String getValue() {
        return this.topping;
    }
}
