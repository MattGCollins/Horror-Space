/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.room.parsing;

import horrorspace.room.parsing.face.Face;
import horrorspace.room.Room;
import horrorspace.room.parsing.vertex.Vertex;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author Matt
 */
public class RoomPrototype {
    private List<Vertex> vertices;
    private List<Face> faces;
    private int vaoID;
    
    /**
     *
     * @param vertices
     */
    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }
    
    /**
     *
     * @param faces
     */
    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }
    
    public Room generateRoom() {
        vaoID = GL30.glGenVertexArrays();
        int vertexID = generateVertices();
        int faceID = generateFaces();
        int textureID = generateTextures();
        final Room room = new Room(vaoID, vertexID, textureID, faceID, faces.size() * 3);
        return room;
    }

    private int generateVertices() {
        int vertexID = GL15.glGenBuffers();
        final Vertex vertexInfo = vertices.get(0);
        int floatBufferSize = vertices.size() * vertexInfo.getVertexFloatSize();
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(floatBufferSize);
        for(Vertex vertex : vertices){
            vertexBuffer.put(vertex.getVertex());
        }
        vertexBuffer.flip();
        
        //Bind
        GL30.glBindVertexArray(vaoID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexID);
        //Add data to buffer
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBuffer, GL15.GL_STATIC_DRAW);
        //Define data structure
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, vertexInfo.getVertexStride(), 0);
        GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, vertexInfo.getVertexStride(), vertexInfo.getNormalOffset());
        GL20.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, vertexInfo.getVertexStride(), vertexInfo.getTextureOffset());
        //Unbind
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
        
        return vertexID;
    }

    private int generateTextures() {
        int vertexID = GL15.glGenBuffers();
//        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.size() * 2);
//        for(Vertex vertex : vertices){
//            vertexBuffer.put(vertex.getS());
//            vertexBuffer.put(vertex.getT());
//        }
//        vertexBuffer.flip();
//        
//        //Bind
//        GL30.glBindVertexArray(vaoID);
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexID);
//        //Add data to buffer
//        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBuffer, GL15.GL_STATIC_DRAW);
//        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
//        //Unbind
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
//        GL30.glBindVertexArray(0);
        
        return vertexID;
    }

    private int generateFaces() {
        int faceId = GL15.glGenBuffers();
        IntBuffer faceBuffer = BufferUtils.createIntBuffer(faces.size() * 3);
        for(Face face : faces){
            for(int index : face.getIndices()){
                faceBuffer.put(index);
            }
        }
        faceBuffer.flip();
        
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, faceId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, faceBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        return faceId;
    }
}
