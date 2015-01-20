package horrorspace.parsing.vertex;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Matt
 */
public class PlyPropertyTTest {
    private PlyVertexPartProperty property;
    
    public PlyPropertyTTest() {
    }
    
    @Before
    public void setUp() {
        property = new PlyPropertyT();
    }
    
    @Test
    public void testXIsSetWhenProcessed() {
        float position = new Random().nextFloat();
        RenderVertex vertex = new RenderVertex();
        
        property.process(position, vertex);
        
        assertEquals(position, vertex.getT(), 0.01f);
    }
}