package horrorspace.entity;

import horrorspace.Globals;
import horrorspace.physics.collision.CollisionObject;
import horrorspace.physics.collision.EmptyCollisionObject;
import org.lwjgl.util.vector.Vector3f;

import static horrorspace.math.HorrorMath.add;
import static horrorspace.math.HorrorMath.scaleVector;
import static horrorspace.math.HorrorMath.square;
import static horrorspace.math.HorrorMath.dotProduct;
import org.lwjgl.util.vector.Quaternion;

/**
 *
 * @author Elle
 */
public class Entity {
    private Vector3f previousPosition;
    private Vector3f position;
    private Vector3f velocity;
    private Vector3f acceleration;
    private Vector3f gravity = new Vector3f();
    private boolean grounded = false;
    
    private static final float FLOOR_DOT = -0.7f;
    
    public Entity()
    {
        position = new Vector3f(0, 1, 0);
        previousPosition = new Vector3f(0, 1, 0);
        velocity = new Vector3f(0, 0, 0);
        acceleration = new Vector3f(0, 0, 0);
    }
    
    public void update()
    {
        Vector3f frameAcceleration = scaleVector(acceleration, square(Globals.frameElapsed) / 2);
        Vector3f frameMovement = add(scaleVector(velocity, Globals.frameElapsed), frameAcceleration);
        position = add(position, frameMovement);
        velocity = add(velocity, acceleration);
        addDrag();
        previousPosition = position;
        acceleration.set(0, 0, 0);
        grounded = false;
    }

    private void addDrag() {
        
        final float dragAmount = grounded ? 20 : 1;
        Vector3f dragAcceleration = scaleVector(velocity, 1 / (dragAmount * Globals.frameElapsed + 1) - 1);
        velocity = add(velocity, dragAcceleration);
    }
    
    public Vector3f getPreviousPosition() {
        return previousPosition;
    }
    
    public void setPosition(Vector3f position) {
        this.position = position;
    }
    
    public Vector3f getPosition() {
        return position;
    }
    
    public void move(Vector3f position) {
        this.position = add(this.position, position);
    }
    
    public void setVelocity(Vector3f velocity) {
        this.velocity = velocity;
    }
    
    public Vector3f getVelocity() {
        return velocity;
    }
    
    public void accelerate(Vector3f acceleration) {
        this.acceleration = add(this.acceleration, acceleration);
    }
    
    public void applyGravity(Vector3f acceleration) {
        accelerate(acceleration);
        gravity = acceleration;
    }
    
    public void reboundSurface(Vector3f vector) {
        move(vector);
        Vector3f normal = new Vector3f();
        vector.normalise(normal);
        float scale = dotProduct(velocity, normal);
        if(scale < 0) {
            setVelocity(add(velocity, scaleVector(normal, -scale)));
        }
        checkIfFloor(normal);
    }

    private void checkIfFloor(Vector3f normal) {
        Vector3f gravityNormal = new Vector3f();
        gravity.normalise(gravityNormal);
        float floorness = dotProduct(gravityNormal, normal);
        if(floorness < FLOOR_DOT){
            grounded = true;
        }
    }
    
    public boolean isGrounded(){
        return grounded;
    }
    
    public void collideWith(Entity entity) {
        
    }
    
    public void collideWithStatic(Entity entity) {
        
    }
    
    public CollisionObject getCollisionObject() {
        return new EmptyCollisionObject();
    }
}
