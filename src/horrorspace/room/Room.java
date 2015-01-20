package horrorspace.room;

import horrorspace.model.Model;
import horrorspace.physics.collision.CollisionModel;
import java.util.List;

/**
 *
 * @author Matt
 */
public class Room {
    private final List<Model> models;
    private final List<CollisionModel> collisionModels;

    public Room(List<Model> models, List<CollisionModel> collisionModels) {
        this.models = models;
        this.collisionModels = collisionModels;
    }
    
    public void render() {
        for (Model model : models) {
            model.render();
        }
    }
}
