package tech.plausible.s2_abstraction;

public class Simulator {
    public void mustImplement() {
        throw new RuntimeException("All children must implement it");
    }

    public void parentProvidedImpl() {
        System.out.println("I just provided a basic impl, override and do what you want as child");
    }
}


class BadChild extends Simulator {
    @Override
    public void parentProvidedImpl() {
        System.out.println("You will surely crashed while using me");
    }
}

class GoodChild extends Simulator {

    @Override
    public void mustImplement() {
        System.out.println("Just avoided a crash");
    }
}

class AbsRunner {

    public static void main(String[] args) {
        Simulator badChild = new BadChild();
        Simulator goodChild = new GoodChild();

        goodChild.parentProvidedImpl();
        goodChild.mustImplement();

        badChild.parentProvidedImpl();
        badChild.mustImplement();
    }
}


// ABSTRACT CLASS
abstract class BetterSimulator {
    abstract void mustImplement();
}

class Child extends BetterSimulator {

    @Override
    void mustImplement() {

    }
}