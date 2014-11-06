/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing;

import java.io.IOException;

/**
 *
 * @author Matt
 */
public class RoomUnloadableException extends IOException{
    private final String message;
    
    public RoomUnloadableException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message + "\n" + super.getMessage();
    }
}
