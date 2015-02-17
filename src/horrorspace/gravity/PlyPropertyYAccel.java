package horrorspace.gravity;

/**
 *
 * @author Matt
 */
public class PlyPropertyYAccel implements PlyCuboidPartProperty {

    @Override
    public void process(float strength, GravityCuboid cuboid) {
        cuboid.setYAccel(strength);
    }
    
}
