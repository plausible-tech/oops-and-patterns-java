package tech.plausible.dp2;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * State in singleton IS SHARED thus, it could be mutated
 * from multiple sources and have unwanted effects.
 *
 * Read-Only singleton is good as it is safe.
 * Consumers should not capture Singleton provided state locally as it defeats the purpose.
 * Or a self updating singleton is also good. Example: -> Configuration properties provider.
 */
class Singleton implements Subject {
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private static final List<Observer> observers = new ArrayList();

    //TODO:  EXAMPLE for Configs
    private final Map<String, String> configs = new HashMap<>();

    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
        configs.put("theme-mode", "dark");
    }

    private void changeTheme() {
        this.configs.put("theme-mode", UUID.randomUUID().toString());
        this.notifyObservers();
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    public String getValue(String key) {
        return this.configs.getOrDefault(key, null);
    }

    private void notifyObservers() {
        observers.forEach(Observer::handleSubscription);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void schedule() {
        scheduledExecutorService.scheduleWithFixedDelay(INSTANCE::changeTheme, 1, 3, TimeUnit.SECONDS);
    }
}

class BadConsumer {
    // STATE IS LOCAL - Any change may not be observed
    private final String themeMode;

    BadConsumer() {
        this.themeMode = Singleton.getInstance().getValue("theme-mode");
    }

    public void doWork() {
        System.out.println(themeMode);
    }
}

class OkConsumerWithSubscription implements Observer {
    // OK OK - NOT FINAL MUTABLE FIELD
    private String themeMode;

    OkConsumerWithSubscription() {
        this.themeMode = Singleton.getInstance().getValue("theme-mode");
    }

    @Override
    public void handleSubscription() {
        this.themeMode = Singleton.getInstance().getValue("theme-mode");
    }

    public void doWork() {
        System.out.println(themeMode);
    }
}

class GoodConsumer {
    private static final String THEME_MODE_KEY = "theme-mode";

    public void doWork() {
        System.out.println(Singleton.getInstance().getValue(THEME_MODE_KEY));
    }
}

class SingletonConsumptionExample {
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


    public static void main(String[] args) throws InterruptedException {
        Singleton.getInstance().schedule();
        BadConsumer badConsumer = new BadConsumer();
        OkConsumerWithSubscription ok = new OkConsumerWithSubscription();
        GoodConsumer goodConsumer = new GoodConsumer();

        Singleton.getInstance().addObserver(ok);

        badConsumer.doWork();
        ok.doWork();
        goodConsumer.doWork();

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                badConsumer.doWork();
                ok.doWork();
                goodConsumer.doWork();
            }
        }, 1, 1, TimeUnit.SECONDS);

        Thread.sleep(1000000);
    }
}

class HasSameHashCode {
    public static void main(String[] args) {
        Singleton first = Singleton.getInstance();
        Singleton second = Singleton.getInstance();

        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
    }
}

interface ConfigurationProvider {
    String getValue();
}

class ConfigurationProviderImpl implements ConfigurationProvider {
    private static final ConfigurationProviderImpl INSTANCE = new ConfigurationProviderImpl();
    @Override
    public String getValue() {
        return null;
    }

    public ConfigurationProvider getInstance() {
        return INSTANCE;
    }
}
