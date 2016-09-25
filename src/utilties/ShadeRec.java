package utilties;

import world.World;

/**
 * @author Yuanqi Li
 * @version 0.1
 */
public class ShadeRec {

    boolean  isHit = false;
    Point3D  localHitPoint = new Point3D();
    Normal   normal = new Normal();
    RgbColor color = RgbColor.BLACK;
    World    world;

    public ShadeRec(World w) {
        world = w;
    }

    public boolean isHit() {
        return isHit;
    }
    public void setHit(boolean hit) {
        isHit = hit;
    }
    public RgbColor getColor() {
        return color;
    }
    public void setColor(RgbColor color) {
        this.color = color;
    }
    public Normal getNormal() { return normal; }
    public void setNormal(Normal normal) { this.normal = normal;}
    public Point3D getLocalHitPoint() { return localHitPoint; }
    public void setLocalHitPoint(Point3D localHitPoint) { this.localHitPoint = localHitPoint; }
}
