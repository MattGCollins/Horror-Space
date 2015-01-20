package horrorspace.physics.collision.parsing;

import horrorspace.model.parsing.AbstractModelLoader;
import horrorspace.physics.collision.CollisionModel;
import horrorspace.model.*;
import horrorspace.parsing.LoadableItemPrototype;

/**
 *
 * @author Matt
 */
public class CollisionModelLoader extends AbstractModelLoader<CollisionModel>{

    @Override
    protected LoadableItemPrototype getPrototype() {
        return new CollisionModelPrototype();
    }
}
