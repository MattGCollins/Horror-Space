package horrorspace.file;

import horrorspace.model.ModelDescriptor;
import horrorspace.model.PlyModelProperty;

/**
 *
 * @author Matt
 */
public class PlyFile implements PlyModelProperty{

    @Override
    public void process(String line, ModelDescriptor descriptor) {
        descriptor.setFilename(line);
    }

}
