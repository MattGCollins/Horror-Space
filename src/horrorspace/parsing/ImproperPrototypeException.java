package horrorspace.parsing;

import java.io.IOException;

/**
 *
 * @author Matt
 */
public class ImproperPrototypeException extends IOException {
    private final String message;
    
    public ImproperPrototypeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message + "\n" + super.getMessage();
    }
}
