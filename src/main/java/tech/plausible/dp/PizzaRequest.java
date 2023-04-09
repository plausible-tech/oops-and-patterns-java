package tech.plausible.dp;

import java.util.Map;
import java.util.Objects;

public class PizzaRequest {
    private PizzaType pizzaType;
    private Map<Topping, Integer> toppingCountInfo;

    public PizzaRequest(PizzaType pizzaType, Map<Topping, Integer> toppingCountInfo) {
        this.pizzaType = pizzaType;
        this.toppingCountInfo = toppingCountInfo;
    }

    public PizzaType getPizzaType() {
        return pizzaType;
    }

    public Map<Topping, Integer> getToppingCountInfo() {
        return toppingCountInfo;
    }

    public static PizzaRequestBuilder builder() {
        return new PizzaRequestBuilder();
    }

    public static class PizzaRequestBuilder {
        private PizzaType pizzaType;
        private Map<Topping, Integer> toppingCountInfo;


        public PizzaRequestBuilder withPizzaType(PizzaType pizzaType) {
            this.pizzaType = pizzaType;
            return this;
        }


        public PizzaRequestBuilder withToppingCountInfo(Map<Topping, Integer> toppingCountInfo) {
            this.toppingCountInfo = toppingCountInfo;
            return this;
        }

        public PizzaRequest build() {
            Objects.requireNonNull(this.pizzaType);
            Objects.requireNonNull(this.toppingCountInfo);
            if (this.toppingCountInfo.size() == 0) {
                throw new IllegalStateException("Toppings are required.");
            }
            return new PizzaRequest(this.pizzaType, this.toppingCountInfo);
        }
    }
}
