package horrorspace.gravity;

import horrorspace.parsing.ImproperPrototypeException;
import horrorspace.parsing.LoadableItemPrototype;
import horrorspace.parsing.PlyElement;
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
        if(!(prototype instanceof GravityVolumeHandlingPrototype)){
            throw new ImproperPrototypeException("Cannot load model into prototype.");
        }
        GravityVolumeHandlingPrototype gravityCuboidPrototype = (GravityVolumeHandlingPrototype) prototype;
        List<GravityVolume> gravityCuboids = new LinkedList<>();
        for(int iter = 0; iter < cuboidCount; ++iter){
            GravityCuboid cuboid = new GravityCuboid();
            String readLine = linebreakFileReader.readLine();
            String[] cuboidParts = readLine.split(" ");
            int propertyIndex = 0;
            for (PlyCuboidPartProperty cuboidProperty : properties) {
                cuboidProperty.process(Float.valueOf(cuboidParts[propertyIndex]).floatValue(), cuboid);
                ++propertyIndex;
            }
            gravityCuboids.add(cuboid);
        }
        gravityCuboidPrototype.setGravityVolumes(gravityCuboids);
    }

}
