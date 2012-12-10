package in.hpc.android.ramayan;

import android.view.MotionEvent;
import in.hpc.android.game.*;

public class Button extends Sprite {
    public Button(Game game, Texture texture, Bounds bounds, Vector2 position) {
        super(game, texture, bounds, position);
    }

    @Override
    public void update(double deltaTime) {
        MotionEvent e = InputManager.getEvent();
        if(e==null)
            return;
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            shape.setScale(new Vector2(2, 2));
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            shape.setScale(new Vector2(1, 1));
        }
    }
}
