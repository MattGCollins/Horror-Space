package horrorspace.parsing.vertex;

import horrorspace.parsing.PlyProperty;

/**
 *
 * @author Matt
 */
public interface PlyVertexPartProperty extends PlyProperty{

    public void process(float position, RenderVertex vertex);
}
