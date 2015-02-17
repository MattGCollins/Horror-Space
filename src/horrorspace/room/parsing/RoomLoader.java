package horrorspace.room.parsing;

import horrorspace.gravity.PlyGravityCuboid;
import horrorspace.physics.collision.parsing.PlyCollisionModel;
import horrorspace.model.parsing.ModelUnloadableException;
import horrorspace.model.parsing.PlyModel;
import horrorspace.parsing.FileLoader;
import horrorspace.parsing.LoadableItemPrototype;
import horrorspace.parsing.PlyElement;
import horrorspace.room.Room;
import horrorspace.util.LinebreakFileReader;
import java.io.IOException;

/**
 *
 * @author Matt
 */
public class RoomLoader extends FileLoader<Room> {
@Override
    protected void checkTypeIsCorrect(LinebreakFileReader linebreakFileReader) throws IOException {
        if(!"ply-format-room".equals(linebreakFileReader.readLine())){
            throw new ModelUnloadableException("Room was not of type \"ply-format-room\".");
        }
    }

    @Override
    protected PlyElement createElement(String lastLine) {
        if(lastLine.contains("model")) {
            return new PlyModel(lastLine);
        } else if(lastLine.contains("collisionModel")) {
            return new PlyCollisionModel(lastLine);
        } else if(lastLine.contains("gravityCuboid")) {
            return new PlyGravityCuboid(lastLine);
        }
        return null;
    }

    @Override
    protected LoadableItemPrototype getPrototype() {
        return new RoomPrototype();
    }
}
