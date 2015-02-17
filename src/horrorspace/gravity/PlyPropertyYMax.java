package horrorspace.gravity;

/**
 *
 * @author Matt
 */
public class PlyPropertyYMax implements PlyCuboidPartProperty {

    @Override
    public void process(float position, GravityCuboid cuboid) {
        cuboid.setYMax(position);
    }

}
