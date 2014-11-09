in vec3 position;
in vec3 normal;
in vec2 textureCoord;

out vec2 pass_TextureCoord;

void main() {
	gl_Position = ftransform();
	pass_TextureCoord = textureCoord;
}