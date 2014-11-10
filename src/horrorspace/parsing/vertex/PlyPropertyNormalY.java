package horrorspace.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyNormalY implements PlyVertexPartProperty{

    @Override
    public void process(float position, Vertex vertex) {
        vertex.setNy(position);
    }
    
}
