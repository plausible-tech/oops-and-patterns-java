package tech.plausible.d1_inheritance.db;

public class SQLDataRepository extends DataRepository {
    private final SQLDriver driver;

    public SQLDataRepository(SQLDriver driver) {
        this.driver = driver;
    }

    @Override
    public void doWork() {
        System.out.println("Using " + driver.getName());
        System.out.println("Hello from SQL");
    }
}
