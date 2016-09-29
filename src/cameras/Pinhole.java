package cameras;

import utilties.*;
import world.ViewPlane;
import world.World;

/**
 * @author Yuanqi Li
 * @version 0.9
 */
public class Pinhole extends Camera {

    double d;       // view-plane distance
    double zoom;    // zoom factor

    public Pinhole(Point3D eye, Point3D lookat, double d, double zoom) {
        super(eye, lookat);
        this.d = d;
        this.zoom = zoom;
    }

    @Override
    public void renderScene(World w) {
        RgbColor  L;
        ViewPlane vp = w.getVp();
        Ray       ray = new Ray(eye, new Vector3D());
        int       depth = 0;
        Point2D   sp;           // sample point in a unit square
        Point2D   pp;           // sample point on a pixel

        vp.setPixelSize((float) vp.getPixelSize() / (float) zoom);

    }

    public Vector3D rayDirection(Point2D p) {
        Vector3D dir = u.scalarProduct(p.x).add(v.scalarProduct(p.y)).sub(w.scalarProduct(d));
        dir.normalize();
        return dir;
    }
}
