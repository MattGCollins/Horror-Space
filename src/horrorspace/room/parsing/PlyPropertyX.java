/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing;

import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Matt
 */
public class PlyPropertyX implements PlyVertexPartProperty{

    @Override
    public void process(float position, Vector3f vertex) {
        vertex.setX(position);
    }
    
}
