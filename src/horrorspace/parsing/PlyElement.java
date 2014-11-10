package horrorspace.parsing;

import horrorspace.util.LinebreakFileReader;
import java.io.IOException;

/**
 *
 * @author Matt
 */
public interface PlyElement {

    public void addProperty(String readLine);

    public void process(LinebreakFileReader linebreakFileReader, LoadableItemPrototype prototype) throws IOException;
    
}
