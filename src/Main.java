import geometrics.Plane;
import geometrics.Sphere;
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
        World w = new World(new ViewPlane(50, 50, 20.0, 1.0));
        w.addObject(new Sphere(new Point3D(0, -25, 0), 80, RgbColor.RED));
        w.addObject(new Sphere(new Point3D(0, 30,  0), 60, RgbColor.BLUE));
        w.addObject(new Plane(new Point3D(0, 0, 0), new Normal(0, 1, 1), RgbColor.GREEN));
        w.renderScene();
    }
}
