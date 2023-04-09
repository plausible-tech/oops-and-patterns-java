package tech.plausible.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaCenter {
    public static void main(String[] args) {
        final PriceProvider priceProvider = new PriceProvider();

        final Map<ToppingType, Integer> cheeseTopping = new HashMap<>();
        cheeseTopping.put(ToppingType.CHEESE, 2);

        PizzaRequest smallCheeseOnlyPizza = PizzaRequest.builder()
                .withPizzaType(PizzaType.SMALL)
                .withToppingCountInfo(cheeseTopping)
                .build();

        final Map<ToppingType, Integer> cheeseAndHamTopping = new HashMap<>();
        cheeseAndHamTopping.put(ToppingType.CHEESE, 2);
        cheeseAndHamTopping.put(ToppingType.HAM, 2);

        PizzaRequest largeCheeseOnlyPizza = PizzaRequest.builder()
                .withPizzaType(PizzaType.LARGE)
                .withToppingCountInfo(cheeseAndHamTopping)
                .build();

        List<PizzaRequest> pizzaRequests = new ArrayList<>();
        pizzaRequests.add(smallCheeseOnlyPizza);
        pizzaRequests.add(largeCheeseOnlyPizza);

        PizzaOrder pizzaOrder = new PizzaOrder(pizzaRequests, priceProvider);

        System.out.println();
        System.out.println(pizzaOrder.getDescription());

    }
}
