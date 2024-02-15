package simplePhysics;

/**
 *
 * @author leonardo
 */
public class Vec2d {

    public double x;
    public double y;

    public Vec2d() {
    }

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2d(Vec2d v) {
        this.x = v.x;
        this.y = v.y;
    }
    
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vec2d v) {
        this.x = v.x;
        this.y = v.y;
    }
    
    public void translate(double tx, double ty) {
        x += tx;
        y += ty;
    }
    
    public void add(Vec2d v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void sub(Vec2d v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void sub(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void scale(double s) {
        this.x *= s;
        this.y *= s;
    }
    
    public double getSize() {
        return Math.sqrt(x * x + y * y);
    }
    
    public void normalize() {
        scale(1 / getSize());
    }
    
    public double dot(Vec2d v) {
        return x * v.x + y * v.y;
    }
    
    @Override
    public String toString() {
        return "Vec2{" + "x=" + x + ", y=" + y + '}';
    }

    
}
