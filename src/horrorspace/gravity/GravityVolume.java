/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.gravity;

import horrorspace.entity.Entity;

/**
 *
 * @author Matt
 */
public interface GravityVolume {

    public void applyGravity(Entity entity);

    public boolean includes(Entity entity);
    
}
