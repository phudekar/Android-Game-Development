package in.hpc.android.game;

import java.util.ArrayList;
import java.util.List;

public class Screen {

    private Screen nextScreen;
    private Screen previousScreen;
    private Game game;

    protected List<Sprite> sprites;
    private boolean initialized;

    public Screen(Game game) {

        this.game = game;
        sprites = new ArrayList<Sprite>();
    }

    public void update(double deltaTime) {
        for (Sprite sprite : sprites) {
            sprite.update(deltaTime);
        }
    }

    public void initialize() {
        for (Sprite sprite : sprites) {
            sprite.initialize();
        }
    }

    public void draw(float[] mvpMatrix) {
        for (Sprite sprite : sprites) {
            sprite.draw(mvpMatrix);
        }
    }

    public Screen getNextScreen() {
        return nextScreen;
    }

    public void setNextScreen(Screen nextScreen) {
        this.nextScreen = nextScreen;
    }

    public Screen getPreviousScreen() {
        return previousScreen;
    }

    public void setPreviousScreen(Screen previousScreen) {
        this.previousScreen = previousScreen;
    }

    public void dispose() {
        for (Sprite sprite : sprites) {
            sprite.dispose();
        }
    }
}
