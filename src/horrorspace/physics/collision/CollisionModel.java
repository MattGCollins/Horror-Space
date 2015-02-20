package horrorspace.physics.collision;

import horrorspace.Globals;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matt
 */
public class CollisionModel implements CollisionObject {
    List<CollisionFace> collisionFaces = new ArrayList<>();
    
    public CollisionModel() {
    }
    
    public void init() {
        Globals.collisionManager.addStaticEntity(this);
    }

    /**
     *
     * @param collisionFaces
     */
    public void setFaces(List<CollisionFace> collisionFaces) {
        this.collisionFaces = collisionFaces;
    }

    @Override
    public void pushAwayPrimary(CollisionObject object) {
        for(CollisionFace face : collisionFaces) {
            face.pushAwayPrimary(object);
        }
    }

    @Override
    public void pushAwaySecondary(CollisionObject object) {
        for(CollisionFace face : collisionFaces) {
            face.pushAwaySecondary(object);
        }
    }

}
