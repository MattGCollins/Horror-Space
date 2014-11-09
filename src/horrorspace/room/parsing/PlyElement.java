/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing;

import horrorspace.util.RoomReader;
import java.io.IOException;

/**
 *
 * @author Matt
 */
public interface PlyElement {

    public void addProperty(String readLine);

    public void process(RoomReader roomReader, RoomPrototype room) throws IOException;
    
}
