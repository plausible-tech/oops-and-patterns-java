package tech.plausible.d2_encapsulation;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Data Hiding / Information Hiding
 *
 * Capsule => Medicines
 * There is an outer shell and then medicine inside
 *
 * Encapsulation means, wrap the data members via methods and not expose them to be altered freely
 * by any consumer.
 * This avoids UNINTENDED SIDE-EFFECTS
 */


class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    /**
     * We are only exposing setAge but not getAge
     * Also, the idea that age can never be > 50 is hidden within.
     */
    public void setAge(int age) {
        if (age > 50) {
            System.out.println("We won't serve");
        } else {
            this.age = age;
            System.out.println("Changed and will serve");
        }
    }

    public void printAdultStatus() {
        if (this.age >= 18) {
            System.out.println(this.name + " is adult!");
        } else {
            System.out.println(this.name + " is not adult!");
        }
    }
}

class Runner1 {
    private Person person;

    public Runner1(Person person) {
        this.person = person;
    }

    private static int random() {
        int min = 1;
        int max = 80;
        double a = Math.random()*(max-min+1)+min;
        return  (int)(Math.random()*(max-min+1)+min);
    }

    public void doWork() {

        System.out.println("R1 trying to change");
        this.person.setAge(random());
        this.person.printAdultStatus();
    }
}

class Runner2 {
    private Person person;

    public Runner2(Person person) {
        this.person = person;
    }

    private static int random() {
        int min = 1;
        int max = 90;
        double a = Math.random()*(max-min+1)+min;
        return  (int)(Math.random()*(max-min+1)+min);
    }

    public void doWork() {
        System.out.println("R2 trying to change");
        this.person.setAge(random());
        this.person.printAdultStatus();
    }
}

class ProviderAndRunner {
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

    static Person p1 = new Person(10, "John");
    static Runner1 r1 = new Runner1(p1);
    static Runner2 r2 = new Runner2(p1);

    public static void main(String[] args) {

        // Simulate multiple consumers
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            r1.doWork();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r2.doWork();

        }, 0, 1000, TimeUnit.MILLISECONDS);


        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int random() {
        int min = 1;
        int max = 30;
        double a = Math.random()*(max-min+1)+min;
        return  (int)(Math.random()*(max-min+1)+min);
    }

}


