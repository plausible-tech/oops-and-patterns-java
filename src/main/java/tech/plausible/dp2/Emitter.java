package tech.plausible.dp2;

import java.util.*;

interface Subscriber {
    void handle(String eventName, Object data);
}


interface EventBus {
    void addObserver(String eventName, Subscriber subscriber);
    void removeObserver(String eventName, Subscriber subscriber);
    void publish(String eventName, Object data);
}

class EventEmitter implements EventBus {
    private static final EventEmitter INSTANCE = new EventEmitter();
    private static final Map<String, List<Subscriber>> eventSubscriberMapping = new HashMap<>();

    @Override
    public void addObserver(String eventName, Subscriber subscriber) {
        List<Subscriber> subscribers = eventSubscriberMapping.get(eventName);
        if (Objects.isNull(subscribers) || subscribers.size() == 0) {
            subscribers = new ArrayList<>();
        }

        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }

        eventSubscriberMapping.put(eventName, subscribers);
    }

    @Override
    public void removeObserver(String eventName, Subscriber subscriber) {
        List<Subscriber> subscribers = eventSubscriberMapping.get(eventName);
        if (Objects.isNull(subscribers) || subscribers.size() == 0) {
            return;
        }

        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        }

        eventSubscriberMapping.put(eventName, subscribers);
    }

    @Override
    public void publish(String eventName, Object data) {
        List<Subscriber> subscribers = eventSubscriberMapping.get(eventName);

        if (Objects.isNull(subscribers) || subscribers.size() == 0) {
            return;
        }

        subscribers.forEach(subscriber -> subscriber.handle(eventName, data));
    }

    public static EventEmitter emitter() {
        return INSTANCE;
    }
}

class A implements  Subscriber {

    A() {
        EventEmitter.emitter().addObserver("B2A" , this);
    }

    public void doWork() {
        EventEmitter.emitter().publish("A2B", "Hello B");
    }

    @Override
    public void handle(String eventName, Object data) {
        System.out.println();
        System.out.println("====================================");
        System.out.println("From A -> for event : " + eventName);
        System.out.println("with data : " + data);
    }
}

class B implements  Subscriber {

    B() {
        EventEmitter.emitter().addObserver("A2B" , this);
    }

    public void doWork() {
        EventEmitter.emitter().publish("B2A", "Hello A");
    }

    @Override
    public void handle(String eventName, Object data) {
        System.out.println();
        System.out.println("====================================");
        System.out.println("From B -> for event : " + eventName);
        System.out.println("with data : " + data);
    }
}

class C implements  Subscriber {

    C() {
        EventEmitter.emitter().addObserver("2C" , this);
    }

    public void doWork() {
        EventEmitter.emitter().publish("CCC", null);
    }

    @Override
    public void handle(String eventName, Object data) {
        System.out.println();
        System.out.println("====================================");
        System.out.println("From C -> for event : " + eventName);
        System.out.println("with data : " + data);
    }
}

class EventRunner {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();

        a.doWork();
        b.doWork();
        c.doWork();
    }
}