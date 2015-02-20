package horrorspace.gravity;

import horrorspace.parsing.LoadableItemPrototype;
import java.util.List;

/**
 *
 * @author Matt
 */
public interface GravityVolumeHandlingPrototype extends LoadableItemPrototype {
    public void setGravityVolumes(List<GravityVolume> gravityCuboids);
}
