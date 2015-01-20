package horrorspace.parsing.vertex;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Matt
 */
public class PlyPropertyXTest {
    private PlyVertexPartProperty property;
    
    public PlyPropertyXTest() {
    }
    
    @Before
    public void setUp() {
        property = new PlyPropertyX();
    }
    
    @Test
    public void testXIsSetWhenProcessed() {
        float position = new Random().nextFloat();
        RenderVertex vertex = new RenderVertex();
        
        property.process(position, vertex);
        
        assertEquals(position, vertex.getX(), 0.01f);
    }
}