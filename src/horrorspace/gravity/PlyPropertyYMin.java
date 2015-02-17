package horrorspace.gravity;

/**
 *
 * @author Matt
 */
public class PlyPropertyYMin implements PlyCuboidPartProperty {

    @Override
    public void process(float position, GravityCuboid cuboid) {
        cuboid.setYMin(position);
    }

}
