package horrorspace.gravity;

/**
 *
 * @author Matt
 */
public class PlyPropertyZAccel implements PlyCuboidPartProperty {

    @Override
    public void process(float strength, GravityCuboid cuboid) {
        cuboid.setZAccel(strength);
    }
    
}
