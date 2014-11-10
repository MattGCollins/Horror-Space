package horrorspace.parsing.vertex;

import horrorspace.parsing.LoadableItemPrototype;
import java.util.List;

/**
 *
 * @author Matt
 */
public interface VertexHandlingPrototype extends LoadableItemPrototype {
    public void setVertices(List<Vertex> vertices);
}
