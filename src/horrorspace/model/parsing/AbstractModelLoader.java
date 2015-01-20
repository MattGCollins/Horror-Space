package horrorspace.model.parsing;

import horrorspace.parsing.FileLoader;
import horrorspace.parsing.PlyElement;
import horrorspace.parsing.face.PlyFace;
import horrorspace.parsing.vertex.PlyVertex;
import horrorspace.util.LinebreakFileReader;
import java.io.IOException;

/**
 *
 * @author Matt
 */
public abstract class AbstractModelLoader<T> extends FileLoader<T>{
    @Override
    protected void checkTypeIsCorrect(LinebreakFileReader linebreakFileReader) throws IOException {
        if(!"ply".equals(linebreakFileReader.readLine())){
            throw new ModelUnloadableException("Model was not of type \"ply\".");
        }
        if(!"format ascii 1.0".equals(linebreakFileReader.readLine())){
            throw new ModelUnloadableException("Model was not the proper format.");
        }
    }

    @Override
    protected PlyElement createElement(String lastLine) {
        if(lastLine.contains("vertex")) {
            return new PlyVertex(lastLine);
        } else if(lastLine.contains("face")) {
            return new PlyFace(lastLine);
        }
        return null;
    }
}
