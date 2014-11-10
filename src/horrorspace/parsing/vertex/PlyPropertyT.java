package horrorspace.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyT implements PlyVertexPartProperty{

    @Override
    public void process(float position, Vertex vertex) {
        vertex.setT(position);
    }
    
}
