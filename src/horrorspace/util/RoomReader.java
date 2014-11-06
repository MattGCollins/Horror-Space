/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.util;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Matt
 */
public class RoomReader {
    private final BufferedReader bufferedReader;

    RoomReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }
    
    public String readLine() throws IOException{
        String readLine;
        do {            
            readLine = bufferedReader.readLine();
        } while (readLine != null && readLine.contains("comment"));
        return readLine;
    }
    
}
