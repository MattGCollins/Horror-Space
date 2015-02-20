package horrorspace.room.parsing;

import horrorspace.gravity.GravityVolume;
import horrorspace.gravity.GravityVolumeHandlingPrototype;
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
public class RoomPrototype implements ModelHandlingPrototype, CollisionModelHandlingPrototype, GravityVolumeHandlingPrototype {
    private List<Model> models;
    private List<CollisionModel> collisionModels;
    private List<GravityVolume> gravityVolumes;

    @Override
    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public void setCollisionModels(List<CollisionModel> collisionModels) {
        this.collisionModels = collisionModels;
    }

    @Override
    public void setGravityVolumes(List<GravityVolume> gravityVolumes) {
        this.gravityVolumes = gravityVolumes;
    }

    @Override
    public Object generateItem() {
        return new Room(models, collisionModels, gravityVolumes);
    }

}
