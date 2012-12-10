package in.hpc.android.game;

import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Game extends Activity implements GLSurfaceView.Renderer {
    public volatile float angle;
    private ResourceManager resourceManager;
    private Screen currentScreen;
    private GameState currentState;
    private ShaderManager shaderManager;

    private int modelViewProjectionMatrixHandle;
    private float[] modelViewProjectionMatrix = new float[16];
    private float[] viewMatrix = new float[16];
    private float[] projectionMatrix = new float[16];
    private float[] rotationMatrix = new float[16];
    private Square square;
    private int shaderProgram;
    private int positionHandle;

    private static final String TAG = "Game";
    private GameSurfaceView surfaceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new GameSurfaceView(this);
        surfaceView.setEGLContextClientVersion(2);

        surfaceView.setRenderer(this);
        surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(surfaceView);
        this.resourceManager = new ResourceManager(this);
        this.shaderManager = new ShaderManager(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(0, 0, 0, 1);
        currentScreen.initialize();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        // make adjustments for screen ratio
        float ratio = (float) width / height;
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }


    @Override
    protected void onPause() {
        super.onPause();
        // Deallocate memory intensive graphics objects
        surfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reallocate memory intensive graphics objects if you have disabled them in onPause()
        surfaceView.onResume();
    }


    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        // Create a camera view matrix
        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -6, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        Matrix.setRotateM(rotationMatrix, 0, angle, 0, 0, -1.0f);

        // Combine the projection and camera view matrices
        Matrix.multiplyMM(modelViewProjectionMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        // Combine the rotation matrix with the projection and camera view
        Matrix.multiplyMM(modelViewProjectionMatrix, 0, rotationMatrix, 0, modelViewProjectionMatrix, 0);

        currentScreen.update(0);
        currentScreen.draw(modelViewProjectionMatrix);
    }

    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    public void setCurrentScreen(Screen screen) {
        if (currentScreen != null)
            currentScreen.dispose();
        this.currentScreen = screen;
    }

    public ShaderManager getShaderManager() {
        return shaderManager;
    }
}
