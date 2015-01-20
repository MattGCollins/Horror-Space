package horrorspace.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyNormalY implements PlyVertexPartProperty{

    @Override
    public void process(float position, RenderVertex vertex) {
        vertex.setNy(position);
    }
    
}
