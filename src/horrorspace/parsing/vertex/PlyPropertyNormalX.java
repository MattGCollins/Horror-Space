package horrorspace.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyNormalX implements PlyVertexPartProperty{

    @Override
    public void process(float position, RenderVertex vertex) {
        vertex.setNx(position);
    }
    
}
