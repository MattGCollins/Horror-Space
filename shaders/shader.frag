uniform sampler2D texture_diffuse;

in vec2 pass_TextureCoord;

void main() {
	gl_FragColor = texture(texture_diffuse, pass_TextureCoord);
}