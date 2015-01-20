package horrorspace.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyZ implements PlyVertexPartProperty{

    @Override
    public void process(float position, RenderVertex vertex) {
        vertex.setZ(position);
    }
    
}
