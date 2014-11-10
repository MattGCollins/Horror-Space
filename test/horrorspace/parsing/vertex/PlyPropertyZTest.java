package horrorspace.parsing.vertex;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Matt
 */
public class PlyPropertyZTest {
    private PlyVertexPartProperty property;
    
    public PlyPropertyZTest() {
    }
    
    @Before
    public void setUp() {
        property = new PlyPropertyZ();
    }
    
    @Test
    public void testXIsSetWhenProcessed() {
        float position = new Random().nextFloat();
        Vertex vertex = new Vertex();
        
        property.process(position, vertex);
        
        assertEquals(position, vertex.getZ(), 0.01f);
    }
}