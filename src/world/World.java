package world;

import geometrics.GeometricObject;
import geometrics.Sphere;
import tracers.SingleSphere;
import tracers.Tracer;
import utilties.*;

import java.util.List;
import java.util.Vector;

/**
 * @author Yuanqi Li
 * @version 0.1
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

    public void renderScene(){}
    public void openWindow(int hres, int vres){}
    public void displayPixel(int row, int column, RgbColor pixelColor){}

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
