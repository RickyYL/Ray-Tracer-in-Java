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

    @Override
    public void renderScene(World w) {
        RgbColor  L;
        ViewPlane vp = w.getVp();
        Ray       ray = new Ray(eye, new Vector3D());
        int       depth = 0;
        Point2D   sp;           // sample point in a unit square
        Point2D   pp;           // sample point on a pixel

        vp.setPixelSize(vp.getPixelSize() / zoom);

    }

    public Vector3D rayDirection(Point2D p) {
        Vector3D dir = p.x * u + p.y * v - d * w;
        dir.normalize();
        return dir;
    }
}
