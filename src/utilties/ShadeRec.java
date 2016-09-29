package utilties;

import world.World;

/**
 * @author Yuanqi Li
 * @version 0.2
 */
public class ShadeRec {

    private boolean  isHit = false;
    private Point3D  localHitPoint = new Point3D();
    private Normal   normal = new Normal();
    private RgbColor color = RgbColor.BLACK;
    private World    world = null;

    public ShadeRec(World w) {
        world = w;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean isHit) { this.isHit = isHit; }

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
