package world;

import geometrics.*;
import tracers.*;
import utilties.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuanqi Li
 * @version 0.5
 */
public class World {

/*--------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------*/

    private ViewPlane             vp;
    private RgbColor              backgroundColor = RgbColor.BLACK;
    private Tracer                tracer  = new MultipleObjects(this);
    private List<GeometricObject> objects = new ArrayList<>();
    private BufferedImage         image;

    @Deprecated
    private Sphere                sphere;

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

    /**
     * Adds new <code>GeometricObject</code> to <code>objects</code>.
     * @param object new object to be rendered in the scene
     */
    public void addObject(GeometricObject object) {
        objects.add(object);
    }

    /**
     * Displays world information in the console.
     */
    public void displayInfo() {
        objects.stream().forEach(System.out::println);
    }

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
        double   x, y;

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
        Point2D  sp;                    // sample point in [0,1]x[0.1]
        Point2D  pp = new Point2D();    // sample point on a pixel

        vp.getSampler().generateSamples();

        for (int row = 0; row < vp.vres; row++) {                       // up
            for (int col = 0; col <= vp.hres; col++) {                  // across
                pixelColor = RgbColor.BLACK;
                for (int j = 0; j < vp.getNumSamples(); j++) {          // samples in a pixel
                    sp = vp.getSampler().nextSampleOnUnitSquare();
                    ray.setOrigin(
                            vp.pixelSize * (col - 0.5 * vp.hres + sp.x),
                            vp.pixelSize * (row - 0.5 * vp.vres + sp.y),
                            zw);
                    pixelColor.add(tracer.trace(ray));
                }
                pixelColor = pixelColor.div(vp.getNumSamples());
                image.setRGB(row, col, pixelColor.toInt());
            }
        }

        displayImage();
    }



    // TODO I'm not sure if I get things work for Java's object model.
    // See Listing 8.1
    public void renderPerspective() {

        double eyePoint = 0;
        double viewPlaneDistance = 0;

        RgbColor pixelColor;
        Ray      ray = new Ray(new Point3D(0.0, 0.0, eyePoint), new Vector3D());

        for (int r = 0; r < vp.getVres(); r++) {
            for (int c = 0; c <= vp.getHres(); c++) {
                ray.setDirection(new Vector3D(
                        vp.getPixelSize() * (c - 0.5 * (vp.getHres() - 1.0)),
                        vp.getPixelSize() * (r - 0.5 * (vp.getVres() - 1.0)),
                        -viewPlaneDistance));
                ray.getDirection().normalize();
                image.setRGB(r, c, tracer.trace(ray).toInt());
            }
        }

        displayImage();
    }

/*--------------------------------------------------------------*\
 *  Private methods
\*--------------------------------------------------------------*/

    /**
     * // TODO To be removed.
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

/*--------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------*/

    public ViewPlane getVp() {
        return vp;
    }

    public void setVp(ViewPlane vp) {
        this.vp = vp;
    }

    public RgbColor getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(RgbColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Tracer getTracer() {
        return tracer;
    }

    public void setTracer(Tracer tracer) {
        this.tracer = tracer;
    }

    public List<GeometricObject> getObjects() {
        return objects;
    }

    public void setObjects(List<GeometricObject> objects) {
        this.objects = objects;
    }

    public Sphere getSphere() {
        return sphere;
    }

    public void setSphere(Sphere sphere) {
        this.sphere = sphere;
    }
}
