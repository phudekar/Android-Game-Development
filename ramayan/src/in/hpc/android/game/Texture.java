package in.hpc.android.game;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;


public class Texture {

    private int textureId;
    private Game game;
    private int resourceId;

    public Texture(Game game, int resourceId) {
        this.game = game;
        this.resourceId = resourceId;
    }

    public int loadTexture() {
        int[] textureHandle = new int[1];
        GLES20.glGenTextures(1, textureHandle, 0);

        if (textureHandle[0] != 0) {
            textureId = textureHandle[0];
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;   // No pre-scaling

            // Read in the resource
            final Bitmap bitmap = BitmapFactory.decodeResource(game.getResources(), resourceId, options);

            // Bind to the texture in OpenGL
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);
            Game.checkGlError("");

            // Set filtering
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            Game.checkGlError("");
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
            Game.checkGlError("");
            // Load the bitmap into the bound texture.
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
            Game.checkGlError("");
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
            Game.checkGlError("");
            // Recycle the bitmap, since its data has been loaded into OpenGL.
            bitmap.recycle();
            Game.checkGlError("");
        }

        if (textureHandle[0] == 0) {
            throw new RuntimeException("Error loading texture.");
        }

        return textureId;
    }

    public void bind() {
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
    }

    public void dispose() {
        bind();
        int[] textureIds = {textureId};
        GLES20.glDeleteTextures(1, textureIds, 0);
    }

    public int getTextureId() {
        return textureId;
    }
}
