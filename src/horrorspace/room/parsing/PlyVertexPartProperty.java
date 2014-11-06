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
public interface PlyVertexPartProperty extends PlyProperty{

    public void process(float position, Vector3f vertex);
}
