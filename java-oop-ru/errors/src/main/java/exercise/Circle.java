package exercise;

// BEGIN
public class Circle {
    private int radius;
    private Point point;
    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public double getSquare() throws NegativeRadiusException {
    if (radius < 0) {
        throw new NegativeRadiusException();
    }
    return Math.PI * Math.pow(this.radius, 2);
    }
}
// END
