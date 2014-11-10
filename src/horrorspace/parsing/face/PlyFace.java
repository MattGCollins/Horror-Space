package horrorspace.parsing.face;

import horrorspace.parsing.PlyElement;
import horrorspace.parsing.PlyProperty;
import horrorspace.parsing.ImproperPrototypeException;
import horrorspace.parsing.LoadableItemPrototype;
import horrorspace.util.LinebreakFileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Matt
 */
public class PlyFace implements PlyElement {
    List<PlyProperty> properties = new LinkedList<>();
    int faceCount;

    public PlyFace(String readLine) {
        String[] split = readLine.split("face ");
        faceCount = Integer.parseInt(split[1]);
    }

    @Override
    public void addProperty(String readLine) {
        if(readLine.contains("list")) {
            properties.add(new PlyVertexList());
        }
    }

    @Override
    public void process(LinebreakFileReader linebreakFileReader, LoadableItemPrototype prototype) throws IOException {
        if(!(prototype instanceof FaceHandlingPrototype)){
            throw new ImproperPrototypeException("Cannot load faces into prototype.");
        }
        FaceHandlingPrototype facePrototype = (FaceHandlingPrototype) prototype;
        List<Face> faces = new ArrayList<>();
        for(int iter = 0; iter < faceCount; ++iter){
            String readLine = linebreakFileReader.readLine();
            String[] data = readLine.split(" ");
            int vertexCount = Integer.parseInt(data[0]);
            if(vertexCount == 3){
                int[] vertices = new int[3];
                vertices[0] = Integer.parseInt(data[1]);
                vertices[1] = Integer.parseInt(data[2]);
                vertices[2] = Integer.parseInt(data[3]);
                faces.add(new Face(vertices));
            }else if(vertexCount == 4){
                int[] vertices = new int[3];
                vertices[0] = Integer.parseInt(data[1]);
                vertices[1] = Integer.parseInt(data[2]);
                vertices[2] = Integer.parseInt(data[3]);
                faces.add(new Face(vertices));
                vertices = new int[3];
                vertices[0] = Integer.parseInt(data[3]);
                vertices[1] = Integer.parseInt(data[4]);
                vertices[2] = Integer.parseInt(data[1]);
                faces.add(new Face(vertices));
            }
        }
        facePrototype.setFaces(faces);
    }
    
}
