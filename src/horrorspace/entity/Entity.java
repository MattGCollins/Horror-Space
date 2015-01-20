package horrorspace.entity;

import horrorspace.physics.collision.CollisionObject;
import horrorspace.physics.collision.EmptyCollisionObject;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Elle
 */
public class Entity {
    public Vector3f previousPosition;
    public Vector3f position;
    public float rotation;
    
    public Entity()
    {
        position = new Vector3f(0, 1, 0);
        previousPosition = new Vector3f(0, 1, 0);
        rotation = 0;
    }
    
    public void update()
    {
        previousPosition = position;
    }
    
    public void moveTo(float x, float y, float z)
    {
    }
    
    public void collideWith(Entity entity) {
        
    }
    
    public void collideWithStatic(Entity entity) {
        
    }
    
    public CollisionObject getCollisionObject() {
        return new EmptyCollisionObject();
    }
}
