/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Matt
 */
public class BufferedReaderFactory {
    public static RoomReader getReader(String filename) throws FileNotFoundException {
        
        File roomFile = new File(filename);
        FileReader reader = new FileReader(roomFile);
        return new RoomReader(new BufferedReader(reader));
    }
}
