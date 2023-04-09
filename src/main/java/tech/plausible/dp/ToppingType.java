package tech.plausible.dp;

public enum ToppingType {
    CHEESE("Cheese"),
    PEPPERONI("Pepperoni"),
    HAM("Ham");

    private String toppingType;

    ToppingType(String toppingType) {
        this.toppingType = toppingType;
    }

    public String getValue() {
        return this.toppingType;
    }
}
