package horrorspace.parsing.vertex;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Matt
 */
public class PlyPropertyNormalZTest {
    private PlyVertexPartProperty property;
    
    public PlyPropertyNormalZTest() {
    }
    
    @Before
    public void setUp() {
        property = new PlyPropertyNormalZ();
    }
    
    @Test
    public void testXIsSetWhenProcessed() {
        float position = new Random().nextFloat();
        RenderVertex vertex = new RenderVertex();
        
        property.process(position, vertex);
        
        assertEquals(position, vertex.getNz(), 0.01f);
    }
}