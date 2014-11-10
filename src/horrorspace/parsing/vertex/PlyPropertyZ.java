package horrorspace.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyZ implements PlyVertexPartProperty{

    @Override
    public void process(float position, Vertex vertex) {
        vertex.setZ(position);
    }
    
}
