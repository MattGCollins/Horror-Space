package horrorspace.physics.collision;

import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Matt
 */
public interface CollisionSphere extends CollisionObject {
    public Vector3f getPosition();
    public void setPosition(Vector3f vector);
    public float getRadius();
}
