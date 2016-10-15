package utilities;

/**
 * @author Yuanqi Li
 */
public class Point2D {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    public double x;
    public double y;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Point2D() {
        this.x = 0;
        this.y = 0;
    }

    public Point2D(double a) {
        this.x = a;
        this.y = a;
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(Point2D p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point2D copy() {
        return new Point2D(x, y);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  General methods
\*--------------------------------------------------------------------------------------------------------------------*/

    public Point2D neg() {
        return new Point2D(-x, -y);
    }

    public Point2D add(Point2D p) {
        return new Point2D(x + p.x, y + p.y);
    }

    public Point2D add(double d) {
        return new Point2D(x + d, y + d);
    }

    public Point2D sub(Point2D p) {
        return new Point2D(x - p.x, y - p.y);
    }

    public Point2D sub(double d) {
        return new Point2D(x - d, y - d);
    }

    public Point2D mul(double d) { return new Point2D(x * d, y * d); }

    public Point2D div(double d) { return new Point2D(x / d, y / d); }

    public double distance(Point2D p) {
        return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
    }

    public double distanceSquare(Point2D p) {
        return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point2D point2D = (Point2D) o;

        if (Double.compare(point2D.x, x) != 0) return false;
        return Double.compare(point2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
