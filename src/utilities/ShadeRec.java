package utilities;

import materials.Material;
import world.World;

/**
 * @author Yuanqi Li
 */
public class ShadeRec {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Did the ray hit an object?
     */
    public boolean isHit;

    /**
     * World coordinates of the hit point.
     */
    public Point3D hitPoint;

    /**
     * For attaching textures to objects.
     */
    public Point3D localHitPoint;

    /**
     * Nearest object's material.
     */
    public Material material;

    /**
     * Normal at the hit point.
     */
    public Normal normal;

    /**
     * In which world the record is.
     */
    public World world;

    /**
     * For specular highlights.
     */
    public Ray ray;

    /**
     * Ray parameter.
     */
    public double t;

    /**
     * Recursion depth limit.
     */
    public int depth;

    /**
     * For area lights.
     */
    public Vector3D direction;

    @Deprecated
    public RgbColor color = RgbColor.BLACK;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public ShadeRec() {
        this(null);
    }

    public ShadeRec(World world) {
        this.isHit = false;
        this.material = null;
        this.hitPoint = new Point3D();
        this.localHitPoint = new Point3D();
        this.normal = new Normal();
        this.ray = new Ray();
        this.depth = 0;
        this.direction = new Vector3D();
        this.world = world;
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Overload methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShadeRec shadeRec = (ShadeRec) o;

        if (isHit != shadeRec.isHit) return false;
        if (depth != shadeRec.depth) return false;
        if (hitPoint != null ? !hitPoint.equals(shadeRec.hitPoint) : shadeRec.hitPoint != null) return false;
        if (localHitPoint != null ? !localHitPoint.equals(shadeRec.localHitPoint) : shadeRec.localHitPoint != null)
            return false;
        if (material != null ? !material.equals(shadeRec.material) : shadeRec.material != null) return false;
        if (normal != null ? !normal.equals(shadeRec.normal) : shadeRec.normal != null) return false;
        if (world != null ? !world.equals(shadeRec.world) : shadeRec.world != null) return false;
        if (ray != null ? !ray.equals(shadeRec.ray) : shadeRec.ray != null) return false;
        if (direction != null ? !direction.equals(shadeRec.direction) : shadeRec.direction != null) return false;
        return color != null ? color.equals(shadeRec.color) : shadeRec.color == null;
    }

    @Override
    public int hashCode() {
        int result = (isHit ? 1 : 0);
        result = 31 * result + (hitPoint != null ? hitPoint.hashCode() : 0);
        result = 31 * result + (localHitPoint != null ? localHitPoint.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (normal != null ? normal.hashCode() : 0);
        result = 31 * result + (world != null ? world.hashCode() : 0);
        result = 31 * result + (ray != null ? ray.hashCode() : 0);
        result = 31 * result + depth;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShadeRec{" +
                "isHit=" + isHit +
                ", hitPoint=" + hitPoint +
                ", localHitPoint=" + localHitPoint +
                ", material=" + material +
                ", normal=" + normal +
                ", world=" + world +
                ", ray=" + ray +
                ", depth=" + depth +
                ", direction=" + direction +
                ", color=" + color +
                '}';
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public ShadeRec setHit(boolean isHit) {
        this.isHit = isHit;
        return this;
    }

    public ShadeRec setColor(RgbColor color) {
        this.color = color;
        return this;
    }

    public ShadeRec setNormal(Normal normal) {
        this.normal = normal;
        return this;
    }

    public ShadeRec setHitPoint(Point3D hitPoint) {
        this.hitPoint = hitPoint;
        return this;
    }
}
