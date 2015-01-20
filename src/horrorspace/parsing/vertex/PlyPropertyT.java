package horrorspace.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyT implements PlyVertexPartProperty{

    @Override
    public void process(float position, RenderVertex vertex) {
        vertex.setT(position);
    }
    
}
