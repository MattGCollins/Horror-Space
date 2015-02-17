package horrorspace.gravity;

/**
 *
 * @author Matt
 */
public class PlyPropertyZMin implements PlyCuboidPartProperty {

    @Override
    public void process(float position, GravityCuboid cuboid) {
        cuboid.setZMin(position);
    }

}
