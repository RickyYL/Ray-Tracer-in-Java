package world;

import cameras.Camera;
import geometrics.*;
import lights.Ambient;
import lights.Light;
import tracers.*;
import utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuanqi Li
 */
public class World {

/*--------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------*/

    private ViewPlane vp;
    private Camera    camera = null;
    private Tracer    tracer = new MultipleObjects(this);
    private RgbColor  backgroundColor = RgbColor.BLACK;
    private Light     ambient = new Ambient();

    private BufferedImage         image;
    private List<GeometricObject> objects = new ArrayList<>();
    private List<Light>           lights = new ArrayList<>();

    @Deprecated
    private Sphere sphere;

/*--------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------*/

    public World(ViewPlane vp) {
        this.vp = vp;
        this.image = new BufferedImage(vp.vres + 1, vp.hres + 1, BufferedImage.TYPE_INT_RGB);
    }

/*--------------------------------------------------------------*\
 *  Public methods
\*--------------------------------------------------------------*/

    /**
     * Uses the given ray to hit every object in <code>objects</code>.
     * @param ray shooting ray
     * @return    ray-intersection information
     */
    public ShadeRec hitBareBonesObjects(final Ray ray) {

        ShadeRec sr = new ShadeRec(this);
        double t = Constants.kEpsilon;
        double tmin = Constants.kHugeValue;

        for (GeometricObject o : objects) {
            if (o.hit(ray, t, sr) && t < tmin) {
                sr.setHit(true);
                tmin = t;
                sr.setColor(o.getColor());
            }
        }
        return sr;
    }

    public ShadeRec hitObjects(final Ray ray) {

        // stores in it all info required for shading the nearest hit point
        ShadeRec sr = new ShadeRec(this);
        double   t = Constants.kEpsilon;
        double   tmin = Constants.kHugeValue;
        Normal   normal = null;
        Point3D  localHitPoint = null;

        for (GeometricObject o : objects) {
            if (o.hit(ray, t, sr) && t < tmin) {
                tmin = t;
                sr.isHit = true;
                sr.material = o.getMaterial();
                sr.hitPoint = ray.getOrigin().add(ray.getDirection().mul(t));
                normal = sr.normal;
                localHitPoint = sr.localHitPoint;
            }
        }

        // restore the nearest object's info
        if (sr.isHit) {
            sr.t = tmin;
            sr.normal = normal;
            sr.localHitPoint = localHitPoint;
        }

        return sr;
    }

    /**
     * Adds new <code>GeometricObject</code> to <code>objects</code>.
     * @param object new object to be rendered in the scene
     */
    public void addObject(GeometricObject object) {
        objects.add(object);
    }

    public void addLight(Light light) { lights.add(light); }

/*--------------------------------------------------------------*\
 *  Render methods (to be relocated to Camera classes)
\*--------------------------------------------------------------*/

    /**
     * Renders a scene with regular sampling in a orthogonal view.
     * The most basic renderer, used to test correctness and functionality of other classes.
     */
    @Deprecated
    public void renderScene() {

        RgbColor pixelColor;
        Ray      ray = new Ray().setDirection(0, 0, -1);
        double   zw = 100.0;

        long startTime = System.currentTimeMillis();

        for (int row = 0; row < vp.vres; row++) {
            for (int col = 0; col <= vp.hres; col++) {
                ray.setOrigin(
                        vp.pixelSize * (col - 0.5 * (vp.hres - 1.0)),
                        vp.pixelSize * (row - 0.5 * (vp.vres - 1.0)),
                        zw);
                pixelColor = tracer.trace(ray);
                image.setRGB(row, col, pixelColor.toInt());
            }
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println(totalTime + " ms");

        displayImage();
        displayInfo();
    }

    /**
     * Renders the scene with a specified sampler in the orthogonal view.
     */
    public void renderSceneWithSampler() {

        RgbColor pixelColor;
        Ray      ray = new Ray().setDirection(0, 0, -1);
        double   zw = 100.0;

        long startTime = System.currentTimeMillis();

        for (int row = 0; row < vp.vres; row++) {
            for (int col = 0; col <= vp.hres; col++) {
                pixelColor = RgbColor.BLACK;
                for (int j = 0; j < vp.getNumSamples(); j++) {
                    Point2D sp = vp.getSampler().nextSampleOnUnitSquare();
                    ray.setOrigin(
                            vp.pixelSize * (col - 0.5 * vp.hres + sp.x),
                            vp.pixelSize * (row - 0.5 * vp.vres + sp.y),
                            zw);
                    pixelColor = pixelColor.add(tracer.trace(ray));
                }
                pixelColor = pixelColor.div(vp.getNumSamples());
                image.setRGB(row, col, pixelColor.toInt());
            }
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println(totalTime + " ms");

        displayInfo();
        displayImage();
    }

    /**
     * Renders the scene in a perspective view.
     */
    public void renderPerspective() {

        double eyePoint = 0;
        double viewPlaneDistance = 0;
        Ray    ray = new Ray().setOrigin(0, 0, eyePoint);

        for (int row = 0; row < vp.vres; row++) {
            for (int col = 0; col <= vp.hres; col++) {
                ray.setDirection(
                        vp.pixelSize * (col - 0.5 * (vp.hres - 1.0)),
                        vp.pixelSize * (row - 0.5 * (vp.vres - 1.0)),
                        -viewPlaneDistance);
                image.setRGB(row, col, tracer.trace(ray).toInt());
            }
        }

        displayInfo();
        displayImage();
    }

    /**
     * Renders the scene using a camera, by which the task is done.
     */
    public void renderWithCamera() {
        camera.renderScene(this);
        displayInfo();
        displayImage();
    }

/*--------------------------------------------------------------*\
 *  Private methods
\*--------------------------------------------------------------*/

    /**
     * // TODO To be removed. Maybe it can be kept, but still need a modification for switch.
     * Displays pixels on somewhere.
     * @param row        the row of the pixel
     * @param column     the column of the pixel
     * @param pixelColor the color of the pixel to be drawn
     */
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

    /**
     * Displays the rendered image in a new window.
     */
    private void displayImage() {
        JFrame frame = new JFrame("image");
        frame.getContentPane().add(new JPanel() {
            public void paint(Graphics g) {
                g.drawImage(image, 0, 0, this);
            }
        });
        frame.setSize(vp.vres, vp.hres);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Displays world information in the console.
     */
    private void displayInfo() {
        objects.forEach(System.out::println);
    }

/*--------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------*/

    public ViewPlane getVp() {
        return vp;
    }

    public void setVp(ViewPlane vp) {
        this.vp = vp;
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

    public void setAmbient(Light ambient) {
        this.ambient = ambient;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public List<GeometricObject> getObjects() {
        return objects;
    }

    public void setObjects(List<GeometricObject> objects) {
        this.objects = objects;
    }

    public List<Light> getLights() {
        return lights;
    }

    public void setLights(List<Light> lights) {
        this.lights = lights;
    }
}
