/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing;

import horrorspace.util.RoomReader;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Matt
 */
public class PlyPropertyY implements PlyVertexPartProperty{

    @Override
    public void process(float position, Vector3f vertex) {
        vertex.setY(position);
    }
    
}
