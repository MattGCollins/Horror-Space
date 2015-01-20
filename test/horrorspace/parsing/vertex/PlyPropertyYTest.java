package horrorspace.parsing.vertex;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Matt
 */
public class PlyPropertyYTest {
    private PlyVertexPartProperty property;
    
    public PlyPropertyYTest() {
    }
    
    @Before
    public void setUp() {
        property = new PlyPropertyY();
    }
    
    @Test
    public void testXIsSetWhenProcessed() {
        float position = new Random().nextFloat();
        RenderVertex vertex = new RenderVertex();
        
        property.process(position, vertex);
        
        assertEquals(position, vertex.getY(), 0.01f);
    }
}