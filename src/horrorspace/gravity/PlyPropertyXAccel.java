package horrorspace.gravity;

/**
 *
 * @author Matt
 */
public class PlyPropertyXAccel implements PlyCuboidPartProperty {

    @Override
    public void process(float strength, GravityCuboid cuboid) {
        cuboid.setXAccel(strength);
    }
    
}
