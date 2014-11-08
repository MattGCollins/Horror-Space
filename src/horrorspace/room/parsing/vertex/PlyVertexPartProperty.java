/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing.vertex;

import horrorspace.room.parsing.PlyProperty;

/**
 *
 * @author Matt
 */
public interface PlyVertexPartProperty extends PlyProperty{

    public void process(float position, Vertex vertex);
}
