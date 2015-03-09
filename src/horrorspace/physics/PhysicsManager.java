package horrorspace.physics;

import horrorspace.Globals;
import horrorspace.room.Room;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matt
 */
public class PhysicsManager {
    List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }
    
    public void update() {
        Globals.player.removeGravity();
        rooms.get(0).applyGravity(Globals.player);
        Globals.player.update();
        Globals.collisionManager.update();
    }

}
