/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing;

import horrorspace.room.Room;
import horrorspace.util.BufferedReaderFactory;
import horrorspace.util.RoomReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Matt
 */
public class RoomLoader {
    int BUFFER_SIZE = 200;
    RoomReader roomReader;
    String readLine = "";
    List<PlyElement> elements = new LinkedList<>();
    
    public Room loadRoom(String filename) throws IOException{
        roomReader = BufferedReaderFactory.getReader(filename);
        readHeader();
        return parseRoom();
    }

    private void readHeader() throws IOException {
        checkTypeIsCorrect(roomReader);
        readLine();
        while (!"end_header".equals(readLine)) {            
            if(readLine.contains("element")){
                pushElement();
            } else {
                readLine();
            }
        }
    }

    private void checkTypeIsCorrect(RoomReader bufferedReader) throws IOException {
        readLine();
        if(!"ply".equals(readLine)){
            throw new RoomUnloadableException("Room was not of type \"ply\".");
        }
        readLine();
        if(!"format ascii 1.0".equals(readLine)){
            throw new RoomUnloadableException("Room was not the proper format.");
        }
    }

    private void pushElement() throws IOException {
        PlyElement element;
        //Push element
        if(readLine.contains("vertex")) {
            element = new PlyVertex(readLine);
        } else if(readLine.contains("face")) {
            element = new PlyFace(readLine);
        } else {
            readLine();
            return;
        }
        elements.add(element);
        
        //Add properties
        readLine();
        int i = 0;
        while (readLine.contains("property")){
            element.addProperty(readLine);
            readLine();
        }
    }

    private Room parseRoom() throws IOException {
        final RoomPrototype room = new RoomPrototype();
        for(PlyElement element : elements){
            element.process(roomReader, room);
        }
        return room.generateRoom();
    }
    
    private void readLine() throws IOException{
        readLine = roomReader.readLine();
    }
}
