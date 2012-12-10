package in.hpc.android.game;

public class Sprite {

    private Game game;
    private final Texture texture;
    private final Bounds bounds;
    private final Vector2 position;
    protected Square shape;
    private Vector2 tranlateVector;

    public Sprite(Game game, Texture texture, Bounds bounds, Vector2 position) {
        this.game = game;
        this.texture = texture;
        this.bounds = bounds;
        this.position = position;
    }

    public void initialize() {
        Shader basicShader = game.getShaderManager().getShader("basic_shader");
        shape = new Square(basicShader, texture);
        if (tranlateVector != null)
            shape.setTranslate(tranlateVector);
        shape.initialize();
    }

    public void update(double deltaTime) {

        shape.setPosition(position);
    }

    public void draw(float[] mvpMatrix) {
        shape.draw(mvpMatrix);
    }

    public void dispose() {
        texture.dispose();
    }

    public void translate(int x, int y) {
        this.tranlateVector = new Vector2(x, y);
    }
}
