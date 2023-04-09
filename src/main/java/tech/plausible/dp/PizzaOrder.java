package tech.plausible.dp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PizzaOrder {
    private String orderId;
    private List<PizzaRequest> pizzaRequests;
    private List<Pizza> pizzas;

    public PizzaOrder(List<PizzaRequest> pizzaRequests, PriceProvider priceProvider) {
        this.orderId = UUID.randomUUID().toString();
        this.pizzaRequests = pizzaRequests;
        this.pizzas = new ArrayList<>();

        this.pizzaRequests.forEach(pizzaRequest -> {
            pizzas.add(new Pizza(pizzaRequest, priceProvider));
        });
    }

    public String getDescription() {
        final StringBuilder description = new StringBuilder();

        description.append(String.format("OrderDescriptor Id: %s \n", this.orderId));
        description.append(String.format("==================================================== \n", this.orderId));
        description.append("\n");

        Double totalPrice = 0.0;
        for (Pizza pizza : this.pizzas) {
            description.append(pizza.getPriceDescription());
            description.append("\n");
            totalPrice += pizza.getPrice();
        }
        BigDecimal formattedValue = BigDecimal.valueOf(totalPrice);
        formattedValue = formattedValue.setScale(2, RoundingMode.HALF_UP);

        description.append("\n");
        description.append("Total: ");
        description.append(formattedValue);
        return description.toString();
    }

    public List<Pizza> getPizzas() {
        return this.pizzas;
    }
}
