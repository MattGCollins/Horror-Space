package horrorspace.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyS implements PlyVertexPartProperty{

    @Override
    public void process(float position, Vertex vertex) {
        vertex.setS(position);
    }
    
}
