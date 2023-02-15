package exercise;

// BEGIN
public class Segment {
    private Point beginPoint1;
    private Point endPoint2;

    public Segment(Point beginPoint1, Point endPoint2) {
        this.beginPoint1 = beginPoint1;
        this.endPoint2 = endPoint2;
    }


    public Point getBeginPoint() {
        return beginPoint1;
    }

    public Point getEndPoint() {
        return endPoint2;
    }

    public Point getMidPoint() {
        return new Point((getBeginPoint().getX() + getEndPoint().getX())/2, (getBeginPoint().getY() + getEndPoint().getY())/2);
    }
}
// END
