package horrorspace;

import horrorspace.room.parsing.RoomLoader;
import horrorspace.rendering.Shaders;
import horrorspace.entity.Player;
import horrorspace.math.HorrorMath;
import horrorspace.room.Room;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;
import static org.lwjgl.util.glu.GLU.gluOrtho2D;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

/**
 *
 * @author Matt
 */
public class HorrorSpace {

    
    public static final Logger LOGGER = Logger.getLogger(HorrorSpace.class.getName());
    private static final DisplayProperties displayProperties = DisplayProperties.getInstance();
    private Room room;

    static {
        try {
        LOGGER.addHandler(new FileHandler("errors.log",true));
        }
        catch(IOException ex) {
        LOGGER.log(Level.WARNING,ex.toString(),ex);
        }
    }

    public static void main(String[] args) {
        HorrorSpace main = null;
        try {
        System.out.println("Keys:");
        System.out.println("Down/S  - Move Backward");
        System.out.println("Up/W    - Move Forward");
        System.out.println("A  - Strafe Left");
        System.out.println("D - Strafe Right");
        System.out.println("Mouse - Turn");
        System.out.println("Esc   - Exit");
        main = new HorrorSpace();
        main.create();
        main.run();
        }
        catch(Exception ex) {
        LOGGER.log(Level.SEVERE,ex.toString(),ex);
        }
        finally {
        if(main != null) {
            main.destroy();
        }
        }
    }

    public HorrorSpace() {
        Globals.curFrameTime = System.nanoTime() / 1000000;
    }

    public void create() throws LWJGLException {
        //Display
        try{
        Display.setDisplayMode(new DisplayMode(DisplayProperties.INITIAL_DISPLAY_WIDTH,DisplayProperties.INITIAL_DISPLAY_HEIGHT));
        Display.setLocation(0, 0);
        }
        catch(LWJGLException o){
            System.out.println(o.getLocalizedMessage());
        }
        Display.setFullscreen(false);
        Display.setTitle("Horror!");
        Display.create();

        //Keyboard
        Keyboard.create();

        //Mouse
        Mouse.setGrabbed(false);
        Mouse.create();

        //OpenGL
        initGL();
        resizeGL();
        
        //Load shaders
        Shaders.loadShaders();
        
        //Load initial map
        try {
            room = new RoomLoader().loadFile("assets/Start_Room.room");
        } catch (IOException ex) {
            Logger.getLogger(HorrorSpace.class.getName()).log(Level.WARNING, null, ex);
        }
        Globals.physicsManager.addRoom(room);
    }

    public void destroy() {
        //Methods already check if created before destroying.
        Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();
    }

    public void initGL() {
        //2D Initialization
        glClearColor(0.0f,0.0f,0.0f,0.0f);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
        
        Resources.init();
        
        Globals.player = new Player();
        Globals.player.init();
        
        // enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        glClearDepth (1.0f);
        glDepthFunc (GL_LEQUAL);
        glShadeModel (GL_SMOOTH);
        glHint (GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        //glHint (GL11.GL_M, GL_NICEST);
        glHint (GL_POLYGON_SMOOTH_HINT, GL_NICEST);
    }

    public void render() {
        
        //Push projections for 3D Drawing
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        glPopMatrix();
        glLoadIdentity();
        GLU.gluPerspective(75,(float)displayProperties.getSize().x/displayProperties.getSize().y,0.01f,100);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        glPushMatrix();
        
        glClearColor(0.0f,0.0f,0.0f,0.0f);
        glClear(GL_COLOR_BUFFER_BIT| GL11.GL_DEPTH_BUFFER_BIT);
        
        GL11.glColor3f(1.0f,1.0f,1.0f);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL_DEPTH_TEST);
        
        //Move the world to the player
        glPushMatrix();
        GL11.glTranslated(0,0,-0.4);
        glPushMatrix();
        //Rotate Here
        Quaternion rotation = Globals.player.getRotation();
//        rotation.
        Vector4f axisAngle = HorrorMath.quaternionToAxisAngle(rotation);
        GL11.glRotated(axisAngle.w * (180.0f / Math.PI), axisAngle.x, axisAngle.y, axisAngle.z);
        glPushMatrix();
        Vector3f position = Globals.player.getPosition();
        GL11.glTranslated(-position.x, -position.y, -position.z);
               
        room.render();
        
        glPopMatrix();
        glPopMatrix();
        glPopMatrix();
        
        GL11.glDisable(GL_DEPTH_TEST);

        //2D HUD Drawing
        
        glMatrixMode(GL_PROJECTION);
        glPopMatrix();
        glLoadIdentity();
        gluOrtho2D(0.0f,displayProperties.getSize().x,0.0f,displayProperties.getSize().y);
        glPushMatrix();

        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        
//        if(Globals.mapOn)
//        {
//            //Render Map
//        }
//        else if(Globals.miniMapOn)
//        {
//            //Render Minimap
//        }
        glPopMatrix();
    }

    public void resizeGL() {
        //2D Scene
        glViewport(0,0,displayProperties.getSize().x,displayProperties.getSize().y);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluOrtho2D(0.0f,displayProperties.getSize().x,0.0f,displayProperties.getSize().y);
        glPushMatrix();

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glPushMatrix();
    }

    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
        if(Display.isVisible()) {
            displayProperties.update();
            Globals.input.update();
            DisplayProperties.getInstance().update();
            update();
            render();
        }
        else {
            if(Display.isDirty()) {
            render();
            }
            try {
                Thread.sleep(100);
            }
            catch(InterruptedException ex) {
            }
        }
        Display.update();
        Display.sync(60);
        }
    }

    public void update() {
        long currentTime = System.nanoTime() / 1000000;
        if(currentTime <= Globals.curFrameTime)
        {
            currentTime += 1;
        }
        Globals.frameElapsed = (currentTime - Globals.curFrameTime) / 1000.0f;
        Globals.curFrameTime = currentTime;
        
        Globals.physicsManager.update();
    }
}
