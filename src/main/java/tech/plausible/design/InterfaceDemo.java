package tech.plausible.design;

interface A {
    void a();
}

interface Drawable {
    void draw();
    int getArea();
}

class Rectangle implements Drawable {
    private int breadth;
    private int length;

    Rectangle(int breadth, int length) {
        this.breadth = breadth;
        this.length = length;
    }

    @Override
    public void draw() {
        System.out.println("Rect");
    }

    @Override
    public int getArea() {
        return 0;
    }
}

class Circle implements Drawable {
    private int radius;

    Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {

    }

    @Override
    public int getArea() {
        return 0;
    }
}
