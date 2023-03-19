package tech.plausible.d1_inheritance;


import tech.plausible.d1_inheritance.db.Repository;
import tech.plausible.d1_inheritance.db.RepositoryFactory;

/**
 * In any project there are two type of code sections/groups
 *
 * One is:
 * Where object initialization and wiring happens
 *
 * Other:
 * Where logic executes by using objects initialized and wired in the first section.
 */

public class Runner {

    public static void main(String[] args) {
        Repository repoType = Repository.SQL;
        RepositoryFactory factory = new RepositoryFactory();
        BusinessLogic businessLogic = new BusinessLogic(factory.getRepo(repoType));

        businessLogic.handleWork();
    }
}
