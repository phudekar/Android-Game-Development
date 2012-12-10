package in.hpc.android.game;

import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class GameSurfaceView extends GLSurfaceView {

    private Game game;
    private InputManager inputManager;

    public GameSurfaceView(Game game) {
        super(game);
        this.game = game;
        inputManager = new InputManager(game, this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        inputManager.processTouchEvent(e);
        return true;
    }
}
