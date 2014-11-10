package horrorspace.parsing.face;

import horrorspace.parsing.LoadableItemPrototype;
import java.util.List;

/**
 *
 * @author Matt
 */
public interface FaceHandlingPrototype extends LoadableItemPrototype {
    public void setFaces(List<Face> faces);
}
