package horrorspace.file;

import horrorspace.model.parsing.ModelDescriptor;
import horrorspace.model.parsing.PlyModelProperty;

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
