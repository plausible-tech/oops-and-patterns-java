package tech.plausible.dp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Pizza implements OrderDescriptor {
    private final PizzaType pizzaType;
    private final Map<ToppingType, Integer> toppingCountInfo;
    private final PriceProvider priceProvider;

    private final Map<ToppingType, Double> toppingCostInfo = new HashMap<>();


    public Pizza(PizzaRequest pizzaRequest, PriceProvider priceProvider) {
        this.pizzaType = pizzaRequest.getPizzaType();
        this.toppingCountInfo = pizzaRequest.getToppingCountInfo();
        this.priceProvider = priceProvider;

        this.toppingCountInfo.forEach((toppingType, count) -> {
            final Double cost = count * priceProvider.getPrice(toppingType);
            toppingCostInfo.put(toppingType, cost);
        });
    }

    private Integer getDiscountPercentage() {
        Integer discountPercentage = 0;
        if (this.pizzaType == PizzaType.SMALL) {
            discountPercentage = 5;
        } else if(this.pizzaType == PizzaType.MEDIUM) {
            discountPercentage = 10;
        } else {
            discountPercentage = 15;
        }

        double basePrice = this.getBasePrice();
        double toppingCost = this.getToppingCost();

        if (this.pizzaType == PizzaType.LARGE) {
            if (toppingCost > 0.2 * basePrice) {
                discountPercentage += 5;
            } else if (discountPercentage > 0.3 * basePrice) {
                discountPercentage += 10;
            }
        }

        if (this.pizzaType == PizzaType.MEDIUM) {
            if (toppingCost > 0.2 * basePrice) {
                discountPercentage += 3;
            } else if (discountPercentage > 0.3 * basePrice) {
                discountPercentage += 5;
            }
        }

        if (this.pizzaType == PizzaType.SMALL) {
            if (discountPercentage > 0.3 * basePrice) {
                discountPercentage += 5;
            }
        }

        return discountPercentage;
    }

    private Double getBasePrice() {
        return this.priceProvider.getPrice(this.pizzaType);
    }

    private Double getToppingCost() {
        Double toppingCost = 0.0;
        for (final Map.Entry<ToppingType, Double> entries: toppingCostInfo.entrySet()) {
            toppingCost += entries.getValue();
        }
        return toppingCost;
    }

    public Double getPrice() {
        Double basePrice = this.getBasePrice();
        Double toppingCost = this.getToppingCost();
        final BigDecimal priceWithoutDiscount = BigDecimal.valueOf(basePrice + toppingCost);
        return priceWithoutDiscount.subtract(BigDecimal.valueOf(this.getDiscount())).setScale(2).doubleValue();
    }

    public Double getDiscount() {
        final double basePrice = this.getBasePrice();
        final double toppingCost = this.getToppingCost();
        final BigDecimal discountPercentage = BigDecimal.valueOf(this.getDiscountPercentage());
        final BigDecimal priceWithoutDiscount = BigDecimal.valueOf(basePrice + toppingCost);
        return discountPercentage.divide(BigDecimal.valueOf(100))
                .multiply(priceWithoutDiscount)
                .setScale(2).doubleValue();
    }

    public String getPriceDescription() {
        final StringBuilder description = new StringBuilder();
        description.append(this.pizzaType.getValue());
        description.append(" pizza with - ");

        this.toppingCountInfo.forEach(((toppingType, count) -> {
            description.append(String.format("%d x %s ", count, toppingType.getValue()));
        }));

        description.append("@ ");
        description.append(this.getPrice());
        description.append(". Happy customer discount of ");
        description.append(this.getDiscount());

        return description.toString();
    }

}
