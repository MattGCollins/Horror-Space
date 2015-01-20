package horrorspace.parsing.vertex;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Matt
 */
public class PlyPropertyNormalYTest {
    private PlyVertexPartProperty property;
    
    public PlyPropertyNormalYTest() {
    }
    
    @Before
    public void setUp() {
        property = new PlyPropertyNormalY();
    }
    
    @Test
    public void testXIsSetWhenProcessed() {
        float position = new Random().nextFloat();
        RenderVertex vertex = new RenderVertex();
        
        property.process(position, vertex);
        
        assertEquals(position, vertex.getNy(), 0.01f);
    }
}