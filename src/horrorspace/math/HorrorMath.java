package horrorspace.math;

import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

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

    public static Vector4f quaternionToAxisAngle(Quaternion quaternion) {
        if(quaternion.getW() == 1){
            return new Vector4f(0, 1, 0, 0);
        }
        float angle = 2 * (float) Math.acos(quaternion.getW());
        float divisionFactor = (float) Math.sin(angle / 2);
        float x = quaternion.getX() / divisionFactor;
        float y = quaternion.getY() / divisionFactor;
        float z = quaternion.getZ() / divisionFactor;
        return new Vector4f(x, y, z, angle);
    }

    public static Quaternion getConjugate(Quaternion quaternion) {
        Quaternion returnQuaternion = new Quaternion(-quaternion.x, -quaternion.y, -quaternion.z, quaternion.w);
        return returnQuaternion;
    }
    
    public static boolean isPointInsideBox(Vector3f point, Vector3f minPoint, Vector3f maxPoint){
        if(minPoint.x < point.x && point.x < maxPoint.x &&
            minPoint.y < point.y && point.y < maxPoint.y &&
            minPoint.z < point.z && point.z < maxPoint.z) {
            return true;
        }
        return false;
    }

    public static Vector3f rotateVectorByQuaternion(Vector3f translation, Quaternion rotationQuaternion) {
        Quaternion translationQuaternion = new Quaternion(translation.x, translation.y, translation.z, 0);
        Quaternion tempStorage = new Quaternion();
        Quaternion.mul(getConjugate(rotationQuaternion), translationQuaternion, tempStorage);
        Quaternion.mul(tempStorage, rotationQuaternion, translationQuaternion);
        return new Vector3f(translationQuaternion.x, translationQuaternion.y, translationQuaternion.z);
    }
    
    public static Matrix3f getMatrixByAxes(Vector3f right, Vector3f up, Vector3f back){
        Matrix3f newMatrix = new Matrix3f();
        newMatrix.m00 = right.x;
        newMatrix.m01 = right.y;
        newMatrix.m02 = right.z;
        newMatrix.m10 = up.x;
        newMatrix.m11 = up.y;
        newMatrix.m12 = up.z;
        newMatrix.m20 = back.x;
        newMatrix.m21 = back.y;
        newMatrix.m22 = back.z;
        return newMatrix;
    }
}
