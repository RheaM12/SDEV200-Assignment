public class TestGeometricObject {
    public static void main(String[] args) {
        // Test circles
        Circle c1 = new Circle(5.0);
        Circle c2 = new Circle(7.0);

        GeometricObject largerCircle = GeometricObject.max(c1, c2);
        System.out.println("Larger circle area: " + largerCircle.getArea());

        // Test rectangles
        Rectangle r1 = new Rectangle(4, 5);
        Rectangle r2 = new Rectangle(3, 8);

        GeometricObject largerRectangle = GeometricObject.max(r1, r2);
        System.out.println("Larger rectangle area: " + largerRectangle.getArea());
    }
}

// GeometricObject class 
abstract class GeometricObject implements Comparable<GeometricObject> {
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

    // Abstract method for subclasses
    public abstract double getArea();

    // Compare based on area
    @Override
    public int compareTo(GeometricObject o) {
        return Double.compare(this.getArea(), o.getArea());
    }

    // Static method to find the larger object
    public static GeometricObject max(GeometricObject o1, GeometricObject o2) {
        if (o1.compareTo(o2) >= 0)
            return o1;
        else
            return o2;
    }
}

// Circle class
class Circle extends GeometricObject {
    private double radius;

    public Circle() { this.radius = 1.0; }
    public Circle(double radius) { this.radius = radius; }
    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }

    @Override
    public double getArea() { return radius * radius * Math.PI; }

    public double getDiameter() { return 2 * radius; }
    public double getPerimeter() { return 2 * radius * Math.PI; }
}

// Rectangle class
class Rectangle extends GeometricObject {
    private double width;
    private double height;

    public Rectangle() {
        this.width = 1.0;
        this.height = 1.0;
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(double width, double height, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.height = height;
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }

    @Override
    public double getArea() { return width * height; }

    public double getPerimeter() { return 2 * (width + height); }
}
