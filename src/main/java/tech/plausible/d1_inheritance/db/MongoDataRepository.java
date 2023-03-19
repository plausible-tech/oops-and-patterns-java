package tech.plausible.d1_inheritance.db;

public class MongoDataRepository extends DataRepository {
    private final MongoDriver mongoDriver;

    public MongoDataRepository(MongoDriver mongoDriver) {
        this.mongoDriver = mongoDriver;
    }

    @Override
    public void doWork() {
        System.out.println("Using " + this.mongoDriver.getName());
        System.out.println("Hello from Mongo");
    }
}
