package tech.plausible.d1_01;

public class Runner {
    public static void main(String[] args) {
        BiCycle heroBiCycle = new BiCycle("hero", "black");
        BiCycle aOneBiCycle = new BiCycle("A1", "red");

        System.out.println();
        System.out.println();

        System.out.println(String.format("Brand: %s and Color: %s", heroBiCycle.brand, heroBiCycle.color));
        System.out.println(String.format("Brand: %s and Color: %s", aOneBiCycle.brand, aOneBiCycle.color));

        heroBiCycle.color = "blue";

        System.out.println();
        System.out.println();

        System.out.println(String.format("Brand: %s and Color: %s", heroBiCycle.brand, heroBiCycle.color));
        System.out.println(String.format("Brand: %s and Color: %s", aOneBiCycle.brand, aOneBiCycle.color));
    }
}
