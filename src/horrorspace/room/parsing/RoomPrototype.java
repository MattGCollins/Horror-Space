package horrorspace.room.parsing;

import horrorspace.physics.collision.CollisionModel;
import horrorspace.physics.collision.parsing.CollisionModelHandlingPrototype;
import horrorspace.model.Model;
import horrorspace.model.parsing.ModelHandlingPrototype;
import horrorspace.room.Room;
import java.util.List;

/**
 *
 * @author Matt
 */
public class RoomPrototype implements ModelHandlingPrototype, CollisionModelHandlingPrototype {
    private List<Model> models;
    private List<CollisionModel> collisionModels;

    /**
     *
     * @param models
     */
    @Override
    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public void setCollisionModels(List<CollisionModel> collisionModels) {
        this.collisionModels = collisionModels;
    }

    @Override
    public Object generateItem() {
        return new Room(models, collisionModels);
    }

}
