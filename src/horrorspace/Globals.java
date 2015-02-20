package horrorspace;

import horrorspace.engine.InputKeeper;
import horrorspace.entity.Player;
import horrorspace.physics.PhysicsManager;
import horrorspace.physics.collision.CollisionManager;
import java.util.Random;

/**
 *
 * @author Elle
 */
public class Globals {
    public static final int FACING_NORTH = 0;
    public static final int FACING_EAST = 1;
    public static final int FACING_SOUTH = 2;
    public static final int FACING_WEST = 3;
    
    public static final int DISPLAY_HEIGHT = 600;
    public static final int DISPLAY_WIDTH = 800;
    
    public static final Random rand = new Random();
    public static final InputKeeper input = new InputKeeper();
    public static final CollisionManager collisionManager = new CollisionManager();
    public static final PhysicsManager physicsManager = new PhysicsManager();
    
    public static long curFrameTime = 0;
    public static float frameElapsed = 1.0f / 60.0f;
    public static boolean mapOn = false;
    public static boolean miniMapOn = false;
    public static int sightDepth = 7;
    
    public static Player player;
}
