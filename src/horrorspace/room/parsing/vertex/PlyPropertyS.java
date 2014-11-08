/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing.vertex;

/**
 *
 * @author Matt
 */
public class PlyPropertyS implements PlyVertexPartProperty{

    @Override
    public void process(float position, Vertex vertex) {
        vertex.setS(position);
    }
    
}
