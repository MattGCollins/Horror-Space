/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing.vertex;

import horrorspace.room.parsing.PlyElement;
import horrorspace.room.parsing.RoomPrototype;
import horrorspace.util.RoomReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Matt
 */
public class PlyVertex implements PlyElement{
    List<PlyVertexPartProperty> properties = new LinkedList<>();
    int vertexCount;

    public PlyVertex(String readLine) {
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
        }else if(property.contains("float nx")){
            properties.add(new PlyPropertyNormalX());
        }else if(property.contains("float ny")){
            properties.add(new PlyPropertyNormalY());
        }else if(property.contains("float nz")){
            properties.add(new PlyPropertyNormalZ());
        }else if(property.contains("float s")){
            properties.add(new PlyPropertyS());
        }else if(property.contains("float t")){
            properties.add(new PlyPropertyT());
        }
    }

    /**
     *
     * @param roomReader
     * @param room
     * @throws IOException
     */
    @Override
    public void process(RoomReader roomReader, RoomPrototype room) throws IOException{
        List<Vertex> vertices = new ArrayList<>();
        for(int iter = 0; iter < vertexCount; ++iter){
            Vertex vertex = new Vertex();
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
