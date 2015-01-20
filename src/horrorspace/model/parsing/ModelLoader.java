package horrorspace.model.parsing;

import horrorspace.model.Model;
import horrorspace.parsing.LoadableItemPrototype;

/**
 *
 * @author Matt
 */
public class ModelLoader extends AbstractModelLoader<Model>{

    @Override
    protected LoadableItemPrototype getPrototype() {
        return new ModelPrototype();
    }
}
