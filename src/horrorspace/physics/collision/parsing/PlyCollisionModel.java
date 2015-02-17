package horrorspace.physics.collision.parsing;

import horrorspace.physics.collision.CollisionModel;
import horrorspace.file.PlyFile;
import horrorspace.model.parsing.ModelDescriptor;
import horrorspace.model.parsing.ModelHandlingPrototype;
import horrorspace.model.parsing.PlyModelProperty;
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
public class PlyCollisionModel implements PlyElement {
    private int collisionModelCount;
    private List<PlyModelProperty> properties = new LinkedList<>();
    
    public PlyCollisionModel(String readLine) {
        String[] split = readLine.split("collisionModel ");
        collisionModelCount = Integer.parseInt(split[1]);
    }
    
    @Override
    public void addProperty(String property) {
        if(property.contains("file")){
            properties.add(new PlyFile());
        }
    }

    @Override
    public void process(LinebreakFileReader linebreakFileReader, LoadableItemPrototype prototype) throws IOException {
        if(!(prototype instanceof ModelHandlingPrototype)){
            throw new ImproperPrototypeException("Cannot load model into prototype.");
        }
        CollisionModelHandlingPrototype collisionModelPrototype = (CollisionModelHandlingPrototype) prototype;
        List<CollisionModel> collisionModels = new LinkedList<>();
        for(int iter = 0; iter < collisionModelCount; ++iter){
            ModelDescriptor descriptor = new ModelDescriptor();
            String readLine = linebreakFileReader.readLine();
            String[] modelParts = readLine.split(" ");
            int propertyIndex = 0;
            for (PlyModelProperty modelProperty : properties) {
                modelProperty.process(modelParts[propertyIndex], descriptor);
                ++propertyIndex;
            }
            collisionModels.add(new CollisionModelLoader().loadFile(descriptor.getFilename()));
        }
        collisionModelPrototype.setCollisionModels(collisionModels);
    }

}
