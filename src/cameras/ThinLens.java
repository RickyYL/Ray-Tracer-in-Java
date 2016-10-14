package cameras;

import samplers.Sampler;
import utilities.*;
import world.ViewPlane;
import world.World;

/**
 * Created by Yuanqi Li on 10/14/16.
 */
public class ThinLens extends Camera {

    double lensRadius;

    /**
     * View plane distance.
     */
    double d;

    /**
     * Focal plane distance.
     */
    double f;

    double zoomFactor;
    Sampler sampler;

    public ThinLens(Point3D eye) {
        super(eye);
    }

    public ThinLens(Point3D eye, Point3D lookat) {
        super(eye, lookat);
    }

    public ThinLens(Point3D eye, Point3D lookat, Vector3D up) {
        super(eye, lookat, up);
    }

    public ThinLens(double eyeX, double eyeY, double eyeZ,
                    double lookatX, double lookatY, double lookatZ,
                    double upX, double upY, double upZ) {
        super(eyeX, eyeY, eyeZ, lookatX, lookatY, lookatZ, upX, upY, upZ);
    }

    @Override
    public void renderScene(World w) {
        RgbColor L;
        Ray ray;
        ViewPlane plane = w.getVp();
        int depth = 0;

        Point2D sp, pp, dp, lp;

        plane.pixelSize /= zoomFactor;

        for (int r = 0; r < plane.vres - 1; r++) {
            for (int c = 0; c < plane.hres - 1; c++) {

                L = RgbColor.BLACK;
                for (int n = 0; n < plane.getNumSamples(); n++) {
                    sp = plane.getSampler().nextSampleOnUnitSquare();
                    pp = new Point2D(
                            plane.pixelSize * (c - plane.hres / 2.0 + sp.x),
                            plane.pixelSize * (r - plane.vres / 2.0 + sp.y)
                    );
                    dp = plane.getSampler().nextSampleOnUnitDisk();
                    lp = dp.mul(lensRadius);

                }
            }
        }


    }

    void setSampler(Sampler sampler) {
        this.sampler = sampler;
        this.sampler.mapSamplesToUnitDisk();
    }

    Vector3D rayDirection(Point2D pixelPoint, Point2D lensPoint) {
        Point2D focalHitPoint = new Point2D(
                pixelPoint.x * f / d,
                pixelPoint.y * f / d
        );
        return u.mul(focalHitPoint.x - lensPoint.x)
                .add(v.mul(focalHitPoint.y - lensPoint.y))
                .sub(w.mul(f)).normalVector();
    }
}
