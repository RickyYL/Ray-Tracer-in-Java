import geometrics.Plane;
import geometrics.Sphere;
import samplers.Regular;
import utilties.Normal;
import utilties.Point3D;
import utilties.RgbColor;
import world.ViewPlane;
import world.World;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class Main {
    public static void main(String... args) {
        World w = new World(new ViewPlane(200, 200, 1.0, 1.0, 1, new Regular(1)));
        w.addObject(new Plane(new Point3D(0, 0, 100), new Normal(0, 1, 1), RgbColor.BLUE));
        w.addObject(new Sphere(new Point3D(0, 0, 50), 40));
        w.renderSceneWithSampler();
    }
}
