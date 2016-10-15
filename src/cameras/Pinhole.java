package cameras;

import utilities.*;
import world.ViewPlane;
import world.World;

public class Pinhole extends Camera {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    /** View-plane distance. */
    private double d;

    /** Zoom factor, default set to 1. */
    private double zoomFactor = 1;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Pinhole(Point3D eye, double d) {
        super(eye);
        this.d = d;
    }

    public Pinhole(Point3D eye, Point3D lookat, double d) {
        super(eye, lookat);
        this.d = d;
    }

    public Pinhole(Point3D eye, Point3D lookat, Vector3D up, double d) {
        super(eye, lookat, up);
        this.d = d;
    }

    public Pinhole(double eyeX, double eyeY, double eyeZ,
                   double lookatX, double lookatY, double lookatZ,
                   double upX, double upY, double upZ,
                   double d) {
        super(eyeX, eyeY, eyeZ, lookatX, lookatY, lookatZ, upX, upY, upZ);
        this.d = d;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public void renderScene(World w) {

        RgbColor  radiance;
        ViewPlane vp = w.getViewPlane();
        Ray       ray = new Ray().setOrigin(eye);
        int       depth = 0;
        Point2D   sp; // sample point in a unit square
        Point2D   pp; // sample point on a pixel

        vp.s /= zoomFactor;

        for (int row = 0; row < vp.vres; row++)
            for (int col = 0; col < vp.hres; col++) {
                radiance = RgbColor.BLACK;
                for (int j = 0; j < vp.getNumSamples(); j++) {
                    sp = vp.getSampler().nextSampleOnUnitSquare();
                    ray.setDirection(rayDirection(
                            vp.s * (col - 0.5 * vp.hres + sp.x),
                            vp.s * (row - 0.5 * vp.vres + sp.y)));
                    radiance = radiance.add(w.getTracer().trace(ray, depth));
                }
                radiance = radiance.div(vp.getNumSamples()).mul(exposureTime);
                System.out.println(radiance);
            }
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Helper methods
\*--------------------------------------------------------------------------------------------------------------------*/

    private Vector3D rayDirection(double x, double y) {
        return u.mul(x)
                .add(v.mul(y))
                .sub(w.mul(d))
                .normalVector();
    }

    private Vector3D rayDirection(Point2D p) {
        return rayDirection(p.x, p.y);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public double getViewPlaneDistance() {
        return d;
    }

    public void setViewPlaneDistance(double d) {
        this.d = d;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public void setZoomFactor(double zoom) {
        this.zoomFactor = zoom;
    }
}
