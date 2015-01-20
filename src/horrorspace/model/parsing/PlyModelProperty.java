package horrorspace.model.parsing;

/**
 *
 * @author Matt
 */
public interface PlyModelProperty {
    /**
     *
     * @param line
     * @param descriptor
     */
    public void process(String line, ModelDescriptor descriptor);
}
