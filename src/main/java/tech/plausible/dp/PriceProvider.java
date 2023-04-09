package tech.plausible.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PriceProvider {
    private final Map<PizzaType, Double> pizzaPriceStore = new HashMap<>();
    private final Map<Topping, Double> toppingPriceStore = new HashMap<>();

    public PriceProvider() {
        pizzaPriceStore.put(PizzaType.SMALL, 10.0);
        pizzaPriceStore.put(PizzaType.MEDIUM, 12.0);
        pizzaPriceStore.put(PizzaType.LARGE, 14.0);
        toppingPriceStore.put(Topping.CHEESE, 1.0);
        toppingPriceStore.put(Topping.PEPPERONI, 2.0);
        toppingPriceStore.put(Topping.HAM, 3.0);
    }

    public Double getPrice(Topping topping) {
        final Double price = this.toppingPriceStore.get(topping);
        if (Objects.isNull(price)) {
            throw new IllegalStateException("Topping not available");
        }
        return price;
    }

    public Double getPrice(PizzaType pizzaType) {
        final Double price = this.pizzaPriceStore.get(pizzaType);
        if (Objects.isNull(price)) {
            throw new IllegalStateException("Pizza not available");
        }
        return price;
    }
}
