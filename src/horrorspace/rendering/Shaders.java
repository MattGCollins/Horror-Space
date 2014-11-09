/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horrorspace.rendering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL20;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author Matt
 */
public class Shaders {
    public static int vertexShader;
    public static int fragmentShader;
    public static int programId;
    
    public static void loadShaders() {
        programId = glCreateProgram();
        
        vertexShader = loadShader("shaders/shader.vert", GL20.GL_VERTEX_SHADER);

        fragmentShader = loadShader("shaders/shader.frag", GL20.GL_FRAGMENT_SHADER);
        
        glAttachShader(programId, vertexShader);
        glAttachShader(programId, fragmentShader);

        GL20.glBindAttribLocation(programId, 0, "position");
        GL20.glBindAttribLocation(programId, 1, "normal");
        GL20.glBindAttribLocation(programId, 2, "textureCoord");
        
        glLinkProgram(programId);
        glValidateProgram(programId);

    }
    
    private static int loadShader(String filename, int type) {
        StringBuilder shaderSource = new StringBuilder();
        int shaderID;

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                        shaderSource.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read file.");
            System.exit(-1);
        }

        shaderID = glCreateShader(type);
        glShaderSource(shaderID, shaderSource);
        glCompileShader(shaderID);

        if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Shader compile failed for " + type + ".");
            System.exit(-1);
        }

        return shaderID;
    }
}
