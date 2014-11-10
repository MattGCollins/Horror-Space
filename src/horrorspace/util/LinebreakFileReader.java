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
public class LinebreakFileReader {
    private final BufferedReader bufferedReader;
    String readLine;

    LinebreakFileReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }
    
    public String readLine() throws IOException{
        do {            
            readLine = bufferedReader.readLine();
        } while (readLine != null && readLine.contains("comment"));
        return readLine;
    }
    
    public String getLastLine() {
        return readLine;
    }
    
}
