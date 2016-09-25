package world;

import geometrics.GeometricObject;
import tracers.Tracer;
import utilties.*;

import java.util.List;
import java.util.Vector;

/**
 * @author Yuanqi Li
 */
public class World {

    ViewPlane vp;
    RgbColor  backgroundColor;
    Tracer    tracer;
    List<GeometricObject> objects;

    public World(){}
    public void build(){}

    public void addObject(GeometricObject object) {
        objects.add(object);
    }

    public ShadeRec hitBareBonesObjects(final Ray ray) {
        ShadeRec sr = new ShadeRec(this);
        double t, tmin = Constants.kHugeValue;
        for (GeometricObject o : objects) {
            if (o.hit(ray, t, sr) && t < tmin) {
                sr.setHit(true);
                tmin = t;
                sr.setColor(o.getColor());
            }
        }
        return sr;
    }

    public void renderSecne(){}
    public void openWindow(int hres, int vres){}
    public void displayPixel(int row, int column, RgbColor pixelColor){}

    public RgbColor getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(RgbColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
