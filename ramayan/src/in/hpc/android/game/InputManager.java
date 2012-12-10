package in.hpc.android.game;

import android.view.MotionEvent;

public class InputManager {

    private float previousX;
    private float previousY;
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    float p1X, p1Y, p2X, p2Y;
    float DX1, DY1, DX2, DY2;
    private static int fingers;
    private Game game;
    private GameSurfaceView gameSurfaceView;
    private static MotionEvent event;

    public InputManager(Game game, GameSurfaceView gameSurfaceView) {

        this.game = game;
        this.gameSurfaceView = gameSurfaceView;
    }

    public void processTouchEvent(MotionEvent e) {
        event = e;
        float x = e.getX();
        float y = e.getY();

        int action = e.getAction();

        // 1 finger down
        if (action == MotionEvent.ACTION_DOWN) {
            p1X = e.getX(0);
            p1Y = e.getY(0);
            fingers = 1;
        }

        // 2 finger down
        if (action == MotionEvent.ACTION_DOWN && e.getActionIndex() == 1) {
            p2X = e.getX(1);
            p2Y = e.getY(1);
            fingers = 2;
        }

        // pointer 1 up
        if (action == MotionEvent.ACTION_UP) {
            DX1 = e.getX(0) - p1X;
            DY1 = e.getY(0) - p1Y;
//
//            if (e.getActionIndex() == 1) {
//                DX2 = e.getX(1) - p2X;
//                DY2 = e.getY(1) - p2Y;
//            }

        }

        if (action == MotionEvent.ACTION_MOVE) {
            float dx = x - previousX;
            float dy = y - previousY;

            // reverse direction of rotation above the mid-line
            if (y > gameSurfaceView.getHeight() / 2) {
                dx = dx * -1;
            }

            // reverse direction of rotation to left of the mid-line
            if (x < gameSurfaceView.getWidth() / 2) {
                dy = dy * -1;
            }

            game.angle += (dx + dy) * TOUCH_SCALE_FACTOR;  // = 180.0f / 320
            gameSurfaceView.requestRender();
        }

        previousX = x;
        previousY = y;
    }

    public static MotionEvent getEvent() {
        return event;
    }
}
