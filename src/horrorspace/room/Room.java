package horrorspace.room;

import horrorspace.model.Model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matt
 */
public class Room {
    private final List<Model> models;

    Room(List<Model> models) {
        this.models = models;
    }
    
    public void render() {
        for (Model model : models) {
            model.render();
        }
    }
}
