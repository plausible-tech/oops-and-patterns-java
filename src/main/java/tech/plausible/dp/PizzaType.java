package tech.plausible.dp;

public enum PizzaType {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    private String pizzaType;

    PizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public String getValue() {
        return this.pizzaType;
    }
}

