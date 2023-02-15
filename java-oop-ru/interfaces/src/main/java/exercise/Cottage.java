package exercise;

// BEGIN
public class Cottage implements Home {
    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }
      double area;
      int floorCount;

    public double getArea() {
        return area;
    }

    public int compareTo(Home realEstateObject) {
        if (this.getArea() > realEstateObject.getArea()) {
            return 1;
        } else if (this.getArea() < realEstateObject.getArea()) {
            return -1;
        } else return 0;
    }

    public String toString() {
        return this.floorCount + " этажный коттедж площадью " + this.getArea() + " метров";
    }
}
// END
