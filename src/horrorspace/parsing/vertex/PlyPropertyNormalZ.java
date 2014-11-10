package horrorspace.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyNormalZ implements PlyVertexPartProperty{

    @Override
    public void process(float position, Vertex vertex) {
        vertex.setNz(position);
    }
    
}
