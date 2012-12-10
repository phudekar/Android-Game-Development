precision mediump float;

uniform vec4 vColor;

uniform sampler2D uTexture;

varying vec2 vTexturePosition;

void main() {
  gl_FragColor = vColor * texture2D(uTexture,vTexturePosition);
}
