/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.physics.collision.parsing;

import horrorspace.physics.collision.CollisionModel;
import horrorspace.parsing.LoadableItemPrototype;
import java.util.List;

/**
 *
 * @author Matt
 */
public interface CollisionModelHandlingPrototype extends LoadableItemPrototype {
    public void setCollisionModels(List<CollisionModel> models);
}
