package utilities;

/**
 * @author Yuanqi Li
 */
public class RgbColor {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Colors
\*--------------------------------------------------------------------------------------------------------------------*/

    public static final RgbColor BLACK = new RgbColor(0,   0,   0);
    public static final RgbColor RED   = new RgbColor(255, 0,   0);
    public static final RgbColor GREEN = new RgbColor(0,   255, 0);
    public static final RgbColor BLUE  = new RgbColor(0,   0,   255);
    public static final RgbColor WHITE = new RgbColor(255, 255, 255);
    public static final RgbColor YELLOW= new RgbColor(255, 255, 0);

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    public double r;
    public double g;
    public double b;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public RgbColor() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    public RgbColor(double a) {
        this.r = a;
        this.g = a;
        this.b = a;
    }

    public RgbColor(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public RgbColor(RgbColor c) {
        this.r = c.r;
        this.g = c.g;
        this.b = c.b;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Color arithmetic
\*--------------------------------------------------------------------------------------------------------------------*/

    public RgbColor add(RgbColor c) {
        double r = this.r + c.r;
        double g = this.g + c.g;
        double b = this.b + c.b;
        return new RgbColor(r, g, b);
    }

    public RgbColor mul(double a) {
        double r = this.r * a;
        double g = this.g * a;
        double b = this.b * a;
        return new RgbColor(r, g, b);
    }

    public RgbColor mul(RgbColor c) {
        double r = this.r * c.r;
        double g = this.g * c.g;
        double b = this.b * c.b;
        return new RgbColor(r, g, b);
    }

    public RgbColor div(double a) {
        double r = this.r / a;
        double g = this.g / a;
        double b = this.b / a;
        return new RgbColor(r, g, b);
    }

    public RgbColor pow(double a) {
        double r = Math.pow(this.r, a);
        double g = Math.pow(this.g, a);
        double b = Math.pow(this.b, a);
        return new RgbColor(r, g, b);
    }

    public int toInt() {
        return (((int)r << 16) | ((int)g << 8) | (int)b);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

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
}
