/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.physics.collision;

/**
 *
 * @author Matt
 */
public interface CollisionObject {
    public void pushAwayPrimary(CollisionObject object);
    public void pushAwaySecondary(CollisionObject object);
}
