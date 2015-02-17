package horrorspace.gravity;

/**
 *
 * @author Matt
 */
public class PlyPropertyXMin implements PlyCuboidPartProperty {

    @Override
    public void process(float position, GravityCuboid cuboid) {
        cuboid.setXMin(position);
    }

}
