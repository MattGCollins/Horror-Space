package horrorspace.parsing;

import java.io.IOException;

/**
 *
 * @author Matt
 */
public class ModelUnloadableException extends IOException{
    private final String message;
    
    public ModelUnloadableException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message + "\n" + super.getMessage();
    }
}
