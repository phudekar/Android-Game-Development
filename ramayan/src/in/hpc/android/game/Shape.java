package in.hpc.android.game;

import android.opengl.GLES20;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public abstract class Shape {

    protected FloatBuffer vertexBuffer;
    protected ShortBuffer indexBuffer;

    protected int shaderProgram;
    private float[] diffuseColor = new float[]{1, 1, 1, 1};
    private FloatBuffer textureCoordinateBuffer;
    private Shader shader;
    private Texture texture;
    private Vector2 position;
    private Vector2 scale;
    private Vector2 translate;

    protected Shape(Shader shader, Texture texture) {
        this.shader = shader;
        this.texture = texture;
        translate = new Vector2(0, 0);
        scale = new Vector2(1, 1);
    }

    public void initialize() {
        // initialize vertex byte buffer for shape coordinates
        vertexBuffer = BufferFactory.createFloatBuffer(getCoordinates());

        // initialize byte buffer for the draw list
        indexBuffer = BufferFactory.createShortBuffer(getIndices());

        // initialize byte buffer for the texture
        textureCoordinateBuffer = BufferFactory.createFloatBuffer(getTextureCoordinates());
        texture.loadTexture();

        shaderProgram = GLES20.glCreateProgram();             // create empty OpenGL ES Program
        GLES20.glAttachShader(shaderProgram, shader.getVertexShader());   // add the vertex shader to shaderProgram
        GLES20.glAttachShader(shaderProgram, shader.getFragmentShader()); // add the fragment shader to shaderProgram
        GLES20.glLinkProgram(shaderProgram);                  // creates OpenGL ES shaderProgram


    }

    public void draw(float[] mvpMatrix) {

        GLES20.glUseProgram(shaderProgram);

        // get handle to vertex shader's vPosition member
        int positionHandle = GLES20.glGetAttribLocation(shaderProgram, "vPosition");

        // Enable a handle to the vertices
        GLES20.glEnableVertexAttribArray(positionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(positionHandle, RendererConstants.COORDS_PER_VERTEX, GLES20.GL_FLOAT, false,
                RendererConstants.VERTEX_STRIDE, vertexBuffer);

        int colorHandle = GLES20.glGetUniformLocation(shaderProgram, "vColor");

        // Set diffuseColor for drawing the vertex
        GLES20.glUniform4fv(colorHandle, 1, diffuseColor, 0);

        int texturePositionHandle = GLES20.glGetAttribLocation(shaderProgram, "aTexturePosition");
        GLES20.glEnableVertexAttribArray(texturePositionHandle);
        GLES20.glVertexAttribPointer(texturePositionHandle, RendererConstants.TEX_COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, RendererConstants.TEX_COORDS_PER_VERTEX * 4, textureCoordinateBuffer);

        // Set the active texture unit to texture unit 0.
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        // Bind the texture to this unit.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture.getTextureId());

        int textureHandle = GLES20.glGetUniformLocation(shaderProgram, "uTexture");
        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES20.glUniform1i(textureHandle, 0);

        // get handle to shape's transformation matrix
        int modelViewProjectionMatrixHandle = GLES20.glGetUniformLocation(shaderProgram, "uMVPMatrix");
        Game.checkGlError("glGetUniformLocation");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(modelViewProjectionMatrixHandle, 1, false, mvpMatrix, 0);

        int scaleHandle = GLES20.glGetAttribLocation(shaderProgram, "aScale");
        GLES20.glVertexAttrib2f(scaleHandle, scale.getX(), scale.getY());

        int translateHandle = GLES20.glGetAttribLocation(shaderProgram, "aTranslate");
        GLES20.glVertexAttrib2f(translateHandle, translate.getX(), translate.getY());

        // Draw the square
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, getIndices().length,
                GLES20.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

    protected abstract short[] getIndices();

    protected abstract float[] getCoordinates();

    protected abstract float[] getTextureCoordinates();

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public void setTranslate(Vector2 translate) {
        this.translate = translate;
    }
}
