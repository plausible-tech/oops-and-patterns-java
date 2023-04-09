package tech.plausible.dp2;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class SimpleObserver implements Observer {
    @Override
    public void handleSubscription() {
        System.out.println("Notified - " + this.hashCode());
    }
}

class SimpleSubject implements Subject {
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private static final List<Observer> observers = new ArrayList();

    SimpleSubject() {

        final SimpleSubject that = this;
        scheduledExecutorService.scheduleWithFixedDelay(that::notifyObservers, 0, 2, TimeUnit.SECONDS);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        observers.forEach(Observer::handleSubscription);
    }
}

class SimpleSubjectObserverRunner {
    public static void main(String[] args) throws InterruptedException {
        SimpleObserver first = new SimpleObserver();
        SimpleObserver second = new SimpleObserver();

        SimpleSubject simpleSubject = new SimpleSubject();
        simpleSubject.addObserver(first);
        simpleSubject.addObserver(second);

        Thread.sleep(10000000);
    }
}
