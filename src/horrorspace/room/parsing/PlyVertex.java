/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing;

import horrorspace.room.Room;
import horrorspace.util.RoomReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Matt
 */
public class PlyVertex implements PlyElement{
    List<PlyVertexPartProperty> properties = new LinkedList<>();
    int vertexCount;

    PlyVertex(String readLine) {
        String[] split = readLine.split("vertex ");
        vertexCount = Integer.parseInt(split[1]);
    }
    
    /**
     *
     * @param property
     */
    @Override
    public void addProperty(String property){
        if(property.contains("float x")){
            properties.add(new PlyPropertyX());
        }else if(property.contains("float y")){
            properties.add(new PlyPropertyY());
        }else if(property.contains("float z")){
            properties.add(new PlyPropertyZ());
        }
    }

    /**
     *
     * @param roomReader
     * @param room
     * @throws IOException
     */
    @Override
    public void process(RoomReader roomReader, Room room) throws IOException{
        List<Vector3f> vertices = new ArrayList<>();
        for(int iter = 0; iter < vertexCount; ++iter){
            Vector3f vertex = new Vector3f();
            String readLine = roomReader.readLine();
            String[] coordinates = readLine.split(" ");
            int coordinateIndex = 0;
            for(PlyVertexPartProperty property : properties) {
                property.process(Float.parseFloat(coordinates[coordinateIndex]), vertex);
                ++coordinateIndex;
            }
            vertices.add(vertex);
        }
        room.setVertices(vertices);
    }
}
