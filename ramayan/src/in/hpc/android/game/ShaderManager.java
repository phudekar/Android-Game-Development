package in.hpc.android.game;

import android.content.Context;
import android.opengl.GLES20;
import in.hpc.R;

import java.util.HashMap;
import java.util.Map;

public class ShaderManager {

    private Map<String, Shader> shaders;
    private Context context;
    private final ResourceManager resourceManager;

    public ShaderManager(Context context) {
        this.context = context;
        shaders = new HashMap<String, Shader>();
        resourceManager = new ResourceManager(context);
    }

    public Shader getShader(String shaderName) {
        if (!shaders.containsKey(shaderName)) {
            int vertexShaderResource = context.getResources().getIdentifier(shaderName + "_vert", "raw", R.class.getPackage().getName());
            int fragmentShaderResource = context.getResources().getIdentifier(shaderName + "_frag", "raw", R.class.getPackage().getName());
            int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderResource);
            int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderResource);
            Shader shader = new Shader(vertexShader, fragmentShader);
            shaders.put(shaderName, shader);
        }
        return shaders.get(shaderName);
    }

    public int loadShader(int type, int resourceId) {
        String shaderCode = resourceManager.getFileContents(resourceId);
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

}
