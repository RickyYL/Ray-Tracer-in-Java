package utilties;

import java.awt.*;

/**
 * @author Yuanqi Li
 * @version 0.5
 */
public class Point2D {

/*--------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------*/

    public double x = 0;
    public double y = 0;

/*--------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------*/

    public Point2D() {
    }

    public Point2D(double a) {
        x = a; y = a;
    }

    public Point2D(double a, double b) {
        x = a; y = b;
    }

    public Point2D(final Point2D p) {
        x = p.x; y = p.y;
    }

/*--------------------------------------------------------------*\
 *  Public methods
\*--------------------------------------------------------------*/

    public Point2D neg() {
        return new Point2D(-x, -y);
    }

    public Point2D add(Point2D p) {
        return new Point2D(x + p.x, y + p.y);
    }

    public Point2D sub(Point2D v) {
        return new Point2D(x - v.x, y - v.y);
    }

    public double distance(Point2D p) {
        return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.x));
    }

    public double distanceSquare(Point2D p) {
        return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.x);
    }

    public Point2D copy() {
        return new Point2D(x, y);
    }

/*--------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------*/

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
        return "Point2D{" +  "x=" + x + ", y=" + y + '}';
    }

/*--------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------*/

}
