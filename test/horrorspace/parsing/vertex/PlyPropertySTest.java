package horrorspace.parsing.vertex;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Matt
 */
public class PlyPropertySTest {
    private PlyVertexPartProperty property;
    
    public PlyPropertySTest() {
    }
    
    @Before
    public void setUp() {
        property = new PlyPropertyS();
    }
    
    @Test
    public void testXIsSetWhenProcessed() {
        float position = new Random().nextFloat();
        Vertex vertex = new Vertex();
        
        property.process(position, vertex);
        
        assertEquals(position, vertex.getS(), 0.01f);
    }
}