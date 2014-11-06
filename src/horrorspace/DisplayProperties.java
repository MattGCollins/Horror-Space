/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace;

import java.awt.Point;
import org.lwjgl.opengl.Display;


/**
 *
 * @author Matt
 */
public class DisplayProperties {
    
    public static final int INITIAL_DISPLAY_WIDTH = 800;
    public static final int INITIAL_DISPLAY_HEIGHT = 600;
    
    private Point displayPosition;
    private Point displaySize;
    
    private static final DisplayProperties instance = new DisplayProperties();
    
    public static DisplayProperties getInstance() {
        return instance;
    }

    private DisplayProperties() {
        displayPosition = new Point();
        displaySize = new Point(INITIAL_DISPLAY_WIDTH, INITIAL_DISPLAY_HEIGHT);
    }
    
    public void update(){
        displayPosition.setLocation(Display.getX(), Display.getY());
        displaySize.setLocation(Display.getWidth(), Display.getHeight());
    }
    
    public Point getPosition(){
        return displayPosition;
    }
    
    public Point getSize(){
        return displaySize;
    }
    
    public Point getCenter(){
        return new Point(displayPosition.x + (displaySize.x / 2), displayPosition.y + (displaySize.y / 2));
    }
}
