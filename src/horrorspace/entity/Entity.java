package horrorspace.entity;

import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Elle
 */
public class Entity {
    public Vector3f position;
    public float rotation;
    
    public Entity()
    {
        position = new Vector3f(0, 1, 0);
        rotation = 0;
    }
    
    public void update()
    {
        
    }
    
    public void moveTo(float x, float y, float z)
    {
        position.set(x, y, z);
    }
}
