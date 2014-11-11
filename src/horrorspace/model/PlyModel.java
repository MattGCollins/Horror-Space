package horrorspace.model;

import horrorspace.file.PlyFile;
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
public class PlyModel implements PlyElement{
    private int modelCount;
    private List<PlyModelProperty> properties = new LinkedList<>();
    
    public PlyModel(String readLine) {
        String[] split = readLine.split("model ");
        modelCount = Integer.parseInt(split[1]);
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
        ModelHandlingPrototype modelPrototype = (ModelHandlingPrototype) prototype;
        List<Model> models = new LinkedList<>();
        for(int iter = 0; iter < modelCount; ++iter){
            ModelDescriptor descriptor = new ModelDescriptor();
            String readLine = linebreakFileReader.readLine();
            String[] modelParts = readLine.split(" ");
            int propertyIndex = 0;
            for (PlyModelProperty modelProperty : properties) {
                modelProperty.process(modelParts[propertyIndex], descriptor);
                ++propertyIndex;
            }
            models.add(new ModelLoader().loadFile(descriptor.getFilename()));
        }
        modelPrototype.setModels(models);
    }
}
