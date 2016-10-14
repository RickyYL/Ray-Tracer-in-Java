import geometrics.Plane;
import geometrics.Sphere;
import samplers.Jittered;
import tracers.Tracer;
import utilities.Normal;
import utilities.Point3D;
import utilities.RgbColor;
import world.ViewPlane;
import world.World;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class Main {
    public static void main(String... args) {

        int x = Integer.parseInt("100");
        System.out.println(x);

//        World w = new World(new ViewPlane(200, 200, 1.0, 1.0, 1, new Jittered(25)));
//        w.addObject(new Plane(new Point3D(0, 0, 70), new Normal(1, 1, 1), RgbColor.BLUE));
//        w.addObject(new Sphere(new Point3D(0, 0, 50), 400));
//        w.renderSceneWithSampler();
//        w.renderScene();
    }
}
