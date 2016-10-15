package world;

import cameras.Camera;
import cameras.Pinhole;
import geometrics.*;
import lights.Ambient;
import lights.Light;
import lights.PointLight;
import materials.Matte;
import samplers.Hammersley;
import samplers.Regular;
import tracers.*;
import utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class World {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    private ViewPlane viewPlane;
    private RgbColor  backgroundColor = RgbColor.BLACK;
    private Camera    camera = null;
    private Tracer    tracer = new MultipleObjects(this);
    private Light     ambient = new Ambient();

    private BufferedImage         image;
    private List<GeometricObject> objects = new ArrayList<>();
    private List<Light>           lights = new ArrayList<>();

    @Deprecated
    private Sphere sphere;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public World() {
    }

    public World(ViewPlane viewPlane) {
        this.viewPlane = viewPlane;
        this.image = new BufferedImage(viewPlane.vres + 1, viewPlane.hres + 1, BufferedImage.TYPE_INT_RGB);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Public methods
\*--------------------------------------------------------------------------------------------------------------------*/

    public void build() {

        viewPlane = new ViewPlane(20, 20, 1.0, new Regular(1));
        tracer = new RayCast(this);
        ambient = new Ambient();
        camera = new Pinhole(0, 0, 100, 0, 0, 0, 0, 1, 0, 850);

        Sphere sphere = new Sphere(new Point3D(10, -5, 0), 27);
        sphere.setMaterial(new Matte(0.25, 0.65, RgbColor.YELLOW));

        objects.add(sphere);
        lights.add(new PointLight(new Point3D(100, 50, 150)));
    }

    @Deprecated
    public ShadeRec hitBareBonesObjects(Ray ray) {
        ShadeRec sr = new ShadeRec(this);
        double t = Constants.EPSILON;
        double tmin = Constants.HUGE_VALUE;
        for (GeometricObject o : objects) {
            if (o.hit(ray, t, sr) && t < tmin) {
                sr.isHit = true;
                tmin = t;
                sr.color = o.getColor();
            }
        }
        return sr;
    }

    public ShadeRec hitObjects(Ray ray) {

        ShadeRec sr = new ShadeRec(this);
        double   t = Constants.EPSILON;
        double   tmin = Constants.HUGE_VALUE;
        Normal   normal = null;
        Point3D  localHitPoint = null;

        for (GeometricObject o : objects) {
            if (o.hit(ray, t, sr) && t < tmin) {
                tmin = t;
                sr.isHit = true;
                sr.material = o.getMaterial();
                sr.hitPoint = ray.getOrigin().add(ray.getDirection().mul(t));
                normal = sr.hitNormal;
                localHitPoint = sr.localHitPoint;
            }
        }

        // restore the nearest object's info
        if (sr.isHit) {
            sr.t = tmin;
            sr.hitNormal = normal;
            sr.localHitPoint = localHitPoint;
        }

        return sr;
    }

    public void addObject(GeometricObject object) {
        objects.add(object);
    }

    public void addLight(Light light) { lights.add(light); }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Private methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Deprecated
    private void displayPixel(int row, int column, RgbColor pixelColor) {
        if (pixelColor.equals(RgbColor.RED)) {
            System.out.print(Console.ANSI_RED + "**" + Console.ANSI_RESET);
        } else if (pixelColor.equals(RgbColor.GREEN)) {
            System.out.print(Console.ANSI_GREEN + "**" + Console.ANSI_RESET);
        } else if (pixelColor.equals(RgbColor.BLUE)) {
            System.out.print(Console.ANSI_BLUE + "**" + Console.ANSI_RESET);
        } else
            System.out.print("  ");
    }

    private void displayImage() {
        JFrame frame = new JFrame("image");
        frame.getContentPane().add(new JPanel() {
            public void paint(Graphics g) {
                g.drawImage(image, 0, 0, this);
            }
        });
        frame.setSize(viewPlane.vres, viewPlane.hres);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void displayInfo() {
        objects.forEach(System.out::println);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public ViewPlane getViewPlane() {
        return viewPlane;
    }

    public void setViewPlane(ViewPlane viewPlane) {
        this.viewPlane = viewPlane;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Tracer getTracer() {
        return tracer;
    }

    public void setTracer(Tracer tracer) {
        this.tracer = tracer;
    }

    public RgbColor getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(RgbColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Light getAmbient() {
        return ambient;
    }

    public BufferedImage getImage() {
        return image;
    }

    public List<GeometricObject> getObjects() {
        return objects;
    }

    public List<Light> getLights() {
        return lights;
    }

    @Deprecated
    public Sphere getSphere() {
        return sphere;
    }

    @Deprecated
    public void setSphere(Sphere sphere) {
        this.sphere = sphere;
    }
}
