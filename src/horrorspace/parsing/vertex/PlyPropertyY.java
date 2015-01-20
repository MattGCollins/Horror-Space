package horrorspace.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyY implements PlyVertexPartProperty{

    @Override
    public void process(float position, RenderVertex vertex) {
        vertex.setY(position);
    }
    
}
