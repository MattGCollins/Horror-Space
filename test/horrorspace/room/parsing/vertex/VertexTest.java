/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing.vertex;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Matt
 */
public class VertexTest {
    private Vertex vertex;
    
    @Before
    public void setUp() {
        vertex = new Vertex();
    }
    
     @Test
     public void testXIsStored() {
        float stored = new Random().nextFloat();
        
        vertex.setX(stored);
        
        assertEquals(stored, vertex.getX(), 0.01);
     }
    
     @Test
     public void testYIsStored() {
        float stored = new Random().nextFloat();
        
        vertex.setY(stored);
        
        assertEquals(stored, vertex.getY(), 0.01);
     }
    
     @Test
     public void testZIsStored() {
        float stored = new Random().nextFloat();
        
        vertex.setZ(stored);
        
        assertEquals(stored, vertex.getZ(), 0.01);
     }
    
     @Test
     public void testNxIsStored() {
        float stored = new Random().nextFloat();
        
        vertex.setNx(stored);
        
        assertEquals(stored, vertex.getNx(), 0.01);
     }
    
     @Test
     public void testNyIsStored() {
        float stored = new Random().nextFloat();
        
        vertex.setNy(stored);
        
        assertEquals(stored, vertex.getNy(), 0.01);
     }
    
     @Test
     public void testNzIsStored() {
        float stored = new Random().nextFloat();
        
        vertex.setNz(stored);
        
        assertEquals(stored, vertex.getNz(), 0.01);
     }
    
     @Test
     public void testSIsStored() {
        float stored = new Random().nextFloat();
        
        vertex.setS(stored);
        
        assertEquals(stored, vertex.getS(), 0.01);
     }
    
     @Test
     public void testTIsStored() {
        float stored = new Random().nextFloat();
        
        vertex.setT(stored);
        
        assertEquals(stored, vertex.getT(), 0.01);
     }
    
     @Test
     public void testGetPositionReturnsXYZ() {
        float x = new Random().nextFloat();
        float y = new Random().nextFloat();
        float z = new Random().nextFloat();
        setPosition(x, y, z);
        
        float[] position = vertex.getPosition();
        
        assertEquals(x, position[0], 0.01);
        assertEquals(y, position[1], 0.01);
        assertEquals(z, position[2], 0.01);
     }
    
     @Test
     public void testGetNormalReturnsNxNyNz() {
        float nx = new Random().nextFloat();
        float ny = new Random().nextFloat();
        float nz = new Random().nextFloat();
        setNormal(nx, ny, nz);
        
        float[] normal = vertex.getNormal();
        
        assertEquals(nx, normal[0], 0.01);
        assertEquals(ny, normal[1], 0.01);
        assertEquals(nz, normal[2], 0.01);
     }
    
     @Test
     public void testGetTextureReturnsST() {
        float s = new Random().nextFloat();
        float t = new Random().nextFloat();
        setTexture(s, t);
        
        float[] texture = vertex.getTexture();
        
        assertEquals(s, texture[0], 0.01);
        assertEquals(t, texture[1], 0.01);
     }
    
     @Test
     public void testGetVertexReturnsPositionNormalTexture() {
        float x = new Random().nextFloat();
        float y = new Random().nextFloat();
        float z = new Random().nextFloat();
        float nx = new Random().nextFloat();
        float ny = new Random().nextFloat();
        float nz = new Random().nextFloat();
        float s = new Random().nextFloat();
        float t = new Random().nextFloat();
        setPosition(x, y, z);
        setNormal(nx, ny, nz);
        setTexture(s, t);
        
        float[] allData = vertex.getVertex();
        
        
        assertEquals(8, allData.length);
        assertEquals(x, allData[0], 0.01);
        assertEquals(y, allData[1], 0.01);
        assertEquals(z, allData[2], 0.01);
        assertEquals(nx, allData[3], 0.01);
        assertEquals(ny, allData[4], 0.01);
        assertEquals(nz, allData[5], 0.01);
        assertEquals(s, allData[6], 0.01);
        assertEquals(t, allData[7], 0.01);
     }
    
     @Test
     public void testGetVertexFunctionsWhenNoNormal() {
        float x = new Random().nextFloat();
        float y = new Random().nextFloat();
        float z = new Random().nextFloat();
        float s = new Random().nextFloat();
        float t = new Random().nextFloat();
        setPosition(x, y, z);
        setTexture(s, t);
        
        float[] allData = vertex.getVertex();
        
        assertEquals(5, allData.length);
        assertEquals(x, allData[0], 0.01);
        assertEquals(y, allData[1], 0.01);
        assertEquals(z, allData[2], 0.01);
        assertEquals(s, allData[3], 0.01);
        assertEquals(t, allData[4], 0.01);
     }
    
     @Test
     public void testGetVertexFunctionsWhenNoTexture() {
        float x = new Random().nextFloat();
        float y = new Random().nextFloat();
        float z = new Random().nextFloat();
        float nx = new Random().nextFloat();
        float ny = new Random().nextFloat();
        float nz = new Random().nextFloat();
        setPosition(x, y, z);
        setNormal(nx, ny, nz);
        
        float[] allData = vertex.getVertex();
        
        assertEquals(6, allData.length);
        assertEquals(x, allData[0], 0.01);
        assertEquals(y, allData[1], 0.01);
        assertEquals(z, allData[2], 0.01);
        assertEquals(nx, allData[3], 0.01);
        assertEquals(ny, allData[4], 0.01);
        assertEquals(nz, allData[5], 0.01);
     }
    
     @Test
     public void testGetVertexFunctionsWhenNoNormalOrTexture() {
        float x = new Random().nextFloat();
        float y = new Random().nextFloat();
        float z = new Random().nextFloat();
        setPosition(x, y, z);
        
        float[] allData = vertex.getVertex();
        
        assertEquals(3, allData.length);
        assertEquals(x, allData[0], 0.01);
        assertEquals(y, allData[1], 0.01);
        assertEquals(z, allData[2], 0.01);
     }
     
     @Test
     public void testIsNormalExistsReturnsTrueAfterSet() {
         assertFalse(vertex.isNormalExists());
         
         setNormal(0f, 1f, 2f);
         
         assertTrue(vertex.isNormalExists());
     }
     
     @Test
     public void testIsTextureExistsReturnsTrueAfterSet() {
         assertFalse(vertex.isTextureExists());
         
         setTexture(0f, 1f);
         
         assertTrue(vertex.isTextureExists());
     }
     
     @Test
     public void testVertexFloatSizeInitially() {
         assertEquals(3, vertex.getVertexFloatSize());
     }
     
     @Test
     public void testVertexFloatSizeIncreasesAfterSetNormal() {
         setNormal(0f, 1f, 2f);
         
         assertEquals(6, vertex.getVertexFloatSize());
     }
     
     @Test
     public void testVertexFloatSizeIncreasesAfterSetTexture() {
         setTexture(0f, 1f);
         
         assertEquals(5, vertex.getVertexFloatSize());
     }
     
     @Test
     public void testVertexFloatSizeIncreasesAfterSetNormalAndTexture() {
         setNormal(0f, 1f, 2f);
         setTexture(0f, 1f);
         
         assertEquals(8, vertex.getVertexFloatSize());
     }
     
     @Test
     public void testVertexStrideIs4TimesVertexFloatSize() {
         assertEquals(12, vertex.getVertexStride());
     }
     
     @Test
     public void testVertexStrideWithNormal() {
         setNormal(0f, 1f, 2f);
         
         assertEquals(24, vertex.getVertexStride());
     }
     
     @Test
     public void testVertexStrideWithTexture() {
         setTexture(0f, 1f);
         
         assertEquals(20, vertex.getVertexStride());
     }
     
     @Test
     public void testVertexStrideWithNormalAndTexture() {
         setNormal(0f, 1f, 2f);
         setTexture(0f, 1f);
         
         assertEquals(32, vertex.getVertexStride());
     }
     
     @Test
     public void testGetNormalOffset() {         
         assertEquals(12, vertex.getNormalOffset());
     }
     
     @Test
     public void testGetTextureOffsetChangesWithNormal() {         
         assertEquals(12, vertex.getTextureOffset());
         
         setNormal(0f, 1f, 2f);
         
         assertEquals(24, vertex.getTextureOffset());
     }
     
     private void setPosition(float x, float y, float z) {
        vertex.setX(x);
        vertex.setY(y);
        vertex.setZ(z);
     }
     
     private void setNormal(float nx, float ny, float nz) {
        vertex.setNx(nx);
        vertex.setNy(ny);
        vertex.setNz(nz);
     }
     
     private void setTexture(float s, float t) {
        vertex.setS(s);
        vertex.setT(t);
     }
}