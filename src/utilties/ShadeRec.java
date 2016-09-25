package utilties;

import world.World;

/**
 * Created by yuanqi on 9/24/16.
 */
public class ShadeRec {

    boolean  isHit;
    Point3D  localHitPoint;
    Normal   normal;
    RgbColor color;
    World    world;

    public ShadeRec(World wr) {
        isHit = false;
        color = black;
        world = wr;
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
}
