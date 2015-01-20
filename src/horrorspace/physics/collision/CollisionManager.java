package horrorspace.physics.collision;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matt
 */
public class CollisionManager {
    private List<CollisionObject> objects = new ArrayList<>();
    private List<CollisionObject> staticObjects = new ArrayList<>();
    
    public void addEntity(CollisionObject object) {
        objects.add(object);
    }
    
    public void removeEntity(CollisionObject object) {
        objects.remove(object);
    }
    public void addStaticEntity(CollisionObject object) {
        staticObjects.add(object);
    }
    
    public void removeStaticEntity(CollisionObject object) {
        staticObjects.remove(object);
    }
    
    public void update() {
            collideMobileEntities();
            collideMobileWithStatic();
    }

    private void collideMobileEntities() {
        for(int i = 0; i < objects.size() - 1; ++i){
            CollisionObject object = objects.get(i);
            for(int j = i + 1; j < objects.size(); ++j) {
//                Add Collision Here
            }
        }
    }

    private void collideMobileWithStatic() {
        for(CollisionObject object : objects){
            for(CollisionObject staticObject : staticObjects) {
                staticObject.pushAway(object);
            }
        }
    }
}
