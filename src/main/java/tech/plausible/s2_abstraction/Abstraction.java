package tech.plausible.s2_abstraction;

import java.util.HashMap;
import java.util.Map;


class DbSimulator {
    private Map<Integer, PersonModel> store = new HashMap<>();

    public DbSimulator() {
        this.store.put(1, new PersonModel(1, 20, "John"));
        this.store.put(2, new PersonModel(2, 20, "Jack"));
    }

    public PersonModel getPersonById(int id) {
        return this.store.get(id);
    }
}

// Assuming it comes from DB
class PersonModel {
    private int id;
    private int age;
    private String name;

    public PersonModel(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public boolean isAdult() {
        return this.age >= 18;
    }

    public boolean isSeniorCitizen() {
        return this.age >= 60;
    }
}

class Business {
    private final DbSimulator dbSimulator;

    Business(DbSimulator dbSimulator) {
        this.dbSimulator = dbSimulator;
    }

    public void doWork() {
        final PersonModel person = this.dbSimulator.getPersonById(1);
        System.out.println(person.getName());


        if (person.isAdult()) {
            // CODE REMOVED
            System.out.println("Adult");
        } else {
            System.out.println("Not Adult");
        }

    }


    public void doWork2() {
        final PersonModel person = this.dbSimulator.getPersonById(1);
        System.out.println(person.getName());

        // EXTRA WORK
        if (person.getAge() >= 18) {
            System.out.println("Adult");
        } else {
            System.out.println("Not Adult");
        }
    }
}

class Runner {
    public static void main(String[] args) {
        DbSimulator simulator = new DbSimulator();
        Business business = new Business(simulator);

        business.doWork();
    }
}