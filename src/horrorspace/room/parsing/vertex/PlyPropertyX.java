/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyX implements PlyVertexPartProperty{

    @Override
    public void process(float position, Vertex vertex) {
        vertex.setX(position);
    }
    
}
