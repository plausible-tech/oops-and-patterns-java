package tech.plausible.d1_inheritance;

import tech.plausible.d1_inheritance.db.DataRepository;

public class BusinessLogic {
    private final DataRepository repository;

    // Dependency Injection
    public BusinessLogic(DataRepository repository) {
        this.repository = repository;
    }

    public void handleWork() {
        this.repository.doWork();
    }

}
