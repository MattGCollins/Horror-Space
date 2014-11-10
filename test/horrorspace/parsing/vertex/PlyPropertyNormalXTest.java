package horrorspace.parsing.vertex;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Matt
 */
public class PlyPropertyNormalXTest {
    private PlyVertexPartProperty property;
    
    public PlyPropertyNormalXTest() {
    }
    
    @Before
    public void setUp() {
        property = new PlyPropertyNormalX();
    }
    
    @Test
    public void testNormalXIsSetWhenProcessed() {
        float position = new Random().nextFloat();
        Vertex vertex = new Vertex();
        
        property.process(position, vertex);
        
        assertEquals(position, vertex.getNx(), 0.01f);
    }
}