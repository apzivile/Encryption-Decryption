abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
    //public abstract int perform();
}

class Triangle extends Shape {
    double a;
    double b;
    double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getPerimeter() {
        return a + b + c;
    }

    public double getArea() {
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

class Rectangle extends Shape {
    double a;
    double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getPerimeter() {
        return a * 2 + b * 2;
    }

    public double getArea() {
        return a * b;
    }
}

class Circle extends Shape {
    double r;

    public Circle(double r) {
        this.r = r;
    }

    public double getPerimeter() {
        return 2 * Math.PI * r;
    }

    public double getArea() {
        return Math.PI * (r * r);
    }
}