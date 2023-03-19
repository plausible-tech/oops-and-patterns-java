package tech.plausible.d1_inheritance.db;

public class RepositoryFactory {
    public DataRepository getRepo(Repository repository) {
        switch (repository) {
            case MONGO:
                return new MongoDataRepository(new MongoDriver());
            case SQL:
                return new SQLDataRepository(new SQLDriver());
                default:
                    throw new IllegalArgumentException();
        }
    }
}
