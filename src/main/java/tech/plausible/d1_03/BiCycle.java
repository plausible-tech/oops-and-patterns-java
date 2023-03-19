package tech.plausible.d1_03;

class BiCycle {
    private static final int STEP_ACCELERATION = 1;
    private static final int STEP_DECELERATION = 1;
    private static final int MIN_SPEED = 0;
    private static final int MAX_SPEED = 20;

    private String brand;
    private String color;

    // State
    private boolean running;
    private int speed;
    private int acceleration;
    private int deceleration;

    BiCycle(String brand, String color) {
        this.brand = brand;
        this.color = color;
        this.acceleration = STEP_ACCELERATION;
        this.deceleration = STEP_DECELERATION;
    }

    private int getAcceleratedSpeed() {
        return this.speed + this.acceleration;
    }

    private int getDeceleratedSpeed() {
        return this.speed - this.deceleration;
    }

    void ride() {
        this.running = true;
        this.accelerate();
    }

    void decelerate() {
        if (!this.running) {
            return;
        }

        int calculatedSpeed = this.getDeceleratedSpeed();

        if (this.speed > MIN_SPEED && calculatedSpeed > MIN_SPEED) {
            this.speed = calculatedSpeed;
        } else {
            this.speed = MIN_SPEED;
        }

        if (this.speed == MIN_SPEED) {
            this.running = false;
        }
    }

    void accelerate() {
        int calculatedSpeed = this.getAcceleratedSpeed();

        if (this.speed < MAX_SPEED && calculatedSpeed < MAX_SPEED) {
            this.speed = calculatedSpeed;
        } else {
            this.speed = MAX_SPEED;
        }
    }

    boolean isRunning() {
        return this.running;
    }


}
