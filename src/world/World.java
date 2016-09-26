package world;

import geometrics.*;
import tracers.*;
import utilties.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class World {

    private ViewPlane             vp;
    private RgbColor              backgroundColor = RgbColor.BLACK;
    private Tracer                tracer  = new MultipleObjects(this);
    private List<GeometricObject> objects = new ArrayList<>();
    private Sphere                sphere;

    public World(ViewPlane vp) {
        this.vp = vp;
    }

    public void addObject(GeometricObject object) {
        objects.add(object);
    }

    public ShadeRec hitBareBonesObjects(final Ray ray) {
        ShadeRec sr = new ShadeRec(this);
        double t = 0, tmin = Constants.kHugeValue;
        for (GeometricObject o : objects) {
            if (o.hit(ray, t, sr) && t < tmin) {
                sr.setHit(true);
                tmin = t;
                sr.setColor(o.getColor());
            }
        }
        return sr;
    }

    public void renderScene() {
        RgbColor pixelColor;
        Ray      ray = new Ray();
        double   zw = 100.0;
        double   x, y;

        openWindow(vp.getHres(), vp.getVres());

        for (int r = 0; r < vp.getVres(); r++) {
            for (int c = 0; c <= vp.getHres(); c++) {
                x = vp.getPixelSize() * (c - 0.5 * (vp.getHres() - 1.0));
                y = vp.getPixelSize() * (r - 0.5 * (vp.getVres() - 1.0));
                ray.setOrigin(new Point3D(x, y, zw));
                pixelColor = tracer.trace(ray);
                displayPixel(r, c, pixelColor);
            }
            System.out.println("");
        }
    }

    public void openWindow(int hres, int vres) {

    }

    public void displayPixel(int row, int column, RgbColor pixelColor) {
        if (pixelColor.equals(RgbColor.RED)) {
            System.out.print(Console.ANSI_RED + "**" + Console.ANSI_RESET);
        } else if (pixelColor.equals(RgbColor.GREEN)) {
            System.out.print(Console.ANSI_GREEN + "**" + Console.ANSI_RESET);
        } else if (pixelColor.equals(RgbColor.BLUE)) {
            System.out.print(Console.ANSI_BLUE + "**" + Console.ANSI_RESET);
        } else
            System.out.print("  ");
    }

    public RgbColor getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(RgbColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    public Sphere getSphere() {
        return sphere;
    }
    public void setSphere(Sphere sphere) {
        this.sphere = sphere;
    }
    public List<GeometricObject> getObjects() {
        return objects;
    }
    public void setObjects(List<GeometricObject> objects) {
        this.objects = objects;
    }
}
