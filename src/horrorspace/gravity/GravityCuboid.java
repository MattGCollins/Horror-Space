package horrorspace.gravity;

import horrorspace.entity.Entity;
import org.lwjgl.util.vector.Vector3f;

import static horrorspace.math.HorrorMath.isPointInsideBox;

/**
 *
 * @author Matt
 */
public class GravityCuboid implements GravityVolume {
    private Vector3f minPosition = new Vector3f();
    private Vector3f maxPosition = new Vector3f();
    private Vector3f accelVector = new Vector3f();
    
    public void setXMin(float x){
        minPosition.setX(x);
    }
    public void setYMin(float y){
        minPosition.setY(y);
    }
    public void setZMin(float z){
        minPosition.setZ(z);
    }
    
    public void setXMax(float x){
        maxPosition.setX(x);
    }
    public void setYMax(float y){
        maxPosition.setY(y);
    }
    public void setZMax(float z){
        maxPosition.setZ(z);
    }
    
    public void setXAccel(float x){
        accelVector.setX(x);
    }
    public void setYAccel(float y){
        accelVector.setY(y);
    }
    public void setZAccel(float z){
        accelVector.setZ(z);
    }
    
    public Vector3f getMinPosition() {
        return minPosition;
    }
    public Vector3f getMaxPosition() {
        return maxPosition;
    }
    public Vector3f getGravityVector() {
        return accelVector;
    }

    @Override
    public void applyGravity(Entity entity) {
        entity.applyGravity(accelVector);
    }

    @Override
    public boolean includes(Entity entity) {
        if(isPointInsideBox(entity.getPosition(), minPosition, maxPosition)){
            return true;
        }
        return false;
    }
}
