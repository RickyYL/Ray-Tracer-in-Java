package world;

import geometrics.GeometricObject;
import geometrics.Sphere;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import tracers.SingleSphere;
import tracers.Tracer;
import utilties.*;

import java.util.List;
import java.util.Vector;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class World {

    ViewPlane             vp;
    RgbColor              backgroundColor;
    Tracer                tracer;

    Sphere                sphere;
    List<GeometricObject> objects;

    public World(){}

    public void build() {
        vp = new ViewPlane(200, 200, 1.0f, 1.0f);
        backgroundColor = RgbColor.BLACK;
        tracer = new SingleSphere(this);
        sphere = new Sphere(new Point3D(0, 0, 0), 85.0);
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

        openWindow(vp.hres, vp.vres);

        for (int r = 0; r < vp.vres; r++) {
            for (int c = 0; c <= vp.hres; c++) {
                x = vp.getPixelSize() * (c - 0.5 * (vp.hres - 1.0));
                y = vp.getPixelSize() * (r - 0.5 * (vp.vres - 1.0));
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
        if (pixelColor.equals(pixelColor.RED))
            System.out.print(".");
        else
            System.out.print(" ");
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
