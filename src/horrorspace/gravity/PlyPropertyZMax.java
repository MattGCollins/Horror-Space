package horrorspace.gravity;

/**
 *
 * @author Matt
 */
public class PlyPropertyZMax implements PlyCuboidPartProperty {

    @Override
    public void process(float position, GravityCuboid cuboid) {
        cuboid.setZMax(position);
    }

}
