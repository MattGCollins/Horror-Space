package horrorspace.math;

import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Matt
 */
public class HorrorMath {
    public static Vector3f crossProduct(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);
    }

    public static Vector3f sub(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public static Vector3f add(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }
    
    public static String stringVector(Vector3f v1) {
        return "{" + v1.x + "," + v1.y + ","+ v1.z + "}";
    }
    
    public static Vector3f scaleVector(Vector3f v1, float scale) {
        return new Vector3f(v1.x * scale, v1.y * scale, v1.z * scale);
    }

    public static float dotProduct(Vector3f vectorTo, Vector3f normal) {
        return vectorTo.x * normal.x + vectorTo.y * normal.y + vectorTo.z * normal.z;
    }
    
    public static float distanceSquared(Vector3f vector1, Vector3f vector2){
        
        return square(vector1.x - vector2.x) + square(vector1.y - vector2.y) + square(vector1.z - vector2.z);
    }
    
    public static float square(float number) {
        return number * number;
    }
    
    public static float getSign(float number) {
        return number < 0 ? -1 : 1;
    }
}