public class TestCircle {
    public static void main(String[] args) {
        Circle c1 = new Circle(5.0, "red", true);
        Circle c2 = new Circle(7.0, "blue", false);
        Circle c3 = new Circle(5.0);

        System.out.println("c1 radius: " + c1.getRadius());
        System.out.println("c1 area: " + c1.getArea());
        System.out.println("c1 perimeter: " + c1.getPerimeter());

        System.out.println("c1 compared to c2: " + c1.compareTo(c2)); // -1
        System.out.println("c1 equals c3? " + c1.equals(c3)); // true
    }
}

// GeometricObject class 
class GeometricObject {
    private String color = "white";
    private boolean filled;
    private java.util.Date dateCreated;

    public GeometricObject() {
        dateCreated = new java.util.Date();
    }

    public GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
        dateCreated = new java.util.Date();
    }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public boolean isFilled() { return filled; }
    public void setFilled(boolean filled) { this.filled = filled; }
    public java.util.Date getDateCreated() { return dateCreated; }
}

// Circle class 
class Circle extends GeometricObject implements Comparable<Circle> {
    private double radius;

    public Circle() { this.radius = 1.0; }
    public Circle(double radius) { this.radius = radius; }
    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }

    public double getArea() { return radius * radius * Math.PI; }
    public double getDiameter() { return 2 * radius; }
    public double getPerimeter() { return 2 * radius * Math.PI; }

    public void printCircle() {
        System.out.println("The circle is created " + getDateCreated() +
                           " and the radius is " + radius);
    }

    @Override
    public int compareTo(Circle o) {
        return Double.compare(this.radius, o.radius);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Circle other = (Circle) obj;
        return this.radius == other.radius;
    }
}

