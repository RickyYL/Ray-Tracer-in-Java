package utilties;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class RgbColor {

    public static final RgbColor BLACK = new RgbColor(0,   0,   0);
    public static final RgbColor RED   = new RgbColor(255, 0,   0);
    public static final RgbColor GREEN = new RgbColor(0,   255, 0);
    public static final RgbColor BLUE  = new RgbColor(0,   0,   255);

    double r, g, b;

    public RgbColor() { r = 0; g = 0; b = 0; }
    public RgbColor(double a) { r = a; g = a; b = a; }
    public RgbColor(double a, double bb, double c) { r = a; g = bb; b = c; }

    public RgbColor add(RgbColor c) { return new RgbColor(r + c.r, g + c.g, b + c.b); }
    public RgbColor mul(double a)   { return new RgbColor(r * a, g * a, b * a); }
    public RgbColor mul(RgbColor c) { return new RgbColor(r * c.r, g * c.g, b * c.b); }
    public RgbColor div(double a)   { return new RgbColor(r / a, g / a, b / a); }
    public RgbColor pow(double a)   { return new RgbColor(Math.pow(r, a), Math.pow(g, a), Math.pow(b, a)); }

    public int toInt() { return ((int)r << 16) | ((int)g << 8) | (int)b; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RgbColor rgbColor = (RgbColor) o;

        if (Double.compare(rgbColor.r, r) != 0) return false;
        if (Double.compare(rgbColor.g, g) != 0) return false;
        return Double.compare(rgbColor.b, b) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(r);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(g);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "RgbColor{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}
