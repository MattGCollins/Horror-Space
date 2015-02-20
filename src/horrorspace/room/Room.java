package horrorspace.room;

import horrorspace.entity.Entity;
import horrorspace.gravity.GravityVolume;
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
    private final List<GravityVolume> gravityVolumes;

    public Room(List<Model> models, List<CollisionModel> collisionModels, List<GravityVolume> gravityVolumes) {
        this.models = models;
        this.collisionModels = collisionModels;
        for(CollisionModel collisionModel : this.collisionModels) {
            collisionModel.init();
        }
        this.gravityVolumes = gravityVolumes;
    }
    
    public void render() {
        for (Model model : models) {
            model.render();
        }
    }

    public void applyGravity(Entity entity) {
        for(GravityVolume gravityVolume : gravityVolumes) {
            gravityVolume.applyGravity(entity);
        }
    }
}
