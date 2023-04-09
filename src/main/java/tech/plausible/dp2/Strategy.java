package tech.plausible.dp2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

enum IdentifiedState {
    Anon,
    Auth,
    Identified,
    Identified_Offline;
}

class User {
    private final String id;
    private final String name;
    private final IdentifiedState state;

    User(String id, String name, IdentifiedState state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public IdentifiedState getState() {
        return state;
    }

    public String getId() {
        return id;
    }
}

interface IdentifyUser {
    User identify(String cookie);
}

enum IdentificationMode {
    Offline,
    Online
}
class UserIdentificationLib {
    private StrategyProvider provider = new StrategyProvider();

    public User identify(String cookie, IdentificationMode mode) {
        return provider.getStrategy(mode).identify(cookie);
    }

    class StrategyProvider {
        IdentifyUser onlineStrategy = new OnlineIdentificationStrategy();
        IdentifyUser offlineStrategy = new OfflineIdentificationStrategy();

        IdentifyUser getStrategy(IdentificationMode mode) {
            if (IdentificationMode.Offline == mode) {
                return offlineStrategy;
            }
            return onlineStrategy;
        }
    }
}


class OnlineIdentificationStrategy implements IdentifyUser {

    @Override
    public User identify(String cookie) {
        // CALL USER SERVICE AND CREATE USER
        return new User("1", "John", IdentifiedState.Identified);
    }
}

class OfflineIdentificationStrategy implements IdentifyUser {

    @Override
    public User identify(String cookie) {
        // EXTRACT BASIC INFO FROM COOKIE AND CREATE USER
        return new User("1", "UNDEFINED", IdentifiedState.Identified_Offline);
    }
}

class StrategyRunner {
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) throws InterruptedException {
        final UserIdentificationLib lib = new UserIdentificationLib();

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 20 + 1);
            IdentificationMode mode = IdentificationMode.Offline;
            if (randomNum % 2 == 0) {
                mode = IdentificationMode.Online;
            }
            User user = lib.identify("", mode);
            System.out.println(user.getName());

        }, 0, 2, TimeUnit.SECONDS);

        Thread.sleep(1000000);

    }
}