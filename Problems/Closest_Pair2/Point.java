/**
 * Point
 */
public class Point {
    public final float x;
    public final float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float Dist(Point other) {
        return (float) Math.sqrt((other.x - x) * (other.x - x) + (other.y - y) * (other.y - y));
    }

    @Override
    public String toString() { 
        return x + " " + y;
    } 
}