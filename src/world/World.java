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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class World {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    private ViewPlane viewPlane;
    private RgbColor  backgroundColor = RgbColor.BLACK;
    private Camera    camera = null;
    private Tracer    tracer = null;
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
        this.image = new BufferedImage(viewPlane.hres, viewPlane.vres, BufferedImage.TYPE_INT_RGB);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Public methods
\*--------------------------------------------------------------------------------------------------------------------*/

    public void build() {

        viewPlane = new ViewPlane(400, 400, 1.0, new Regular(4));
        image = new BufferedImage(viewPlane.hres, viewPlane.vres, BufferedImage.TYPE_INT_RGB);
        tracer = new RayCast(this);
        ambient = new Ambient();
        camera = new Pinhole(0, 50, 100, 0, 0, 0, 0, 1, 0, 200);

        objects.add(new Sphere(new Point3D(-40, 0, 0), 50).withMaterial(new Matte(0.005, 0.75, new RgbColor(2, 0, 0))));
        objects.add(new Sphere(new Point3D(0, 0, 0), 50).withMaterial(new Matte(0.005, 0.75, new RgbColor(0, 2, 0))));
        objects.add(new Sphere(new Point3D(40, 0, 0), 50).withMaterial(new Matte(0.005, 0.75, new RgbColor(0, 0, 2))));

        lights.add(new PointLight(new Point3D(200, 0, 250)));
    }

    public void render() {
        this.camera.renderScene(this);
        windowManager(viewPlane.hres, viewPlane.vres, image);
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

    /**
     * Displays the rendered image onto the screen.
     */
    static void windowManager(int hres, int vres, BufferedImage image) {
        JFrame frame = new JFrame("Image");
        frame.getContentPane().add(BorderLayout.CENTER, new JPanel() {
            protected void paintComponent(Graphics g) {
                g.drawImage(image, 0, 0, null);
            }
        });
        frame.getContentPane().setPreferredSize(new Dimension(hres, vres));
        frame.pack();
        frame.setResizable(false);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    System.exit(0);
            }
        });
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static RgbColor maxTo255(RgbColor c) {
        double max = Math.max(c.r, Math.max(c.r, c.g));
        return max > 255 ? c.div(max) : c;
    }

    public static RgbColor climpToRed(RgbColor c) {
        return (c.r > 255 || c.g > 255 || c.b > 255) ? RgbColor.RED : c;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public ViewPlane getViewPlane() {
        return viewPlane;
    }

    public void setViewPlane(ViewPlane viewPlane) {
        this.viewPlane = viewPlane;
        this.image = new BufferedImage(viewPlane.hres, viewPlane.vres, BufferedImage.TYPE_INT_RGB);
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
