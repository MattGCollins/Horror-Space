package horrorspace.physics.collision;

/**
 *
 * @author Matt
 */
public class EmptyCollisionObject implements CollisionObject {

    @Override
    public void pushAwayPrimary(CollisionObject object) {
    }

    @Override
    public void pushAwaySecondary(CollisionObject object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
