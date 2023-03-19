package tech.plausible.d1_01;

class BiCycle {
    String brand;
    String color;

    /*
    Not being initialised in constructor as
    the default values works for the example
    and then the state would be maintained
    during execution.
    */
    boolean running;
    int speed;

    // Constructor
    BiCycle(String brand, String color) {
        this.brand = brand;
        this.color = color;
    }

    void ride() {}
    void decelerate() {}
    void accelerate() {}
}
