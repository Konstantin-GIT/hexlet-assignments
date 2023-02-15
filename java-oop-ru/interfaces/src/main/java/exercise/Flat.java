package exercise;

// BEGIN
public class Flat implements Home{
    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }
    double area;
    double balconyArea;
    int floor;

    public double getArea() {
        return area + balconyArea;
    }

    public int compareTo(Home realEstateObject) {
        if (this.getArea() > realEstateObject.getArea()) {
            return 1;
        } else if (this.getArea() < realEstateObject.getArea()) {
            return -1;
        } else return 0;
    }

    public String toString() {
        return "Квартира площадью " + this.getArea() + " метров на " + this.floor + " этаже";
    }
}
// END
