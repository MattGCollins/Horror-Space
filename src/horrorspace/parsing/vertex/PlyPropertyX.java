package horrorspace.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyX implements PlyVertexPartProperty{

    @Override
    public void process(float position, Vertex vertex) {
        vertex.setX(position);
    }
    
}
