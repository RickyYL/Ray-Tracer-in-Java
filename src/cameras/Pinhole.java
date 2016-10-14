package cameras;

import utilities.*;
import world.ViewPlane;
import world.World;

public class Pinhole extends Camera {

    private double viewPlaneDistance;
    private double zoom = 1;

    public Pinhole(Point3D eye, Point3D lookat, double viewPlaneDistance) {
        super(eye, lookat);
        this.viewPlaneDistance = viewPlaneDistance;
    }

    public Pinhole(Point3D eye, Point3D lookat, Vector3D up, double viewPlaneDistance) {
        super(eye, lookat, up);
        this.viewPlaneDistance = viewPlaneDistance;
    }

    @Override
    public void renderScene(World world) {
        RgbColor  radiance;
        ViewPlane vp = world.getVp();
        Ray       ray = new Ray().setOrigin(eye);
        int       depth = 0;
        Point2D   sp; // sample point in a unit square
        Point2D   pp; // sample point on a pixel

        vp.pixelSize /= zoom;

        for (int row = 0; row < vp.vres; row++) {
            for (int col = 0; col < vp.hres; col++) {
                radiance = RgbColor.BLACK;
                for (int j = 0; j < vp.getNumSamples(); j++)
                {
                    sp = vp.getSampler().nextSampleOnUnitSquare();
                    ray.setDirection(rayDirection(
                            vp.pixelSize * (col - 0.5 * vp.hres + sp.x),
                            vp.pixelSize * (row - 0.5 * vp.vres + sp.y))
                    );
                    radiance = radiance.add(world.getTracer().trace(ray, depth));
                }
                radiance = radiance.div(vp.getNumSamples()).mul(exposureTime);
            }
        }
    }

    private Vector3D rayDirection(double x, double y) {
        return u.mul(x).add(v.mul(y)).sub(w.mul(viewPlaneDistance)).normalVector();
    }

    private Vector3D rayDirection(Point2D p) {
        return rayDirection(p.x, p.y);
    }
}
