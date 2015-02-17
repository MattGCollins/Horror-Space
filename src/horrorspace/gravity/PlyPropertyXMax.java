package horrorspace.gravity;

/**
 *
 * @author Matt
 */
public class PlyPropertyXMax implements PlyCuboidPartProperty {

    @Override
    public void process(float position, GravityCuboid cuboid) {
        cuboid.setXMax(position);
    }

}
