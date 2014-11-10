/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author Matt
 */
public class BufferedReaderFactory {
    public LinebreakFileReader getReader(String filename) throws FileNotFoundException {
        
        File linebreakFile = new File(filename);
        FileReader reader = new FileReader(linebreakFile);
        return new LinebreakFileReader(new BufferedReader(reader));
    }
}
