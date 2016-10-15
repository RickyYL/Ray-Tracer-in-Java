package cameras;

import samplers.Sampler;
import utilities.*;
import world.ViewPlane;
import world.World;


public class ThinLens extends Camera {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    /** Lens radius. */
    double lensRadius;

    /** View plane distance. */
    double d;

    /** Focal plane distance. */
    double f;

    /** Zoom factor, default set to 1. */
    double zoomFactor =  1;

    /** A sampler used to generate sample points on lens. */
    Sampler sampler;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public ThinLens(Point3D eye, double lensRadius, double d, double f) {
        super(eye);
        this.lensRadius = lensRadius;
        this.d = d;
        this.f = f;
    }

    public ThinLens(Point3D eye, Point3D lookat, double lensRadius, double d, double f) {
        super(eye, lookat);
        this.lensRadius = lensRadius;
        this.d = d;
        this.f = f;
    }

    public ThinLens(Point3D eye, Point3D lookat, Vector3D up, double lensRadius, double d, double f) {
        super(eye, lookat, up);
        this.lensRadius = lensRadius;
        this.d = d;
        this.f = f;
    }

    public ThinLens(double eyeX, double eyeY, double eyeZ,
                    double lookatX, double lookatY, double lookatZ,
                    double upX, double upY, double upZ,
                    double lensRadius, double d, double f) {
        super(eyeX, eyeY, eyeZ, lookatX, lookatY, lookatZ, upX, upY, upZ);
        this.lensRadius = lensRadius;
        this.d = d;
        this.f = f;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public void renderScene(World w) {
        RgbColor radiance;
        Ray ray;
        ViewPlane vp = w.getViewPlane();
        int depth = 0;

        Point2D sp, pp, dp, lp;

        vp.s /= zoomFactor;

        for (int r = 0; r < vp.vres - 1; r++) {
            for (int c = 0; c < vp.hres - 1; c++) {
                radiance = RgbColor.BLACK;
                for (int n = 0; n < vp.getNumSamples(); n++) {
                    sp = vp.getSampler().nextSampleOnUnitSquare();
                    pp = new Point2D(
                            vp.s * (c - 0.5 * vp.hres + sp.x),
                            vp.s * (r - 0.5 * vp.vres + sp.y));
                    dp = vp.getSampler().nextSampleOnUnitDisk();
                    lp = dp.mul(lensRadius);
                    ray = new Ray(
                            eye.add(u.mul(lp.x).add(v.mul(lp.y))),
                            rayDirection(pp, lp));
                    radiance = radiance.add(w.getTracer().trace(ray, depth));
                }
                radiance = radiance.div(vp.getNumSamples()).mul(exposureTime);
            }
        }
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Implemented methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "ThinLens{" +
                "lensRadius=" + lensRadius +
                ", d=" + d +
                ", f=" + f +
                ", zoomFactor=" + zoomFactor +
                ", sampler=" + sampler +
                '}';
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Helper methods
\*--------------------------------------------------------------------------------------------------------------------*/

    Vector3D rayDirection(Point2D pixelPoint, Point2D lensPoint) {
        Point2D focalHitPoint = new Point2D(
                pixelPoint.x * f / d,
                pixelPoint.y * f / d);
        return u.mul(focalHitPoint.x - lensPoint.x)
                .add(v.mul(focalHitPoint.y - lensPoint.y))
                .sub(w.mul(f)).normalVector();
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    void setSampler(Sampler sampler) {
        this.sampler = sampler;
        this.sampler.mapSamplesToUnitDisk();
    }

}
