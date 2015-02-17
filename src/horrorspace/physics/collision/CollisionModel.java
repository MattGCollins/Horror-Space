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
        for(int i = 0; i < collisionFaces.size(); ++i) {
            System.out.println(collisionFaces.get(i));
        }
    }

    @Override
    public void pushAway(CollisionObject object) {
        for(CollisionFace face : collisionFaces) {
            face.pushAway(object);
        }
    }

}
