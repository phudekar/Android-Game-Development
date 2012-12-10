package in.hpc.android.game;

public class Square extends Shape {

    private float coordinates[] = {
            -0.5f, 0.5f, 0.0f,   // top left
            -0.5f, -0.5f, 0.0f,   // bottom left
            0.5f, -0.5f, 0.0f,   // bottom right
            0.5f, 0.5f, 0.0f}; // top right

    private float[] textureCoordinates = {
            0.0f, 1.0f,  // top left
            0.0f, 0.0f,     // bottom left
            1.0f, 0.0f,    // bottom right
            1.0f, 1.0f};    // top right

    private short indices[] = {0, 1, 2, 0, 2, 3}; // order to draw vertices

    public Square(Shader shader, Texture texture) {
        super(shader, texture);
    }

    @Override
    protected short[] getIndices() {
        return indices;
    }

    @Override
    protected float[] getCoordinates() {
        return coordinates;
    }

    @Override
    protected float[] getTextureCoordinates() {
        return textureCoordinates;
    }
}