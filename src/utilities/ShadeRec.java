package utilities;

import materials.Material;
import world.World;

public class ShadeRec {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    /** Did the ray hit an object? */
    public boolean isHit;

    /** World coordinates of the hit point. */
    public Point3D hitPoint;

    /** Normal at the hit point. */
    public Normal hitNormal;

    /** For attaching textures to objects. */
    public Point3D localHitPoint;

    /** Nearest object's material. */
    public Material material;

    /** In which world the record is. */
    public World world;

    /** For specular highlights. */
    public Ray ray;

    /** Ray parameter. */
    public double t;

    /** Recursion depth limit. */
    public int depth;

    /** For area lights. */
    public Vector3D direction;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public ShadeRec() {
    }

    public ShadeRec(World world) {
        this.world = world;
    }

    public ShadeRec(ShadeRec s) {
        this.isHit = s.isHit;
        this.hitPoint = s.hitPoint;
        this.hitNormal = s.hitNormal;
        this.localHitPoint = s.localHitPoint;
        this.material = s.material;
        this.world = s.world;
        this.ray = s.ray;
        this.t = s.t;
        this.ray = s.ray;
        this.depth = s.depth;
        this.direction = s.direction;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "ShadeRec{" +
                "isHit=" + isHit +
                ", hitPoint=" + hitPoint +
                ", localHitPoint=" + localHitPoint +
                ", material=" + material +
                ", length=" + hitNormal +
                ", world=" + world +
                ", ray=" + ray +
                ", depth=" + depth +
                ", direction=" + direction +
                '}';
    }
}
