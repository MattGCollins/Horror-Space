package horrorspace.model;

import horrorspace.parsing.LoadableItemPrototype;
import java.util.List;

/**
 *
 * @author Matt
 */
public interface ModelHandlingPrototype extends LoadableItemPrototype {
    public void setModels(List<Model> models);
}
