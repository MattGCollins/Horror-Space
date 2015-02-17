package horrorspace.gravity;

import horrorspace.parsing.LoadableItemPrototype;
import horrorspace.parsing.PlyElement;
import horrorspace.parsing.vertex.PlyVertexPartProperty;
import horrorspace.util.LinebreakFileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Matt
 */
public class PlyGravityCuboid implements PlyElement {
    private final int cuboidCount;
    List<PlyCuboidPartProperty> properties = new LinkedList<>();
    

    public PlyGravityCuboid(String readLine) {
        String[] split = readLine.split("gravityCuboid ");
        cuboidCount = Integer.parseInt(split[1]);
    }

    @Override
    public void addProperty(String property) {
        if(property.contains("float xMin")){
            properties.add(new PlyPropertyXMin());
        } else if(property.contains("float yMin")){
            properties.add(new PlyPropertyYMin());
        } else if(property.contains("float zMin")){
            properties.add(new PlyPropertyZMin());
        } else if(property.contains("float xMax")){
            properties.add(new PlyPropertyXMax());
        } else if(property.contains("float yMax")){
            properties.add(new PlyPropertyYMax());
        } else if(property.contains("float zMax")){
            properties.add(new PlyPropertyZMax());
        } else if(property.contains("float xAccel")){
            properties.add(new PlyPropertyXAccel());
        } else if(property.contains("float yAccel")){
            properties.add(new PlyPropertyYAccel());
        } else if(property.contains("float zAccel")){
            properties.add(new PlyPropertyZAccel());
        }
    }

    @Override
    public void process(LinebreakFileReader linebreakFileReader, LoadableItemPrototype prototype) throws IOException {
    }

}
