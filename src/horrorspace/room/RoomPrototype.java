package horrorspace.room;

import horrorspace.collision.CollisionModelHandlingPrototype;
import horrorspace.model.Model;
import horrorspace.model.ModelHandlingPrototype;
import java.util.List;

/**
 *
 * @author Matt
 */
public class RoomPrototype implements ModelHandlingPrototype, CollisionModelHandlingPrototype {
    private List<Model> models;

    /**
     *
     * @param models
     */
    @Override
    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public Object generateItem() {
        return new Room(models);
    }

}
