import world.World;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class Main {
    public static void main(String... args) {
        World w = new World();
        w.build();
        w.renderScene();
    }
}
