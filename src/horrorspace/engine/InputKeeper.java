package horrorspace.engine;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import horrorspace.DisplayProperties;

/**
 *
 * @author Elle
 */
public class InputKeeper {
    private Point mousePos;
    private Point mousePosPrev;
    
    private boolean leftDown;
    private boolean leftDownPrev;
    
    private boolean rightDown;
    private boolean rightDownPrev;
    
    private boolean[] keysDown;
    private boolean[] keysDownPrev;
    private LinkedList<Integer> keysToCheck;
    
    private boolean captured;
    private DisplayProperties displayProperties = DisplayProperties.getInstance();
    
    
    public InputKeeper()
    {
        Point center = displayProperties.getCenter();
        mousePos = new Point(center);
        mousePosPrev = new Point(center);
        
        leftDown = false;
        leftDownPrev = false;
        
        rightDown = false;
        rightDownPrev = false;
        
        keysToCheck = new LinkedList<>();
        
        keysDown = new boolean[1024];
        keysDownPrev = new boolean[1024];
        
    }
    
    public void update()
    {
        observeMousePosition();
        observeMouseButtons();
        observeKeys();
        if(captured){
        doCaptureMouse();
        }
    }
    
    public void addCheck(int i)
    {
        if(!keysToCheck.contains(i))
        {
            keysToCheck.add(i);
        }
    }
    public void removeCheck(int i)
    {
        keysToCheck.remove(i);
    }
    
    public boolean isDown(int key)
    {
        return keysDown[key];
    }
    
    public Point getMouseDiff(){
        return new Point(mousePos.x - mousePosPrev.x, mousePos.y - mousePosPrev.y);
    }
    
    public void captureMouse(boolean capture){
        this.captured = capture;
        Mouse.setGrabbed(capture);
    }

    private void observeMousePosition() {
        if(captured){
            Point center = displayProperties.getCenter();
            mousePosPrev.x = center.x;
            mousePosPrev.y = center.y;
        } else {
            mousePosPrev.x = mousePos.x;
            mousePosPrev.y = mousePos.y;
        }
        mousePos.x = Mouse.getX();
        mousePos.y = Mouse.getY();
    }

    private void observeMouseButtons() {
        leftDownPrev = leftDown;
        leftDown = Mouse.isButtonDown(0);
        
        rightDownPrev = rightDown;
        rightDown = Mouse.isButtonDown(1);
    }

    private void observeKeys() {
        Iterator i = keysToCheck.iterator();
        if(keysToCheck.size() > 0)
        {
            do
            {
                int currentCheck = (int)i.next();
                keysDownPrev[currentCheck] = keysDown[currentCheck];
                keysDown[currentCheck] = Keyboard.isKeyDown(currentCheck);
            }while(i.hasNext());
        }
    }

    private void doCaptureMouse() {
        Point center = displayProperties.getCenter();
        Mouse.setCursorPosition(center.x, center.y);
    }
    
}
